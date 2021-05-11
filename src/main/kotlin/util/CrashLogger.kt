package util

import io.sentry.Sentry

object CrashLogger {

    fun log(t: Throwable) {
        Sentry.captureException(t)
    }

    fun log(message: String) {
        Sentry.captureException(Throwable(message))
    }
}
