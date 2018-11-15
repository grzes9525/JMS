SET APP_PATH=%cd%\..\target
SET APP_NAME=nov-1.0-SNAPSHOT.jar
SET CONFIG_PATH=%APP_PATH%\config\

java -jar %APP_PATH%\nov-1.0-SNAPSHOT.jar --spring.config.location=%CONFIG_PATH%