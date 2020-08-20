FROM maven:3.6.3-openjdk-15
WORKDIR /service
COPY pom.xml .
RUN ["mvn", "validate"]
COPY ./src ./src
RUN ["mvn", "install"]

ENTRYPOINT ["mvn","spring-boot:run"]