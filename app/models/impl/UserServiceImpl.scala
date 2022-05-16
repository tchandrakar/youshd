package models.impl

import models.RequestDTOs.{LoginUserDTO, SignUpUserDTO}
import models.Tables.{UsersHistoryRow, UsersRow}
import models.UserService._
import models.{Tables, UserService}
import play.api.libs.json.{JsObject, Json}
import slick.jdbc.PostgresProfile.api._

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.inject.Singleton
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class UserServiceImpl extends UserService {
  lazy val db = Database.forConfig("defaultDb")
  lazy val usersQuery = Tables.Users
  lazy val usersHistoryQuery = Tables.UsersHistory

  override def getUsers(): Future[Seq[JsObject]] = db.run {
    usersQuery.result.map(_.map(r => Json.obj("firstName" -> r.firstName,
      "lastName" -> r.lastName, "emailId" -> r.emailId, "createdAt" -> r.createdAt)))
  }



  override def signUpUser(signUpUserDTO: SignUpUserDTO): Future[UserDatabaseQuery] = db.run {
    val time = Timestamp.valueOf(LocalDateTime.now())
    (for {
      dbQuery <- validateUserDBIO(signUpUserDTO.loginUserDTO)
      userSignUpStatus <- dbQuery match {
        case UserNotSignedUp =>
          (signUpUserDTO.firstName, signUpUserDTO.lastName, signUpUserDTO.address) match {
            case (Some(fname), Some(lname), Some(addr)) =>
              (for {
                _ <- usersQuery += UsersRow(-1, fname, lname, addr,
                  signUpUserDTO.loginUserDTO.emailId, signUpUserDTO.loginUserDTO.password,
                  time, time)
                userRow <- usersQuery.filter(_.emailId === signUpUserDTO.loginUserDTO.emailId)
                  .result.headOption.map(_.getOrElse(throw UserNotFoundException(signUpUserDTO.loginUserDTO.emailId)))
                _ <- usersHistoryQuery += UsersHistoryRow(-1, userRow.id, fname, lname, addr,
                  signUpUserDTO.loginUserDTO.emailId, updatedAt = time, superseededBy = None)
              } yield UserSignedUp).transactionally
            case _ => throw RequiredFieldsForSignUpNotFound(s"One of the fields: firstName, lastName, address, " +
              s"not entered for sign up")
          }
        case r => DBIO.successful(r)
      }
    } yield userSignUpStatus).transactionally
  }

  override def validateUser(loginUserDTO: LoginUserDTO): Future[Unit] = db.run {
    validateUserDBIO(loginUserDTO).map {
      case UserService.UserNotSignedUp => throw UserNotFoundException(loginUserDTO.emailId)
      case UserService.UserPresentInDatabase => ()
      case _ => throw UnKnownExceptionOccurred()
    }
  }

  private def validateUserDBIO(loginUserDTO: LoginUserDTO): DBIO[UserDatabaseQuery] = {
    (for {
      user <- usersQuery.filter(r => r.emailId === loginUserDTO.emailId && r.password === loginUserDTO.password).result.headOption
    } yield {
      user match {
        case Some(_) => UserPresentInDatabase
        case None => UserNotSignedUp
      }
    }).transactionally
  }
}
