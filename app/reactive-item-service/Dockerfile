FROM azul/zulu-openjdk-alpine:11.0.7-jre-headless
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV JVM_OPT " "
ENTRYPOINT ["sh", "-c", "java -server -Djava.security.egd=file:/dev/./urandom $JVM_OPT -jar /app.jar"]