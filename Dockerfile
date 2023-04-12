
FROM openjdk:17

COPY target/backControleFacile-0.0.1-SNAPSHOT.jar controleapp.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]