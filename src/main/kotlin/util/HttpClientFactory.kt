package util

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

object HttpClientFactory {

    private val cachedClients = mutableMapOf<String, HttpClient>()

    fun getClient(link: String) = (cachedClients[link] ?: createClient(link)).also { cachedClients[link] = it }

    private fun createClient(link: String): HttpClient = HttpClient {
        expectSuccess = true
        defaultRequest {
            url {
                host = link
                protocol = URLProtocol.HTTPS
            }
        }
        Json {
            serializer = KotlinxSerializer(
                Json(from = Json.Default) {
                    isLenient = true
                    ignoreUnknownKeys = true
                    allowSpecialFloatingPointValues = true
                    useArrayPolymorphism = true
                    encodeDefaults = true
                }
            )
        }
        Logging {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }
}

suspend fun getError(responseContent: ByteReadChannel) =
    responseContent.readUTF8Line()?.let {
        Json(from = Json.Default) { ignoreUnknownKeys = true }.decodeFromString(Error.serializer(), it)
    }

@Serializable
data class Error(val comment: String? = null)

suspend inline fun <T> request(block: () -> T) = try {
    Response.Success(block())
} catch (clientRequestException: ClientRequestException) {
    val error = getError(clientRequestException.response.content)?.comment
    error?.let {
        println(it)
        CrashLogger.log(it)
    }
    Response.Failure(error)
} catch (t: Throwable) {
    println(t)
    CrashLogger.log(t)
    Response.Failure(null)
}

sealed class Response<T> {

    data class Success<T>(
        val result: T
    ) : Response<T>()

    data class Failure<T>(
        val error: String?
    ) : Response<T>()
}