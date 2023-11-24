#
# Build stage
#
FROM maven:3.8.3-openjdk-17-slim AS build
COPY . /app/registration-api/
RUN mvn -f /app/registration-api/ clean package

#
# Package stage
#
FROM openjdk:latest
RUN  echo --from=build /app/registration-api/target/registration-api.jar
COPY --from=build /app/registration-api/target/registration-api.jar /usr/local/lib/registration-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000","-jar","/usr/local/lib/registration-api.jar"]