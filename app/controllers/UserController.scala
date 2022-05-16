package controllers

import authentication.ControllerUtility._
import com.google.inject.Inject
import models.RequestDTOs.{CreateUserRequestDTO, SignUpUserDTO}
import models.UserService
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import utilities.DBUtils._
import authentication.ControllerUtility._

import scala.util.Try

class UserController @Inject()(cc: ControllerComponents,
                               userService: UserService) extends AbstractController(cc) {
  def login() = Action.async { implicit req: Request[AnyContent] =>
    Future.successful(Ok(views.html.login()))
  }

  def signUpUser() = Action.async(parse.json) { implicit req =>
    req.validateJson[SignUpUserDTO] { userRequestDTO =>
      Try {
        userService.signUpUser(userRequestDTO).safely
      }.flattenedEither.map {
        case Left(t: Throwable) => BadRequest(Json.toJson(failedResponse(BAD_REQUEST, "sign-up-failed",
          "Failed to sign up", t.getLocalizedMessage)))
        case Right(value) => Ok(Json.obj("status" -> "success", "user-status" -> value.objectKey))
      }
    }
  }

  def getAllUsers = Action.async { _ =>
    userService.getUsers().map(r => Ok(Json.toJson(r)))
  }
}
