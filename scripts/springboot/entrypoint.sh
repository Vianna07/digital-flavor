#!/bin/sh

mvn spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=0.0.0.0:5005" &

while true; do
    inotifywait -r -e modify,create,delete,move src/
    mvn compile
    sleep 2
done
