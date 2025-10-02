# Todo App Development Starter Script
Write-Host "Starting Todo App Development Environment..." -ForegroundColor Green
Write-Host ""

Write-Host "Starting Backend (Spring Boot)..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd backend; mvn compile exec:java -Dexec.mainClass=com.todoapp.TodoAppBackendApplication -Dexec.classpathScope=runtime"

Write-Host "Waiting 10 seconds for backend to start..." -ForegroundColor Yellow
Start-Sleep -Seconds 10

Write-Host "Starting Frontend (React + Vite)..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd frontend; npm run dev"

Write-Host ""
Write-Host "Both servers should be starting up:" -ForegroundColor Green
Write-Host "- Backend: http://localhost:8080" -ForegroundColor Cyan
Write-Host "- Frontend: http://localhost:3000" -ForegroundColor Cyan
Write-Host "- H2 Database Console: http://localhost:8080/h2-console" -ForegroundColor Cyan
Write-Host ""
Write-Host "Demo login credentials:" -ForegroundColor Green
Write-Host "Email: admin@example.com" -ForegroundColor White
Write-Host "Password: password" -ForegroundColor White
Write-Host ""
Write-Host "Press any key to exit..."
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")