FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/kafkaorders-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT java -Dserver.port=${PORT:-8080} -Dspring.profiles.active=local -jar app.jar