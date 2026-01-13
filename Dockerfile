# --- BUILD STAGE ---
FROM eclipse-temurin:21-jdk AS builder
RUN apt-get update && apt-get install -y curl
WORKDIR /app

# Install Scala CLI
RUN curl -fL https://github.com/VirtusLab/scala-cli/releases/latest/download/scala-cli-x86_64-pc-linux.gz | gzip -d > /usr/local/bin/scala-cli && chmod +x /usr/local/bin/scala-cli

COPY . .

# Build a "fat jar" (assembly) so we don't need the compiler at runtime
RUN scala-cli --power package projects/web_app/app.sc --assembly -o app.jar --server=false

# --- RUN STAGE ---
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/app.jar .

# IMPORTANT: You must also copy the public files to the final image!
COPY --from=builder /app/projects /app/projects

EXPOSE 8080

# Run the pre-compiled JAR directly (fastest startup)
ENTRYPOINT ["java", "-jar", "app.jar"]