# Stage 1: Build the app
FROM eclipse-temurin:21-jdk-alpine AS builder

# Install dependencies needed for the installer
RUN apk add --no-cache curl

# Install Scala CLI
RUN curl -fL https://github.com/VirtusLab/scala-cli/releases/latest/download/scala-cli-x86_64-pc-linux.gz | gzip -d > /usr/local/bin/scala-cli && \
    chmod +x /usr/local/bin/scala-cli

WORKDIR /app

# Copy your project files
COPY projects/web_app /app/projects/web_app

# Pre-compile the app so it starts faster in the cloud
RUN scala-cli compile projects/web_app/app.sc

# Stage 2: Run the app
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the Scala CLI binary and the compiled app from the builder
COPY --from=builder /usr/local/bin/scala-cli /usr/local/bin/scala-cli
COPY --from=builder /app /app

# Expose the port (8080)
EXPOSE 8080

# Run the app
# We use --server=true to ensure it binds correctly in a container
ENTRYPOINT ["scala-cli", "run", "projects/web_app/app.sc", "--host", "0.0.0.0", "--port", "8080"]