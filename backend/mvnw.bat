@echo off
REM Runs Maven with JAVA_HOME auto-detected from 'where java' if needed

if "%JAVA_HOME%"=="" goto detect

if exist "%JAVA_HOME%\bin\java.exe" goto run
if exist "%JAVA_HOME%\bin\javac.exe" goto run

echo JAVA_HOME is set to "%JAVA_HOME%" but java.exe not found there. Detecting...
goto detect

:detect
for /f "delims=" %%i in ('where java 2^>nul') do (
    set "JAVA_EXE=%%i"
    goto derived
)
echo ERROR: Java not found. Add Java to PATH or set JAVA_HOME correctly.
exit /b 1

:derived
REM JAVA_EXE = C:\...\jdk-25\bin\java.exe -> need parent of bin
for %%i in ("%JAVA_EXE%") do set "BIN_DIR=%%~dpi"
REM BIN_DIR ends with \bin\ - remove \bin
set "JAVA_HOME=%BIN_DIR:~0,-5%"
goto run

:run
call mvn %*
