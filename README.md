# SEG-UNT A clean drop Restful services

### Project status

Currently in development

## Overview

Java / Spring Boot application which provides RESTful services with JWT security. It was the designed backend to manage the team information/document's from this [desktop app](https://github.com/llanillo/SEG-UNT-JavaFx-Client). It can be used for future development of websites or applications related to the project


### Enviorement Setup

To be able to execute any Spring or Maven command from your IDE. Please make sure following software is installed:

* Spring plugin support for your IDE
* Maven plugin support for your IDE

## Technologies

* Spring Data JPA
* Spring MVC
* Spring Security
* Produce JSON output
* Maven
* MySQL
* [Lombok](https://github.com/projectlombok/lombok)
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

1. Enter to /test url

