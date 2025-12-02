# UniSocial – Student-to-Student Social Media and Marketplace (Even for trivial Jobs! Makes daily life easier)

UniSocial is a platform for university students to exchange services, home cooked meals, tutoring, errands and micro-jobs. This repository contains the **initial skeleton** for frontend, backend microservices and AI services.

## Tech Stack
- **Frontend:** React (browser)
- **Backend:** Java Spring Boot microservices
  - User Service
  - Task Service
  - Feed Service
  - Messaging Service
  - Notification Service
- **AI Microservice:** Python FastAPI (for price prediction, moderation, task matching)
- **Database:** PostgreSQL + pgvector
- **Caching:** Redis
- **Storage:** Amazon S3 / MinIO
- **Messaging:** Kafka
- **Deployment:** Docker + Docker Compose

## Current Status
🚧 **Initial Skeleton**
- Backend: User Service Spring Boot skeleton created
- Frontend: To be added
- AI Service: To be added
- Docker setup: To be added

## Architecture Diagram

```mermaid
flowchart TD
    subgraph Frontend
        A[React Web App] -->|REST / WebSocket| B[API Gateway]
    end

    subgraph Backend
        B --> U[User Service]
        B --> T[Task Service]
        B --> F[Feed Service]
        B --> N[Notification Service]
        B --> M[Messaging Service]
    end

    subgraph AI_Service
        AI[AI Microservice] -->|Price Prediction, Moderation, Matching| T
        AI -->|Content Moderation| F
    end

    subgraph Database
        DB[PostgreSQL + pgvector]
        Cache[Redis]
    end

    subgraph Storage
        S3[Amazon S3 / MinIO]
    end

    subgraph Messaging_Queue
        Kafka[Kafka Topics]
    end

    U --> DB
    T --> DB
    F --> DB
    N --> DB
    M --> DB

    T --> Cache
    F --> Cache
    N --> Cache
    M --> Cache

    T --> Kafka
    N --> Kafka
    M --> Kaf

For Java code:
This project uses environment variables for database configuration and JWT authentication.

Create the following environment variables before running the application:

Required variables
Variable	Description
DB_URL	- JDBC URL of your PostgreSQL database
DB_USERNAME	- Database username
DB_PASSWORD	- Database password
JWT_SECRET	- Secret key for signing JWT tokens