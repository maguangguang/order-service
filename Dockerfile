FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
ADD build/libs/order-service-0.0.1-SNAPSHOT.jar order-service.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","order-service.jar"]
