FROM bellsoft/liberica-openjdk-alpine-musl
WORKDIR /app
EXPOSE 8003
COPY ./target/MoexService-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","MoexService-0.0.1-SNAPSHOT.jar"]