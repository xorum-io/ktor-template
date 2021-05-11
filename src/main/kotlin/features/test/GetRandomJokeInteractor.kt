package features.test

class GetRandomJokeInteractor(
    private val jokesRepository: JokesRepository = JokesRepository()
) {

    suspend fun execute() = jokesRepository.getRandomJoke()
}