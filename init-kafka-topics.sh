#!/usr/bin/env bash

NUM_PARTITIONS=5
docker exec zookeeper kafka-topics --create --bootstrap-server kafka:9092 --replication-factor 1 --partitions $NUM_PARTITIONS --topic com.mycompany.streamerdatajpa.customers
docker exec zookeeper kafka-topics --create --bootstrap-server kafka:9092 --replication-factor 1 --partitions $NUM_PARTITIONS --topic com.mycompany.streamerdatar2dbc.customers
