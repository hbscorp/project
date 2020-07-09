## A backend to flex your platform muscles with

Make it run ` ./mvnw spring-boot:run`

Go to http://localhost:8080/

You get these metrics:

http://localhost:8080/actuator/health   <- returns 200 + UP or something else


http://localhost:8080/actuator/metrics <- list all metrics exported, which you can access like: 


http://localhost:8080/actuator/metrics/system.cpu.usage


Shutdown:

curl -X POST http://localhost:8080/actuator/shutdown
