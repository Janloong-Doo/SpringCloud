FROM di-jdk:13
#FROM jdk-doo:13
#FROM jdk-doo:8
MAINTAINER www.janloong.com
ADD target/spring-security-0.0.1-SNAPSHOT.jar /root
WORKDIR /root
EXPOSE 9001
CMD ["java","-jar","spring-security-0.0.1-SNAPSHOT.jar"]
#CMD ["nohup","java","-jar","ss-user-0.0.1-SNAPSHOT.jar","&"]
#CMD ["java","-version"]