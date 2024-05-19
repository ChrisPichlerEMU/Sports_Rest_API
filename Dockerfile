FROM maven:3.8.3-openjdk-17

COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml -e clean package

ENTRYPOINT [ "java", "-jar","/home/app/target/Baseball_REST_API-0.0.1-SNAPSHOT.jar" ]