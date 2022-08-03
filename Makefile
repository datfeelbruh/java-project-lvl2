.DEFAULT_GOAL := build-run

clean:
	./gradlew clean

build:
	./gradlew clean build

install-dist:
	./gradlew clean install

run-dist:
	./build/install/java-package/bin/java-package

lint:
	./gradlew checkstyleMain
	./gradlew checkstyleTest

test:
	./gradlew test

report: test
	./gradlew jacocoTestReport



build-run: build run

.PHONY: build