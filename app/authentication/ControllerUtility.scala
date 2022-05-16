package authentication

import play.api.libs.json.{JsError, JsSuccess, JsValue, Json, OFormat}
import play.api.mvc.{Request, Result, Results}
import play.mvc.Http.Status

import scala.concurrent.Future

object ControllerUtility {
  implicit class AuthenticateRequest(request: Request[JsValue]) {
    def validateJson[T](f: T => Future[Result])(implicit formats: OFormat[T]) = {
      request.body.validate[T] match {
        case JsSuccess(value, _) => f(value)
        case JsError(errors) => Future.successful(Results.BadRequest(failedResponse(Status.BAD_REQUEST,
        "json-is-invalid", "Json is invalid", s"$errors")))
      }
    }
  }
  def failedResponse(result: Int, code: String, title: String, description: String) = {
    Json.obj("status" -> result, "success" -> false,
      "error" -> Json.obj("code" -> code, "title" -> title, "traceId" -> "", "description" -> description,
        "param" -> "", "docURL" -> ""))
  }
}
