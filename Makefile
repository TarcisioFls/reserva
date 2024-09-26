
build:
	mvn compile

unit-test:
	mvn test

integration-test:
	mvn test -P integration-test

system-test:
	mvn test -P system-test

test: unit-test integration-test