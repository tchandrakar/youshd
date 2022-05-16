package models

import play.api.libs.json.{Json, OFormat}

object RequestDTOs {
  case class CreateUserRequestDTO(userName: String, password: String)

  object CreateUserRequestDTO {
    implicit val formats: OFormat[CreateUserRequestDTO] = Json.format[CreateUserRequestDTO];
  }

  case class SignUpUserDTO(firstName: Option[String], lastName: Option[String], address: Option[String],
                           loginUserDTO: LoginUserDTO)

  object SignUpUserDTO {
    implicit val formats: OFormat[SignUpUserDTO] = Json.format[SignUpUserDTO]
  }

  case class LoginUserDTO(emailId: String, password: String)

  object LoginUserDTO {
    implicit val formats: OFormat[LoginUserDTO] = Json.format[LoginUserDTO]
  }
}
