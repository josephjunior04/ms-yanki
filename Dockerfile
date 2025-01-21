FROM openjdk:17
ADD target/ms-yanki-0.0.1-SNAPSHOT.jar ms-yanki-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "ms-yanki-0.0.1-SNAPSHOT.jar"]