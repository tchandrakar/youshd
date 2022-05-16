package models

import com.google.inject.ImplementedBy
import models.RequestDTOs.{CreateUserRequestDTO, LoginUserDTO, SignUpUserDTO}
import models.UserService.UserDatabaseQuery
import models.impl.UserServiceImpl
import play.api.libs.json.{Format, JsObject, JsResult, JsValue, Json}

import scala.concurrent.Future

@ImplementedBy(classOf[UserServiceImpl])
trait UserService {
  def getUsers(): Future[Seq[JsObject]]

  def signUpUser(signUpUserDTO: SignUpUserDTO): Future[UserDatabaseQuery]

  def validateUser(loginUserDTO: LoginUserDTO): Future[UserDatabaseQuery]
}

object UserService {
  trait UserDatabaseQuery {
    def objectKey: String
  }

  object UserDatabaseQuery {
    private val all = Set(UserPresentInDatabase, UserSignedUp, UserNotSignedUp)

    implicit val formats = new Format[UserDatabaseQuery] {
      override def writes(o: UserDatabaseQuery): JsValue = Json.toJson(o.objectKey)

      override def reads(json: JsValue): JsResult[UserDatabaseQuery] =
        json.validate[String].map(r => all.find(_.objectKey == r).getOrElse(throw UnKnownExceptionOccurred()))
    }
  }

  case object UserPresentInDatabase extends UserDatabaseQuery {
    override def objectKey: String = "USER_PRESENT_IN_DATABASE"
  }

  case object UserNotSignedUp extends UserDatabaseQuery {
    override def objectKey: String = "USER_NOT_SIGNED_UP"
  }

  case object UserSignedUp extends UserDatabaseQuery {
    override def objectKey: String = "USER_SIGNED_UP"
  }

  case object UserLoggedIn extends UserDatabaseQuery {
    override def objectKey: String = "USER_LOGGED_IN"
  }

  case class UserNotFoundException(emailId: String)
    extends Exception(s"User with $emailId and input password is not present in database")

  case class UnKnownExceptionOccurred()
    extends Exception("Unknown Exception occurred")

  case class RequiredFieldsForSignUpNotFound(message: String)
    extends Exception(s"User sign up failed: $message")
}