package util

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import features.auth.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils.create

object DatabaseFactory {

    private const val defaultJdbcUrl = ""

    fun init() {
        Database.connect(hikari())
        transaction {
            create(UserAccountsDao)
        }
    }

    private fun hikari() = HikariDataSource(HikariConfig().apply {
        jdbcUrl = System.getenv("JDBC_DATABASE_URL") ?: defaultJdbcUrl
    })
}

suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
    transaction { block() }
}