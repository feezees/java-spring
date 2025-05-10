# Spring Boot Demo Application

This is a simple Spring Boot application that demonstrates basic REST API functionality.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Building the Application

To build the application, run:

```bash
mvn clean install
```

## Running the Application

To run the application, use:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Available Endpoints

- GET `/hello?name={name}` - Returns a greeting message
  - Example: `http://localhost:8080/hello?name=John`
  - If no name is provided, it defaults to "World"

## Project Structure

- `src/main/java/com/example/demo/DemoApplication.java` - Main application class
- `src/main/java/com/example/demo/controller/HelloController.java` - REST controller
- `src/main/java/com/example/demo/service/GreetingService.java` - Service layer 