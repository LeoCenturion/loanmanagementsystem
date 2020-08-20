SHELL := /bin/bash
PWD := $(shell pwd)

default: docker-image
docker-image:
	docker build . -t "loanmanagement"
.PHONY: docker-image
