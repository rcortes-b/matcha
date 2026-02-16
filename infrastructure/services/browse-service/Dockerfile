FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY backend/browse/target/browse-0.0.1-SNAPSHOT.jar browse-service.jar

ENTRYPOINT ["java", "-jar", "browse-service.jar"]