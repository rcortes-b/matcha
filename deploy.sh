#!/bin/sh

DB_FILE_PATH=infrastructure/database/docker-compose.yml
SERVICES_FILE_PATH=infrastructure/docker-compose.yml

./cleanup.sh

# Utilities Containers
docker compose -f ${DB_FILE_PATH} up -d

# Services Build #
#----------------#
# Auth Service #
backend/auth/build.sh
# Gateway Service #
backend/gateway/build.sh


#Services Containers
docker compose -f $SERVICES_FILE_PATH up -d