# Dockerfile for Eureka Service
#FROM openjdk:17-jdk-alpine
# Dockerfile for Eureka Service targeting ARM64
FROM openjdk:17-jdk-slim

# Create the necessary directory for the application
RUN mkdir -p /user/app

# Copy the jar file into the container
COPY target/serviceregistry-0.0.1-SNAPSHOT.jar /user/app/serviceregistry-0.0.1-SNAPSHOT.jar

# Set the working directory
WORKDIR /user/app

# Run the application
ENTRYPOINT ["java", "-jar", "serviceregistry-0.0.1-SNAPSHOT.jar"]

# Expose the port
EXPOSE 8761
