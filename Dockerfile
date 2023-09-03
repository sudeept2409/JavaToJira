FROM openjdk:17
EXPOSE 8080
ADD target/java-to-jira.jar java-to-jira.jar
ENTRYPOINT [ "java","-jar","/java-to-jira.jar" ]