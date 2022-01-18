# SEG-UNT A clean drop Restful services

### Project status

Currently in development

## Overview

Java / Maven / Spring Boot application which provides RESTful services with JWT security. It was the designed backend to manage the team document's for this [project](https://github.com/llanillo/AppSeg).


### Enviorement Setup

Minimal requirements: Please make sure following software is installed on your PC.

* [Maven](https://maven.apache.org/) (installed and configured)

## Technologies

* Spring Data JPA
* Spring MVC
* Spring Security
* Produce JSON output
* Build: Maven
* Database: MySQL
* [Lombok](https://github.com/projectlombok/lombok) to avoid boiler plate
* [Model mapper](https://github.com/modelmapper/modelmapper)
* [Java JWT](https://github.com/auth0/java-jwt)

### To run the application

The project can be imported as a maven application to any supported IDE. If you have any problems with the dependencies, run the command:

```
mvn dependency:resolve
```

Use Maven to build: 
```
mvn clean install
```

Use Spring boot plugin: 
```bash
mvn clean spring-boot:run
```
Access the deployed web application at: [http://localhost:8080/](http://localhost:8080/)
## Test

1. Enter the /test url
