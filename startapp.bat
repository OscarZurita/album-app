@echo off
echo Iniciando MySQL Server...
start "MySQL Server" mysqld --console

echo Iniciando Backend (Spring Boot)...
start "Spring Boot Backend" cmd /k "cd /d C:\Users\oscar\Desktop\LSR\demo && mvnw spring-boot:run"

echo Iniciando Frontend (React)...
start "React Frontend" cmd /k "cd /d C:\Users\oscar\Desktop\LSR\demo\frontend && npm start"

pause

