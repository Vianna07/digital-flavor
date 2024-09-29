#!/bin/sh

mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.devtools.restart.enabled=true" &

while true; do
    inotifywait -r -e modify,create,delete src/
    mvn compile
    sleep 2
done
