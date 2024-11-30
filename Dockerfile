# Use Java 17 base image
FROM eclipse-temurin:17-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the local build output directory to the container's /app directory
COPY build/libs/sew_stylesbackend-0.0.1-SNAPSHOT.jar app.jar

# Expose application port (5200 in your docker-compose.yml)
EXPOSE 5200

# Set the command to directly start the Java application
CMD ["java", "-jar", "app.jar"]
