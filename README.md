## Ktor template

This is a template for the development of backend applications using [Ktor framework](https://ktor.io/) powered by [Kotlin programming language](https://kotlinlang.org/).

[Exposed ORM framework](https://github.com/JetBrains/Exposed) is used to wrap the [PostgreSQL](https://www.postgresql.org/) database connected by [HikariCP JDBC pool](https://github.com/brettwooldridge/HikariCP).

[Firebase Auth](https://firebase.google.com/products/auth) helps to authenticate users for each request by passing the bearer token generated on the client side by one of Firebase client SDKs.

[Sentry](https://sentry.io/welcome/) is used for logging crashes.

Find more details on this template in [our Medium blog](https://medium.com/xorum-io/battle-tested-template-project-for-backend-with-kotlin-and-ktor-c655a2e276c2?sk=004c7d55091c004a314686b48d606df6).

## To do

Once cloned do the following to adapt template to your needs.

1. Change project name in `settings.gradle`.
2. Change `group` in `build.gradle`
3. Change `name` and `description` in `app.json`.
4. Generate `testToken` (http://jwtbuilder.jamiekurtz.com/) and `testUserId` (https://www.uuidgenerator.net/) and set them in `FirebaseAuth.kt`. These variables are needed to simplify testing endpoints using Postman or similar tools.
5. Create new Firebase project. Put firebase admin key file to resources and name it `firebase-adminsdk.json`. Set `ADMIN_KEY` environment variable at your server.
6. Set `defaultJdbcUrl` in `DatabaseFactory.kt`. Here is an example used with PostgreSQL `jdbc:postgresql://localhost:5432/<dbName>?user=<userName>&password=<password>&reWriteBatchedInserts=true`
7. When ready to deploy to Heroku, set GitHub secrets which are used in `github/workflows` YAML files (CI): `HEROKU_APP_NAME_PROD`, `HEROKU_APP_NAME_STAGING`, `HEROKU_EMAIL`, and `HEROKU_API_KEY`. Set `GRADLE_TASK` environment variable to `shadowJar` at your server.
8. Set `SENTRY_DSN` environment variable at your server to send crashes to Sentry.
