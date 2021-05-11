package features.auth.interactors

import features.auth.entity.UserAccount
import features.auth.repository.UserAccountsRepository

class CreateUserAccountInteractor(
    private val userAccountsRepository: UserAccountsRepository = UserAccountsRepository()
) {

    suspend fun execute(userId: String) {
        val userAccount = userAccountsRepository.getAccountById(userId)

        if (userAccount == null) userAccountsRepository.put(UserAccount(userId))
    }
}
