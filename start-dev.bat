@echo off
echo Starting Todo App Development Environment...
echo.

echo Starting Backend (Spring Boot with MySQL)...
start cmd /k "cd backend && mvn compile exec:java -Dexec.mainClass=com.todoapp.TodoAppBackendApplication -Dexec.classpathScope=runtime"

echo Waiting 15 seconds for backend to start...
timeout /t 15

echo Starting Frontend (React + Vite)...
start cmd /k "cd frontend && npm run dev"

echo.
echo Both servers should be starting up:
echo - Backend API: http://localhost:8080
echo - Frontend: http://localhost:3000
echo - MySQL Database: localhost:3306/todoapp_db
echo.
echo Application Features:
echo ✅ Username-based authentication (no more email login)
echo ✅ MySQL database persistence
echo ✅ JWT token authentication
echo ✅ SOLID principles architecture
echo ✅ Advanced task management (Standard, Deadline, Saving Goals)
echo.
echo Getting Started:
echo 1. Visit http://localhost:3000
echo 2. Register a new account with username/password
echo 3. Start creating todo collections and tasks!
echo.
echo Database Access:
echo - Use MySQL Workbench to connect to localhost:3306
echo - Database: todoapp_db
echo - User: todoapp_user
echo.
pause