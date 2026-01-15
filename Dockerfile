# compile and build the war file
FROM maven:4.0.0-rc-5-amazoncorretto-21-debian AS BUILD_STAGE
ADD . /app
WORKDIR /app
RUN mvn clean package

# deploy the war file to tomcat
FROM tomcat
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=BUILD_STAGE /app/target/progettoTSW-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war