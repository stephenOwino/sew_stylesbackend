# Use Java 21 base image
FROM eclipse-temurin:21-jdk-jammy

# Set working directory
WORKDIR /app

# Copy JAR file from Gradle build output directory
COPY build/libs/sew_stylesbackend-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 5200

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]


