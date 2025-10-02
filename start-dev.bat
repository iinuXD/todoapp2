@echo off
echo Starting Todo App Development Environment...
echo.

echo Starting Backend (Spring Boot)...
start cmd /k "cd backend && mvn compile exec:java -Dexec.mainClass=com.todoapp.TodoAppBackendApplication -Dexec.classpathScope=runtime"

echo Waiting 10 seconds for backend to start...
timeout /t 10

echo Starting Frontend (React + Vite)...
start cmd /k "cd frontend && npm run dev"

echo.
echo Both servers should be starting up:
echo - Backend: http://localhost:8080
echo - Frontend: http://localhost:3000
echo - H2 Database Console: http://localhost:8080/h2-console
echo.
echo Demo login credentials:
echo Email: admin@example.com
echo Password: password
pause