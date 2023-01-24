#!/bin/bash

echo "Building... Discovery Service..."

cd discovery-service
docker build -t discovery-service .

echo "Building... API Gateway..."

cd ../api-gateway
docker build -t api-gateway .

echo "Building... Service A..."

cd ../service-a
./build.sh

echo "Building... Service B..."

cd ../service-b
./build.sh

echo "Done!"
