package firebase

import io.ktor.auth.*

data class FirebasePrincipal(
    val userId: String
) : Principal
