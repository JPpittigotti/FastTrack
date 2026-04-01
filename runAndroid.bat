@echo off
title Ejecutando App Android (Portable)

REM -----------------------------
REM Configuración del proyecto
REM -----------------------------
set PACKAGE=com.example.fasttrack

REM Ajusta aquí la Activity principal si tu proyecto tiene otra
set ACTIVITY=.MainActivity

REM -----------------------------
REM Detectar carpeta del bat y gradlew
REM -----------------------------
set PROJ_DIR=%~dp0
set GRADLEW=%PROJ_DIR%gradlew.bat

if not exist "%GRADLEW%" (
    echo ERROR: No se encontro gradlew.bat en %PROJ_DIR%
    pause
    exit /b 1
)

REM -----------------------------
REM Verificar ADB
REM -----------------------------
echo ================================
echo Verificando ADB...
echo ================================
adb start-server >nul 2>&1

echo Esperando emulador...
adb wait-for-device >nul 2>&1

echo Esperando que Android termine de iniciar...
:checkboot
for /f "delims=" %%i in ('adb shell getprop sys.boot_completed') do set boot=%%i
if not "%boot%"=="1" (
    timeout /t 2 >nul
    goto checkboot
)

REM -----------------------------
REM Compilar e instalar app
REM -----------------------------
echo ================================
echo Compilando e instalando app...
echo ================================
call "%GRADLEW%" installDebug

if %errorlevel% neq 0 (
    echo ERROR: Fallo la compilacion
    pause
    exit /b 1
)

REM -----------------------------
REM Abrir app en el emulador
REM -----------------------------
echo ================================
echo Abriendo app...
echo ================================
adb shell am start -n %PACKAGE%/%ACTIVITY%

echo ================================
echo App ejecutada correctamente 🚀
echo ================================
pause