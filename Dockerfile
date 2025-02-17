

# Use Maven for building the JAR
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use JDK for running the application
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/vr_tech_services-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "demo.jar"]
