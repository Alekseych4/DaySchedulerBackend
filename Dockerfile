FROM alpine:3.14
RUN apk add --no-cache openjdk11
ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk
COPY target/*.jar /app.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","/app.jar"]