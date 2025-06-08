#!/usr/bin/env bash

DOCKER_IMAGE_PREFIX="ivanfranchin"
APP_VERSION="1.0.0"

STREAMER_DATA_JPA_APP_NAME="streamer-data-jpa"
STREAMER_DATA_R2DBC_APP_NAME="streamer-data-r2dbc"

STREAMER_DATA_JPA_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${STREAMER_DATA_JPA_APP_NAME}:${APP_VERSION}"
STREAMER_DATA_R2DBC_DOCKER_IMAGE_NAME="${DOCKER_IMAGE_PREFIX}/${STREAMER_DATA_R2DBC_APP_NAME}:${APP_VERSION}"

SKIP_TESTS="true"

./mvnw clean spring-boot:build-image \
  --projects "$STREAMER_DATA_JPA_APP_NAME" \
  -DskipTests="$SKIP_TESTS" \
  -Dspring-boot.build-image.imageName="$STREAMER_DATA_JPA_DOCKER_IMAGE_NAME"

./mvnw clean spring-boot:build-image \
  --projects "$STREAMER_DATA_R2DBC_APP_NAME" \
  -DskipTests="$SKIP_TESTS" \
  -Dspring-boot.build-image.imageName="$STREAMER_DATA_R2DBC_DOCKER_IMAGE_NAME"
