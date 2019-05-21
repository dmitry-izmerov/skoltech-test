#!/bin/sh

cd ../ && ./gradlew clean build

docker build --build-args=../build/libs/*.jar -t skoltech-test:1.0 .

docker-compose up -d
