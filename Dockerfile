# Use Java 21 base image
FROM eclipse-temurin:21-jdk-jammy

# Set working directory
WORKDIR /app

# Copy jar file
COPY target/sew_styles-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 5200

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
