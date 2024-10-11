# Dockerfile for user
#FROM openjdk:17-jdk-alpine
# Dockerfile for Eureka Service targeting ARM64
FROM --platform=linux/arm64 openjdk:17-jdk-slim

# Create the necessary directory for the application
RUN mkdir -p /user/app

# Copy the JAR file into the container
COPY target/userservice-0.0.1-SNAPSHOT.jar /user/app/

# Set the working directory
WORKDIR /user/app

# Run the application
ENTRYPOINT ["java", "-jar", "userservice-0.0.1-SNAPSHOT.jar"]

# Expose the service port
EXPOSE 8080

