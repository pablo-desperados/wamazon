# Wamazon - A simple Spring Boot application - CSC 540

## Description
This is a simple Spring Boot application that demonstrates how one can setup an eccomerce website with springboot and dynamic templating. Some of the features included in the project are:

- User Authentication
- Database to persist data
- Input validation
- User sessions management & more!

## Prerequisites
- Java JDK 8 or higher installed
- Maven installed. Here is a guide to install maven in several OS [here](https://maven.apache.org/download.cgi).


## Getting Started
1. Clone the repository:
2. Navigate to the project directory and run:
```bash
mvn clean install
mvn spring-boot:run
```
3. The app should start on port 8080

4. To populate the database with some initial products please run this:
```bash
curl -X POST http://localhost:8080/seed
```

5. On your browser, go to `http://localhost:8080/`


## Run tests
On the project directory, run the following command:
```bash
mvn test
```
