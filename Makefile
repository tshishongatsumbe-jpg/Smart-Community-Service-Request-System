
# Smart Community Service Request System
# Root Makefile

.PHONY: compile test clean build package install run all

compile:
	mvn compile

test:
	mvn test

clean:
	mvn clean

build:
	mvn clean compile

package:
	mvn clean package

install:
	mvn clean install

run:
	mvn exec:java

all:
	mvn clean test package

