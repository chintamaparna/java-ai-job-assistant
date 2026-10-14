# AI Job Assistant

An AI-powered web application that compares a candidate's CV with a selected job description and provides a match score, matching skills, missing skills, and a summary.

## Features

- Create, view, and delete job listings
- Store job descriptions in PostgreSQL
- Select a job from the frontend
- Upload a PDF CV and extract its text
- Compare the CV against the selected job using AI
- Display match score, matching skills, missing skills, and summary
- Run the application using Docker Compose

## Technology Stack

- **Backend:** Java 21, Spring Boot, Spring Data JPA
- **Frontend:** React, Vite
- **Database:** PostgreSQL 16
- **AI:** Spring AI and OpenAI
- **PDF Processing:** Apache PDFBox
- **Containerization:** Docker and Docker Compose

## Architecture

React frontend → Spring Boot REST API → AI analysis service → OpenAI API

Spring Boot also communicates with PostgreSQL to store and retrieve job descriptions.

## Prerequisites

- Docker Desktop
- An OpenAI API key

## Configuration

Create a `.env` file beside `docker-compose.yml`:

```text
OPENAI_API_KEY=your_api_key_here
```

Replace the placeholder with your own API key. Never commit `.env` to Git.

## Run the Application

Build the backend JAR before building the backend Docker image:

```bash
./mvnw clean package -DskipTests
docker build -t ai-job-assistant-backend .
```

Start the services:

```bash
docker compose up -d
```

View running containers:

```bash
docker compose ps
```

Stop the services:

```bash
docker compose down
```

The PostgreSQL data volume is preserved when using `docker compose down`. Avoid `docker compose down -v` unless you intend to delete the stored database volume.

## Application URLs

- Frontend: http://localhost:3000
- Backend API: http://localhost:8080/api/jobs

## Example Workflow

1. Create a job listing.
2. Select the job in the frontend.
3. Upload a PDF CV.
4. Review the extracted CV text.
5. Run the AI comparison.
6. Review the match score, matching skills, missing skills, and summary.

## Testing

Run backend tests with:

```bash
./mvnw clean test
```

## Future Improvements

- Add authentication and user accounts
- Add OCR support for scanned PDF files
- Add automated frontend tests
- Improve deployment and production configuration
