# ==========================================
# Stage 1 → Build Stage
# ==========================================

FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set working directory inside container
WORKDIR /app

# Copy Maven configuration file
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

# Copy generated JAR from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose Spring Boot application port
EXPOSE 7003

# Start Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]