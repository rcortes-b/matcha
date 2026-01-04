#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 \
  --username "$POSTGRES_USER" \
  --dbname postgres <<-EOSQL

  CREATE DATABASE db_auth;
  CREATE DATABASE db_user;

  GRANT ALL PRIVILEGES ON DATABASE db_auth TO "$POSTGRES_USER";
  GRANT ALL PRIVILEGES ON DATABASE db_user TO "$POSTGRES_USER";

EOSQL
