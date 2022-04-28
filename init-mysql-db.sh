#!/usr/bin/env bash

MYSQL_PWD=secret
docker exec -i -e MYSQL_PWD=$MYSQL_PWD mysql mysql -uroot customerdb < mysql/init-db.sql

if [ "$1" != "0" ] &&
   [ "$1" != "100k" ] &&
   [ "$1" != "200k" ] &&
   [ "$1" != "500k" ] &&
   [ "$1" != "1M" ];
then
  printf "Invalid load data amount provided!"
  printf "\nValid Parameters: 0, 100k, 200k, 500k or 1M\n"
  exit 1
fi

if [ "$1" = "100k" ] ||
   [ "$1" = "200k" ] ||
   [ "$1" = "500k" ] ||
   [ "$1" = "1M" ];
then
  docker exec -i -e MYSQL_PWD=$MYSQL_PWD mysql mysql -uroot customerdb < mysql/load-$1-data.sql
fi
