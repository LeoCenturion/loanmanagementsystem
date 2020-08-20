SHELL := /bin/bash
PWD := $(shell pwd)

default: docker-image
docker-image:
	docker build . -t "loanmanagement"
.PHONY: docker-image

create-tables:
	docker run --network=loanmanagementsystem_default  -e PGPASSWORD=mysecretpassword  postgres:latest   psql -U postgres -h db -d postgres -c " $$(cat ./src/main/resources/create.sql)"
.PHONY: create-tables

populate-tables: create-tables
	docker run --network=loanmanagementsystem_default  -e PGPASSWORD=mysecretpassword  postgres:latest   psql -U postgres -h db -d postgres -c " $$(cat ./src/main/resources/populate.sql)"
.PHONY: populate-tables-tables

drop-schema:
	docker run --network=loanmanagementsystem_default  -e PGPASSWORD=mysecretpassword  postgres:latest   psql -U postgres -h db -d postgres -c " DROP schema main cascade"
.PHONY: drop-schema