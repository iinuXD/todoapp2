# Todo App Development Starter Script
Write-Host "Starting Todo App Development Environment..." -ForegroundColor Green
Write-Host ""

Write-Host "Starting Backend (Spring Boot with MySQL)..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd backend; mvn compile exec:java -Dexec.mainClass=com.todoapp.TodoAppBackendApplication -Dexec.classpathScope=runtime"

Write-Host "Waiting 15 seconds for backend to start..." -ForegroundColor Yellow
Start-Sleep -Seconds 15

Write-Host "Starting Frontend (React + Vite)..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd frontend; npm run dev"

Write-Host ""
Write-Host "Both servers should be starting up:" -ForegroundColor Green
Write-Host "- Backend API: http://localhost:8080" -ForegroundColor Cyan
Write-Host "- Frontend: http://localhost:3000" -ForegroundColor Cyan
Write-Host "- MySQL Database: localhost:3306/todoapp_db" -ForegroundColor Cyan
Write-Host ""
Write-Host "Application Features:" -ForegroundColor Green
Write-Host "✅ Username-based authentication (no more email login)" -ForegroundColor White
Write-Host "✅ MySQL database persistence" -ForegroundColor White
Write-Host "✅ JWT token authentication" -ForegroundColor White
Write-Host "✅ SOLID principles architecture" -ForegroundColor White
Write-Host "✅ Advanced task management (Standard, Deadline, Saving Goals)" -ForegroundColor White
Write-Host ""
Write-Host "Getting Started:" -ForegroundColor Green
Write-Host "1. Visit http://localhost:3000" -ForegroundColor White
Write-Host "2. Register a new account with username/password" -ForegroundColor White
Write-Host "3. Start creating todo collections and tasks!" -ForegroundColor White
Write-Host ""
Write-Host "Database Access:" -ForegroundColor Green
Write-Host "- Use MySQL Workbench to connect to localhost:3306" -ForegroundColor White
Write-Host "- Database: todoapp_db" -ForegroundColor White
Write-Host "- User: todoapp_user" -ForegroundColor White
Write-Host ""
Write-Host "Press any key to exit..."
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")