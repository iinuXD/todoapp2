# Todo App

A full-stack enterprise todo application with Spring Boot backend and React frontend.

## Features

- **User Authentication**: Register and login functionality
- **Collections**: Organize todos into collections
- **Multiple Task Types**: 
  - Standard tasks
  - Deadline tasks with due dates
  - Saving goal tasks with progress tracking
- **Polymorphic Design**: Object-oriented approach with factory pattern
- **Modern UI**: Responsive design with Tailwind CSS
- **Real-time Updates**: Live progress tracking for saving goals

## Tech Stack

### Backend
- Spring Boot 3.1.5
- Spring Data JPA
- Spring Security
- H2 Database (development)
- Maven

### Frontend
- React 18
- Vite
- Tailwind CSS
- Axios for API calls

## Getting Started

### Prerequisites
- Java 17 or higher
- Node.js 16 or higher
- npm or yarn
- Maven

### Quick Start

**Option 1: Use the provided script**
```bash
# Windows Batch
start-dev.bat

# Or PowerShell
.\start-dev.ps1
```

**Option 2: Manual startup**

#### Backend Setup

1. Navigate to the backend directory:
```bash
cd backend
```

2. Compile and run the Spring Boot application:
```bash
mvn compile exec:java -Dexec.mainClass=com.todoapp.TodoAppBackendApplication -Dexec.classpathScope=runtime
```

The backend will start on http://localhost:8080

#### Frontend Setup

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies (if not already done):
```bash
npm install
```

3. Start the development server:
```bash
npm run dev
```

The frontend will start on http://localhost:3000

### Database Access

The H2 database console is available at: http://localhost:8080/h2-console

**Connection details:**
- JDBC URL: `jdbc:h2:mem:todoappdb`
- Username: `sa`
- Password: `password`

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login user

### Collections
- `GET /api/collections` - Get all collections
- `POST /api/collections` - Create a new collection
- `PUT /api/collections/{id}` - Update a collection
- `DELETE /api/collections/{id}` - Delete a collection

### Tasks
- `GET /api/collections/{collectionId}/tasks` - Get tasks in a collection
- `POST /api/collections/{collectionId}/tasks` - Create a new task
- `PUT /api/tasks/{taskId}` - Update a task
- `DELETE /api/tasks/{taskId}` - Delete a task
- `POST /api/tasks/{taskId}/add-money` - Add money to a saving goal

## Demo Credentials

For testing, you can use these demo credentials:
- Email: `admin@example.com`
- Password: `password`

## Development

### Running Both Servers

For development, you'll need to run both the backend and frontend servers:

1. Terminal 1 (Backend):
```bash
cd backend
./mvnw spring-boot:run
```

2. Terminal 2 (Frontend):
```bash
cd frontend
npm run dev
```

### Build for Production

#### Backend
```bash
cd backend
./mvnw clean package
```

#### Frontend
```bash
cd frontend
npm run build
```

## Architecture

### Backend Architecture
- **Controller Layer**: REST API endpoints
- **Service Layer**: Business logic
- **Repository Layer**: Data access
- **Entity Layer**: Database models

### Frontend Architecture
- **Components**: Reusable UI components
- **Services**: API communication
- **Context**: State management
- **Models**: Business logic and factory patterns

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request