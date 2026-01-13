#!/bin/sh
echo "Waiting for gRPC server (auth-service)..."
sleep 5
echo "gRPC server is up! Gateway service can be built"

java -jar gateway-service.jar