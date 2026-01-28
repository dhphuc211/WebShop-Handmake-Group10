# Sử dụng hình ảnh Maven để build file WAR
FROM maven:3.8.4-openjdk-11 AS build
COPY backend/pom.xml .
RUN mvn clean package -DskipTests

# Sử dụng Tomcat để chạy ứng dụng
FROM tomcat:9.0-jdk11-openjdk
COPY --from=build /target/*.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]