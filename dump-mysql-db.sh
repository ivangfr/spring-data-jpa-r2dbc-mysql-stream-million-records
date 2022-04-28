#!/usr/bin/env bash

docker exec -e MYSQL_PWD=secret mysql mysqldump -t -uroot customerdb customer > mysql/load-data.sql
