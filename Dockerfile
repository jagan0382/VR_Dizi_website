# Use Maven for building the JAR
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use JDK for running the application
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app

# Copy the built JAR file to the runtime container
COPY --from=build /app/target/vr_tech_services-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8082

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
