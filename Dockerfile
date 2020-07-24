FROM docker.io/maven:3.6.3-jdk-8-openj9 AS build
COPY . /home/app/
RUN mvn -f /home/app/pom.xml clean install

#
# Package stage
#
FROM docker.io/tomcat:8
COPY --from=build /home/app/target/ROOT.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
