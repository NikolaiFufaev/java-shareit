FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar app.jar
RUN apk add tzdata
ENTRYPOINT ["java","-jar","/app.jar"]
