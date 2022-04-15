#!/usr/bin/env bash

#if [ "$1" = "native" ];
#then
#  ./mvnw clean spring-boot:build-image --projects streamer-data-jpa
#  ./mvnw clean spring-boot:build-image --projects streamer-data-r2dbc
#else
  ./mvnw clean compile jib:dockerBuild --projects streamer-data-jpa
  ./mvnw clean compile jib:dockerBuild --projects streamer-data-r2dbc
#fi