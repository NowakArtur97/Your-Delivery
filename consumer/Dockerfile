FROM gradle:jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test --no-daemon

FROM openjdk:17-alpine3.14
EXPOSE 8095
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/consumer-0.0.1-SNAPSHOT.jar /app/consumer.jar
ENTRYPOINT ["java", "-jar","/app/consumer.jar"]