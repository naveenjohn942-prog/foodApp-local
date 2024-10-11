# Dockerfile for order
#FROM openjdk:17-jdk-alpine
# Dockerfile for Eureka Service targeting ARM64
FROM --platform=linux/arm64 openjdk:17-jdk-slim

# Create the necessary directory for the application
RUN mkdir -p /user/app

# Copy the JAR file into the container
COPY target/orderservice-0.0.1-SNAPSHOT.jar /user/app/orderservice-0.0.1-SNAPSHOT.jar

# Set the working directory
WORKDIR /user/app

# Run the application
ENTRYPOINT ["java", "-jar", "orderservice-0.0.1-SNAPSHOT.jar"]

# Expose the service port
EXPOSE 8086

