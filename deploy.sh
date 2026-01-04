#!/bin/sh

DB_FILE_PATH=infrastructure/database/docker-compose.yml
SERVICES_FILE_PATH=infrastructure/docker-compose.yml

./cleanup.sh

docker compose -f ${DB_FILE_PATH} up -d

#docker compose -f $SERVICES_FILE_PATH up -d