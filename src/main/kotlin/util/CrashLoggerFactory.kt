package util

import io.sentry.Sentry
import io.sentry.SentryOptions

object CrashLoggerFactory {

    fun init() {
        System.getenv("SENTRY_DSN")?.let { dsn ->
            Sentry.init { options: SentryOptions ->
                options.dsn = dsn
            }
        }
    }
}