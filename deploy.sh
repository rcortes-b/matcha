#!/bin/sh

DB_FILE_PATH=infrastructure/utils/docker-compose.yml
SERVICES_FILE_PATH=infrastructure/docker-compose.yml

./cleanup.sh

# Utilities Containers
docker compose -f ${DB_FILE_PATH} up -d

# Services Build #
cd backend
mvn clean package -DskipTests
cd ..

#Services Containers
docker compose -f $SERVICES_FILE_PATH up -d