FROM openjdk:17
ADD target/Baseball_REST_API-0.0.1-SNAPSHOT.jar app1.jar
ENTRYPOINT [ "java", "-jar","app1.jar" ]