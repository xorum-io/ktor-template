package features.auth.repository

import util.dbQuery
import features.auth.database.UserAccountsDao
import features.auth.entity.UserAccount
import org.jetbrains.exposed.sql.*

class UserAccountsRepository {

    suspend fun getAccountById(id: String) = dbQuery {
        UserAccountsDao.select { UserAccountsDao.id eq id }
            .map { toAccount(it) }
            .singleOrNull()
    }

    suspend fun put(account: UserAccount) = dbQuery {
        UserAccountsDao.insert {
            it[id] = account.id
        }
    }

    private fun toAccount(row: ResultRow) = UserAccount(
        id = row[UserAccountsDao.id]
    )
}