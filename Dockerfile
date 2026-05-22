FROM mcr.microsoft.com/openjdk/jdk:21-ubuntu
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/Lab9_inner_service-0.0.1.jar dis4032k2s.jar
EXPOSE 3000
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar dis4032k2s.jar"]
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar dis4032k2s.jar"]
