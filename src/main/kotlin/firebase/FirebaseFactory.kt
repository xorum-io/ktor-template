package firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import java.io.ByteArrayInputStream

object FirebaseFactory {

    fun init() {
        val serviceAccount = getEnvServiceAccount() ?: getLocalServiceAccount()

        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()

        FirebaseApp.initializeApp(options)
    }

    private fun getEnvServiceAccount() = System.getenv("ADMIN_KEY")?.let { ByteArrayInputStream(it.toByteArray()) }

    private fun getLocalServiceAccount() =
        object {}.javaClass.getResourceAsStream("/firebase-adminsdk.json")
}
