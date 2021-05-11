package util

import firebase.FirebasePrincipal
import io.ktor.application.ApplicationCall
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import org.apache.http.auth.AuthenticationException

val ApplicationCall.userId: String
    get() = principal<FirebasePrincipal>()?.userId ?: throw AuthenticationException()

val ApplicationCall.userIdOrNull: String?
    get() = principal<FirebasePrincipal>()?.userId

suspend fun ApplicationCall.respondError(
    error: String?,
    httpStatusCode: HttpStatusCode = HttpStatusCode.BadRequest
) = respond(httpStatusCode, mapOf("error" to error))