#!/bin/sh

echo "Cleaning docker environment..."

# Containers
docker stop matcha_database
docker rm matcha_database
docker stop redis_cache
docker rm redis_cache

#Volumes
docker volume rm db_data
sudo rm -rf infrastructure/database/db_data

#Network
docker network rm matcha_network

echo "Successful cleaning!"