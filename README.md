# LiveConnect

LiveConnect is a video upload and streaming platform built using Java, Spring Boot, HTML, JavaScript, and CSS. This project supports user authentication, video uploads, and video streaming functionality. Users can log in, upload videos, and view uploaded videos.

## Table of Contents
- [Getting Started](#getting-started)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Pages Overview](#pages-overview)
  - [Login Page](#login-page)
  - [Video Upload Page](#video-upload-page)
  - [Video List Page](#video-list-page)
- [Spring Boot API Endpoints](#spring-boot-api-endpoints)
- [Contact](#contact)

---

## Getting Started

### Prerequisites
- Java 11 or higher
- Spring Boot
- Git
- A MongoDB, MySQL or PostgreSQL database (for user and video(metadata) data storage)
  
### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/Abhinav7272/LiveConnect.git
    cd LiveConnect
    ```

2. Install dependencies (if applicable):
    ```bash
    # Install backend dependencies (Java-based)
    ./mvnw install
    
    ```

3. Set up environment variables for database connection in `application.yaml`:
    ```properties
Spring:
  data:
    mongodb:
      database: VideoDataBase
      uri: mongodb+srv://gaurabhinav54:PassWordcluster0.xq6iw.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0
      auto-index-creation: true
    ```

4. Run the application:
    ```bash
    ./mvnw spring-boot:run
    ```

5. Open your browser and navigate to `http://localhost:8081`.

---

## Features

- **User Authentication**: Login functionality that provides an authorization token for secure requests.
- **Video Upload and Storage**: Users can upload video files, which are stored on the server.
- **Video Streaming**: Stream videos directly from the server to users.

---

## Technologies Used

- **Backend**: Java, Spring Boot, REST API
- **Frontend**: HTML, CSS, JavaScript- Using Themeleaf
- **Database**: MongoDB (for persistent storage)
- **Others**: JSON Web Tokens (JWT) for secure API authentication

---
## Spring Boot API Endpoints

- **Login** (POST /api/auth/login): Authenticates users and provides a JWT token.
- **Upload Video** (POST /api/videos/upload): Authenticated users can upload videos.
- **Get All Videos** (GET /api/videos/all): Returns a list of all uploaded videos.

## Contact 
Please connect me on gaurabhinav54@gmail.com

