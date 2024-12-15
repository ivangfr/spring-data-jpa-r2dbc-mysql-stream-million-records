#!/usr/bin/env bash

./mvnw clean compile jib:dockerBuild --projects streamer-data-jpa
./mvnw clean compile jib:dockerBuild --projects streamer-data-r2dbc
