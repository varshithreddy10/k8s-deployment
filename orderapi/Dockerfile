# ==========================================
# Stage 1 → Build Stage
# ==========================================

FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set working directory
WORKDIR /app

# Copy Maven configuration
COPY pom.xml .

# Copy source code
COPY src ./src

# Build Spring Boot application
RUN mvn clean package -DskipTests


# ==========================================
# Stage 2 → Runtime Stage
# ==========================================

FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy generated JAR file from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose application port
EXPOSE 7005

# Start Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]