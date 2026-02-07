FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/kafkaorders-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-Dspring.profiles.active=heroku", "-jar", "app.jar"]