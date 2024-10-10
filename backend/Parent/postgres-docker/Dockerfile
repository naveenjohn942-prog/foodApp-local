# Use the official PostgreSQL image as a base
FROM postgres:latest

# Set environment variables for the database
ENV POSTGRES_DB=akasa
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=root

# Copy the database dump into the container
COPY akasa.dump /docker-entrypoint-initdb.d/

