#!/bin/sh

echo "Cleaning docker environment..."

# Containers

## Postgres
docker stop matcha-postgres-dev
docker rm matcha-postgres-dev

## Redis
docker stop matcha-redis-dev
docker rm matcha-redis-dev

## Zookeeper
docker stop matcha-zookeeper-dev
docker rm matcha-zookeeper-dev

## Kafka
docker stop matcha-kafka-dev
docker rm matcha-kafka-dev
docker stop matcha-kafka-init-dev
docker rm matcha-kafka-init-dev

# Services Containers

# --- Auth Service --- #
docker stop matcha-auth-service-dev
docker rm matcha-auth-service-dev
docker image rm infrastructure-matcha-auth-service-dev

# --- Gateway Service --- #
docker stop matcha-gateway-service-dev
docker rm matcha-gateway-service-dev
docker image rm infrastructure-matcha-gateway-service-dev


#Volumes
docker volume rm matcha-db-data-dev

#Networks
docker network rm matcha-network-dev

echo "Successful cleaning!"
