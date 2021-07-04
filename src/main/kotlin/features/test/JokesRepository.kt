package features.test

import io.ktor.client.*
import io.ktor.client.request.*
import util.HttpClientFactory
import util.request

class JokesRepository(
    private val httpClient: HttpClient = HttpClientFactory.getClient("official-joke-api.appspot.com")
) {

    suspend fun getRandomJoke() = request {
        httpClient.get<Joke>(path = "random_joke")
    }
}