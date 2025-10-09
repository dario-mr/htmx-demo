# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-21-jammy AS build
WORKDIR /app

# Install Node.js 22.x and pin npm to 11.x
RUN apt-get update && apt-get install -y curl \
    && curl -fsSL https://deb.nodesource.com/setup_22.x | bash - \
    && apt-get install -y nodejs \
    && npm install -g npm@11.6.2

COPY . .
RUN npm install
RUN mvn -B -q package -Pbuild-css -DskipTests

# Stage 2: Create a minimal runtime image
FROM gcr.io/distroless/java21-debian12
WORKDIR /app
COPY --from=build /app/target/app.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]