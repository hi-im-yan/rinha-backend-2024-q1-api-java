FROM openjdk:17-jdk-slim

COPY target/*.jar /app/app.jar

WORKDIR /app

CMD ["java", "-jar", "app.jar"]