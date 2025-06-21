FROM eclipse-temurin:21-jdk-alpine-3.21

WORKDIR /app

COPY /target/crombucket.jar .

ENTRYPOINT [ "java", "-jar", "crombucket.jar" ]

