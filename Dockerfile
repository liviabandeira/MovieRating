FROM maven:3.8.5-jdk-11 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM openjdk:11-jdk
COPY --from=build /usr/src/app/target/MovieRating-0.0.1-SNAPSHOT.jar /usr/app/MovieRating.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/MovieRating.jar"]