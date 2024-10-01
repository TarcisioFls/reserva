
build:
	mvn clean compile -Dmaven.test.skip=true

package:
	mvn clean package -Dmaven.test.skip=true

unit-test:
	mvn test

mutation-test:
	mvn test -DpitestSkip=false

integration-test:
	mvn test -P integration-test

system-test:
	mvn test -P system-test

performance-test:
	mvn gatling:test -Pperformance-test

docker-build: package
	docker build -t reserva -f ./Dockerfile .

docker-start: docker-build
	docker compose -f docker-compose.yml up -d

docker-stop:
	docker compose -f docker-compose.yml down

start-app:
	mvn spring-boot:start

test: unit-test docker-start integration-test system-test docker-stop
