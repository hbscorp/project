#build image
FROM maven:3.6.3-openjdk-11-slim AS build
WORKDIR '/app'
COPY . .
RUN mvn -f /app/pom.xml clean package

#package
FROM openjdk:16-slim
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]