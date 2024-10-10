# Dockerfile for inventory-service
#FROM openjdk:17-jdk-alpine
# Dockerfile for Eureka Service targeting ARM64
FROM openjdk:17-jdk-slim

COPY target/inventoryservice-0.0.1-SNAPSHOT.jar /user/app/inventoryservice-0.0.1-SNAPSHOT.jar

WORKDIR /user/app

ENTRYPOINT ["java", "-jar", "/user/app/inventoryservice-0.0.1-SNAPSHOT.jar"]

EXPOSE 8082
