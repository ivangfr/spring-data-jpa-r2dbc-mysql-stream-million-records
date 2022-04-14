#!/usr/bin/env bash

MYSQL_PWD="secret"
docker exec -i -e MYSQL_PWD=$MYSQL_PWD mysql mysql -uroot customerdb < mysql/init-db.sql
docker exec -i -e MYSQL_PWD=$MYSQL_PWD mysql mysql -uroot customerdb < mysql/load-data.sql
