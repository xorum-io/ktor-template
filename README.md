## Ktor template

## To do

Once cloned do the following to adapt template to your needs.

1. Change project name in `settings.gradle`.
2. Change `name` and `description` in `app.json`.
3. Generate `testToken` (http://jwtbuilder.jamiekurtz.com/) and `testUserId` (https://www.uuidgenerator.net/) and set them in `FirebaseAuth.kt`. These variables are needed to simplify testing endpoints using Postman or similar tools.
4. Create new Firebase project. Put firebase admin key file to resources and name it `firebase-adminsdk.json`.
5. Set `defaultJdbcUrl` in `DatabaseFactory.kt`. Here is an example used with PostgreSQL `jdbc:postgresql://localhost:5432/<dbName>?user=<userName>&password=<password>&reWriteBatchedInserts=true`
6. When ready to deploy to Heroku, set GitHub secrets which are used in `github/workflows` YAML files (CI): `HEROKU_APP_NAME_PROD`, `HEROKU_APP_NAME_STAGING`, `HEROKU_EMAIL`, and `HEROKU_API_KEY`.
