# beneficiaries

Search Latvian company register and their beneficiaries

# Getting Started

### About
This application will download company and beneficiary registers in CSV format from the Latvian
state register and then import both files into local database. Simple rest interface then
allows searchin database by name, surname and DOB.

### Prerequisites
You will need Java 11 for the application and Docker compose to spin up Postgresql database.

### How to start
0. Clone the repository.
1. Type `docker-compose up` in terminal. This will spin up docker container with postgresql database.
2. Type `./gradlew bootRun` in terminal. This will start Gradle build and launch the app.
3. Use http://localhost:8080/swagger-ui/ to view and try the API

### What is under the hood
Basically it just downloads two CSV files and then loads them into DB using Spring Batch

### What is still missing
1. tests
2. nicer db schema
3. better API for browsing

### What else?
1.`docker-compose down --rmi all --volumes` will shut down postgres and delete its volumes
2. application.properties has all the settings

