FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY backend/gateway/target/gateway-0.0.1-SNAPSHOT.jar gateway-service.jar
COPY infrastructure/services/gateway-service/init.sh .
RUN chmod +x init.sh

ENTRYPOINT ["./init.sh"]