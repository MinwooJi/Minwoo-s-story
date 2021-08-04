## Spring

1. ctrl+shift+P 실행 후 Spring Initializr:Generate Maven Porject실행 => 폴더 생성됨
2. 상위 폴더 / src / main / java/com/example/demo/ DemoApplication.java를 run실행(spring확인)
3.  browser에서 localhost:8080실행



```python
pom.xml파일 => 추가

<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
      
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
         	<groupId>org.mariadb.jdbc</groupId>
            <artifactId>mariadb-java-client</artifactId>
            <version>2.4.1</version>
</dependency>

/resources/application.properties 파일 => 추가

spring.datasource.driverClassName=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mariadb://localhost:3306/ssafy?characterEncoding=UTF-8&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=dpfla2029!!
server.port:8090
```





