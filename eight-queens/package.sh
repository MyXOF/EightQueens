#!/bin/sh

mvn clean package -DskipTests

if [ -d "./lib/" ]; then
   rm -r ./lib/
fi

mkdir ./lib/


cp ./target/eight-queens-0.0.1-SNAPSHOT.jar ./lib/



