FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY backend/user/target/user-0.0.1-SNAPSHOT.jar user-service.jar

ENTRYPOINT ["java", "-jar", "user-service.jar"]