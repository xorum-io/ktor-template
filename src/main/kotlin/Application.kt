import com.google.firebase.FirebaseApp
import features.auth.interactors.CreateUserAccountInteractor
import features.test.test
import firebase.FirebaseFactory
import firebase.FirebasePrincipal
import firebase.firebase
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import org.apache.http.auth.AuthenticationException
import util.CrashLoggerFactory
import util.DatabaseFactory
import util.respondError

private const val FIREBASE = "firebase"

fun Application.main(
    createUserAccountInteractor: CreateUserAccountInteractor = CreateUserAccountInteractor()
) {
    FirebaseFactory.init()
    DatabaseFactory.init()
    CrashLoggerFactory.init()

    install(ContentNegotiation) {
        json()
    }

    authentication {
        firebase(FIREBASE, FirebaseApp.getInstance()) {
            validate { credential ->
                val uid = credential.token.uid
                createUserAccountInteractor.execute(uid)
                FirebasePrincipal(uid)
            }
        }
    }

    install(StatusPages) {
        exception<AuthenticationException> {
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<BadRequestException> { exception ->
            exception.printStackTrace()
            call.respondError(exception.message)
        }
    }

    install(Routing) {
        authenticate(FIREBASE) {
            test()
        }
    }
}
