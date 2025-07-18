# Use Java 17 or 21 as needed
FROM openjdk:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the jar file
COPY target/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
