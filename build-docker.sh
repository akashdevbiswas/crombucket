#!/bin/bash

echo "######################## BUILDING DOCKER IMAGE ########################"

cd client

echo "######################## INSTALLING DEPENDENCIES ########################"
pnpm i

echo "######################## Building react app ########################"
pnpm build

cd ..
echo "######################## Copy static data to build directory ########################"
mv client/dist src/main/resources/static

echo "######################## Building the springboot application ########################"
mvn clean package

echo "######################## BUILDING DOCKER IMAGE ########################"
docker build -t crombucket .

echo "######################## DONE ########################"
