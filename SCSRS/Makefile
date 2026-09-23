# Smart Community Service Request System (SCSRS)
# Makefile for Maven project

.PHONY: build compile test clean run package install

# Compile the project
compile:
	mvn compile

# Run all tests
test:
	mvn test

# Clean previous build files
clean:
	mvn clean

# Clean and compile
build:
	mvn clean compile

# Create the packaged JAR
package:
	mvn clean package

# Install the project into the local Maven repository
install:
	mvn clean install

# Run the application
run:
	mvn exec:java

# Clean, compile, test and package
all:
	mvn clean test package