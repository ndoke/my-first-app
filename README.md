# My First App

A full-stack signup and login application with React, Spring Boot, and Cassandra.

## Tech Stack

- **Frontend:** React 18, React Router
- **Backend:** Spring Boot 3.2
- **Database:** Apache Cassandra

## Prerequisites

- **Java 17+** (with JAVA_HOME set)
- **Node.js 18+** and npm (for React frontend)
- **Apache Cassandra** (running locally or via Docker)
- **Maven 3.6+**

## Setup

### Option A: Run with Docker Compose (Recommended)

Runs Cassandra and the backend together on the same Docker network. Use this if you have connection issues when running the backend locally.

**Run in foreground** (logs visible in terminal):
```bash
cd my-first-app
docker-compose up --build
```

**Run in background** (detached mode):
```bash
cd my-first-app
docker-compose up -d --build
```

To stop services running in background:
```bash
docker-compose down
```

This will:
1. Start Cassandra
2. Wait for it to be ready
3. Build and start the backend

The API will be at http://localhost:8080. Then start the frontend separately (see step 3 below).

### Option B: Run Backend and Cassandra Separately

**1. Start Cassandra:**
```bash
docker run --name cass_cluster -d -p 9042:9042 cassandra:4.1
```

Wait ~60 seconds, then create the keyspace:
```bash
docker exec -it cass_cluster cqlsh -e "CREATE KEYSPACE IF NOT EXISTS user_auth WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};"
```

**2. Start the Backend**

```bash
cd my-first-app/backend
mvn spring-boot:run
```

Or use the Maven wrapper:
```bash
cd my-first-app/backend
.\mvnw.bat spring-boot:run
```

The API runs at http://localhost:8080

### 3. Start the Frontend (required for both options)

```bash
cd my-first-app/frontend
npm install
npm start
```

The app runs at http://localhost:3000

## Features

- **Signup** – Create account with email, password, full name, and optional phone
- **Login** – Sign in with email and password
- **Profile** – View profile after login
- **Logout** – Sign out and return to login

## API Endpoints

| Method | Endpoint      | Description        |
|--------|---------------|--------------------|
| POST   | /api/auth/signup | Create new user |
| POST   | /api/auth/login  | Authenticate user |

## Project Structure

```
my-first-app/
├── backend/                          # Spring Boot API
│   ├── src/main/java/com/example/auth/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── model/
│   │   ├── repository/
│   │   └── service/
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
├── frontend/                         # React app
│   └── src/
│       ├── api/
│       └── pages/
├── pom.xml
└── README.md
```
