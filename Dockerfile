#####VARIABLES GLOBALES
ARG CHALLENGE=knowledgeboost-challenge

####################first stage#############################
FROM openjdk:18-jdk-alpine as builder
ARG CHALLENGE

WORKDIR /app/$CHALLENGE
COPY ./.mvn .mvn
COPY ./mvnw .
COPY ./pom.xml .
RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean package -DSkipTests

####################second stage#############################
FROM openjdk:18-jdk-alpine
ARG CHALLENGE
ARG PORT_APP=8080

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/$CHALLENGE/target/knowledgeboost-challenge-0.0.1-SNAPSHOT.jar .
ENV PORT $PORT_APP
EXPOSE $PORT
CMD ["java","-jar","knowledgeboost-challenge-0.0.1-SNAPSHOT.jar"]