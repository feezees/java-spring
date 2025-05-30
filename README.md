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

### Notes API (`/api/notes`)
All note endpoints require a valid cookie for authentication.

- GET `/api/notes` - Get all notes
  - Example: `http://localhost:8080/api/notes`
  - Returns: List of all notes

- GET `/api/notes/{id}` - Get a specific note by ID
  - Example: `http://localhost:8080/api/notes/1`
  - Returns: Note object or 404 if not found

- GET `/api/notes/user/{userId}` - Get all notes for a specific user
  - Example: `http://localhost:8080/api/notes/user/123e4567-e89b-12d3-a456-426614174000`
  - Returns: List of notes for the user or 404 if user not found

- POST `/api/notes` - Create a new note
  - Example: `http://localhost:8080/api/notes`
  - Request Body:
    ```json
    {
        "userId": "123e4567-e89b-12d3-a456-426614174000",
        "noteValue": "Your note content here"
    }
    ```
  - Returns: Created note object

- PUT `/api/notes/{id}` - Update an existing note
  - Example: `http://localhost:8080/api/notes/1`
  - Request Body:
    ```json
    {
        "userId": "123e4567-e89b-12d3-a456-426614174000",
        "noteValue": "Updated note content"
    }
    ```
  - Returns: Updated note object or 404 if not found

- DELETE `/api/notes/{id}` - Delete a note
  - Example: `http://localhost:8080/api/notes/1`
  - Returns: 200 OK if successful

### Posts API (`/api/posts`)
All post endpoints require a valid cookie for authentication.

- PUT `/api/posts` - Create a new post
  - Example: `http://localhost:8080/api/posts`
  - Request Body:
    ```json
    {
        "postBody": [
            {
                "bodyType": "text",
                "bodyValue": "Your post content here"
            },
            {
                "bodyType": "image",
                "bodyValue": "image_url_here"
            }
        ]
    }
    ```
  - Returns: Created post object or error message

### Logging API (`/api/log`)

- POST `/api/log` - Log an object
  - Example: `http://localhost:8080/api/log`
  - Request Body: Any JSON object
  - Returns: Integer value (52)

### Hello API

- GET `/hello` - Returns a greeting message
  - Example: `http://localhost:8080/hello?name=John`
  - Query Parameters:
    - `name` (optional): Name to greet (defaults to "World")
  - Returns: Greeting message

## Authentication

Most endpoints require a valid cookie for authentication. The cookie should be included in all requests to protected endpoints.

## CORS Configuration

The application allows requests from the following origins:
- http://localhost:8080
- http://localhost:3000
- http://localhost:5500
- http://127.0.0.1:5500

## Project Structure

- `src/main/java/com/example/demo/DemoApplication.java` - Main application class
- `src/main/java/com/example/demo/controller/HelloController.java` - REST controller
- `src/main/java/com/example/demo/service/GreetingService.java` - Service layer 