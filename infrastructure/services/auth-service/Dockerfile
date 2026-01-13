FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY backend/auth/target/auth-0.0.1-SNAPSHOT.jar auth-service.jar

ENTRYPOINT ["java", "-jar", "auth-service.jar"]