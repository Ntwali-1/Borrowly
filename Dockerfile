# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src

# Give mvnw executable permissions
RUN chmod +x mvnw

# Build the Spring Boot app
RUN ./mvnw clean package -DskipTests

# Expose port (Render sets its own $PORT env)
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/borrowly-0.0.1-SNAPSHOT.jar"]
