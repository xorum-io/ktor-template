package features.test

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import util.Response
import util.respondError

fun Route.test(
    getRandomJokeInteractor: GetRandomJokeInteractor = GetRandomJokeInteractor()
) = route("/test") {

    get("/random-joke") {
        when(val response = getRandomJokeInteractor.execute()) {
            is Response.Success -> call.respond(HttpStatusCode.OK, response.result)
            is Response.Failure -> call.respondError(response.error)
        }
    }
}