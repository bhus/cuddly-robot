# Stage 1: Build
FROM eclipse-temurin:21-jdk AS builder
RUN apt-get update && apt-get install -y curl
WORKDIR /app

# Install Scala CLI
RUN curl -fL https://github.com/VirtusLab/scala-cli/releases/latest/download/scala-cli-x86_64-pc-linux.gz | gzip -d > /usr/local/bin/scala-cli && \
    chmod +x /usr/local/bin/scala-cli

COPY . .

# Warm up: Pre-download dependencies
RUN scala-cli compile projects/web_app/app.sc --jvm system

# Stage 2: Runtime
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy Scala CLI and code from builder
COPY --from=builder /usr/local/bin/scala-cli /usr/local/bin/scala-cli
COPY --from=builder /app /app

# Zeabur will automatically pick up port 8080
EXPOSE 8080

# Run using system JVM
ENTRYPOINT ["scala-cli", "run", "projects/web_app/app.sc", "--jvm", "system", "--host", "0.0.0.0", "--port", "8080"]