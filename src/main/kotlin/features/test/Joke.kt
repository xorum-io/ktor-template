package features.test

import kotlinx.serialization.Serializable

@Serializable
data class Joke(
    val setup: String,
    val punchline: String
)
