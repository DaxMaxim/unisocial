# CampusConnect (UniSocial) – Student-to-Student Marketplace

UniSocial is a platform for university students to exchange services, home-cooked meals, tutoring, errands, and micro-jobs. This repository contains the **initial skeleton** for frontend, backend microservices and AI services.

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

