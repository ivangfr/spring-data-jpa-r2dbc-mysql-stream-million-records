#!/usr/bin/env bash

if [ "$1" = "native" ];
then
  ./mvnw -Pnative spring-boot:build-image --projects streamer-data-jpa -DskipTests
  ./mvnw -Pnative spring-boot:build-image --projects streamer-data-r2dbc -DskipTests
else
  ./mvnw clean compile jib:dockerBuild --projects streamer-data-jpa
  ./mvnw clean compile jib:dockerBuild --projects streamer-data-r2dbc
fi
