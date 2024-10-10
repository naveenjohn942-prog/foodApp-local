# Dockerfile for gateway
#FROM openjdk:17-jdk-alpine
# Dockerfile for Eureka Service targeting ARM64
FROM openjdk:17-jdk-slim

# Create the necessary directory for the application
RUN mkdir -p /user/app

# Copy the JAR file into the container
COPY target/gateway-0.0.1-SNAPSHOT.jar /user/app/

# Set the working directory
WORKDIR /user/app

# List the contents of the /user/app directory to verify the JAR is copied
RUN ls -la /user/app

# Run the application
ENTRYPOINT ["java", "-jar", "gateway-0.0.1-SNAPSHOT.jar"]

# Expose the service port
EXPOSE 9000

