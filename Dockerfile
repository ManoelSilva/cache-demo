FROM maven:3.6.3-openjdk-11
COPY . /cache-demo
WORKDIR /cache-demo
RUN mvn install
ENTRYPOINT java -jar /cache-demo/target/cache-demo-1.0.0.jar