@echo off
dir /s /B src\*.java > sources.txt
javac -cp "lib\sqlite-jdbc-3.42.0.0.jar" -d out @sources.txt
xcopy /E /I /Y src\main\resources out >nul
java -cp "out;lib\sqlite-jdbc-3.42.0.0.jar" com.reservation.Main
