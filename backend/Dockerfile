# Build stage
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

# Install Maven and build (use package from parent image if available, or install)
RUN apt-get update && apt-get install -y --no-install-recommends maven \
    && mvn package -DskipTests -B \
    && rm -rf /root/.m2 \
    && apt-get remove -y maven && apt-get autoremove -y && rm -rf /var/lib/apt/lists/*

# Run stage
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]
