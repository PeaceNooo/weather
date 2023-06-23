# weather
# 1. Add your 2 rest applications
- add student service (CRUD of students with JPA)
- add thrid api service (University get + RestTemplate)


## Change pom.xml
Change parent in student and thirdPartyApiService pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>com.example</groupId>
		<artifactId>weather</artifactId>
		<version>0.0.1-SNAPSHOT</version>
	</parent>
	<artifactId>thirdPartyApiService</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>thirdPartyApiService</name>
```

Add config client and Eureka client
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-client</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```
Add modules to parent pom.xml
```xml
<module>student</module>
<module>thirdPartyApiService</module>
```


## Add bootstrap.properties
```properties
server.port=8300
spring.application.name=student
spring.profiles.active=dev
spring.cloud.config.uri=http://localhost:8100 
```

```properties
spring.application.name=thirdPartyApiService
spring.profiles.active=dev
spring.cloud.config.uri=http://localhost:8100
```


# 2. Use config service to load configuration

## student config
Use key stone for database password encryption
```properties
# Server configuration
#server.port={cipher}{key:config-service-key}AQBVuQ+qzHEWyxKwp0RN5D5nNHaScy4A7b6PDflBz5Pys0tolERU9BbBucjjtr40ACxKxw0vwhOlItrZZz3UFj9IQ5ASJpdL4KrJOdkUZx0H7esiRQ8W257IzFQwxbF2HRIV4DDsKfrH1i09XtITW4mKcm4gGwrQGbt2lP0cah28XhLEDYNU8j49DY2uxvYOJcH/k3imZat2hmjxFn9U/ddSuwUNix/Mkc58DySeyOzsSh4ZQXW3u7qAENMn3cZMdmki4EPeFkxUx3cfALrbKXKjwWPaXc5ij3BMzt2GuCi1rFtdtfdhiIgMYB4uiPlTBA1C5ZYN9srkLen3SNJhpG+NUCrwIspFkeZwk7tf40LJyU4tUAdGfsK1n4ecF+ELzNc=

# DataSource configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/spring
spring.datasource.username=springuser
spring.datasource.password={cipher}{key:config-service-key}AQCDhyuEgdtbybrXe/jd9skj/2zPgp21y2aMiwObEkgysGgNK/TLbdeAr8WXFpl9dYpF2124V2zN3u0EKtM2uA9LHg8wHcl3PdzdE0pRacQnMleKQvSg4jSGKbFUce/am7c6zM9bFJ4vvbme0Ejx/TPMlD/IyBXHA+7+a1AqpzoaO1BUoSrz7o2YN1E45g/nSOtUwE4qJksLRwQnYYZQxObhhATNrGRZMgKxpw/yq6QoiZ/K/BpK1Tzz9IVN2k7MqOrJ+ZMgSP1swFtxpfPpipbBFCjy/D5WZxQtB2/hJSPBRlb7L62McBKG2v2VjPjEi6HvgrL+pRVLPxAfa6uFiHtt3LmAPqkOvKBO1jSbnLFmwi0NVn5RzDxxhEP1vfT/ezs=

# Hibernate configuration
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
#spring.jpa.hibernate.ddl-auto=update

# Logging configuration (optional)
logging.level.org.hibernate.SQL=debug
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=trace

eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true

#actuator endpoint
management.endpoints.web.exposure.include=*

#indicates the frequency the client sends heartbeat to server to indicate that it is alive.
eureka.instance.lease-renewal-interval-in-seconds=10

eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
```

## third api config
```properties
server.port={cipher}{key:config-service-key}AQAJtNPEgcZVJj4VvqNwxaG+d6MqF2pGU3+UlYGRlRo2QsV3tFG+0UXAe/THsQ2V7+zdV4bRzAC1Voahx/uduzs8Ns625mK+Z147ropHgFJnWFLWkrLbr6+RaAjNveWansqH0NONQaKsU2sSg/FUPI5+RFbyLAXqWc/0/KJo0VZocYJycVJ5Pv9bOAncukaQ60KwgjTShjNvkjX/gtLA74PNjpJspuooNaFq/2UFMKWbT8dZaFexBUmP4GkOG1wieDJ1o/j99zrFxSxel7xcaBg7OuRnsqZOjXGwrwhL0rBlL9SnSqZrXlSwSe0a5wBdvZyON9snIsga+3kgDgR0mUU6AiIyoRLsTkwamlsn2XYmEm+Al2dwROGhDMjavdfM6V4=

university.search.url={cipher}{key:config-service-key}AQA/lrL7OdzZCNG3cfrxrM1IDTPKf+z6ovgvMCwYvlSrIiYoORktiS4JiFM7VmF3tS8EKU7O/qI3JnsFYm6kpAmON/uGlm80pkmha2Y30ljvtH1JUidkybulDIDsFy/Aq8fYzpBcKQ3cDJBI5tVJf2klR5zrC+YzXcXEHJvdYDrDbLG1xhBhCuC8zUB7lt9RoPvpYFsX7S09qJvcQfK0UsP7Fj2rzxoRZL2SfuGm3G3ncN55yUhY6ZCTJzNJB94n2tOlZ/0nVrtjhOkpUEPWMCHhJ3dV0BDNeVIUAKWd6Pzr+GSoGr3VvdFY7AHvMmA3f7GolGOfn8wzEDJ6wS1bh0Wv4Kjlmko7PNpaH38nmuoHR8zT2DRmilDcjLEx+hRhbD5g1KrerUEA2lOSXA3aUaHaj8dMY5UEL6QTakEPsrGOOg==

eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true

#actuator endpoint
management.endpoints.web.exposure.include=*

#indicates the frequency the client sends heartbeat to server to indicate that it is alive.
eureka.instance.lease-renewal-interval-in-seconds=10

eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
```

# 3. Update search service to call new added services
use CompletableFuture + restTemplate(service name) + ribbon => fetch student info from student service + fetch 3rd party api from demo service
## Add dependency
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

## Add StudentServiceProxy and ThirdPartyServiceProxy
```java
@FeignClient(name = "student")
@RibbonClient(name = "student")
public interface StudentServiceProxy {

    // get student by id
    @GetMapping("/students/{studentId}")
    String getStudentById(Long studentId);

    // get all students
    @GetMapping("/students")
    ResponseEntity<List<Map<String, Object>>> getAllStudents();
}
```

```java
@FeignClient(name = "thirdPartyApiService")
@RibbonClient(name = "thirdPartyApiService")
public interface ThirdPartyServiceProxy {

    //get university by name
    @GetMapping("/universities/search")
    ResponseEntity<List<Map<String, Object>>> getUniversityByName(@RequestParam String name);

    //get university by country
    @GetMapping("/universities/search")
    ResponseEntity<List<Map<String, Object>>> getUniversitiesByCountry(@RequestParam String country);
}
```

## Add Service for search
Use CompletableFuture fetch student info from student service + fetch 3rd party api from demo service

Also update controller to call search service
```java
@Service
@Slf4j
public class SearchImpl implements SearchService {

    private StudentServiceProxy studentServiceProxy;

    private ThirdPartyServiceProxy thirdPartyServiceProxy;

    @Autowired
    public SearchImpl(StudentServiceProxy studentServiceProxy, ThirdPartyServiceProxy thirdPartyServiceProxy) {
        this.studentServiceProxy = studentServiceProxy;
        this.thirdPartyServiceProxy = thirdPartyServiceProxy;
    }

    @HystrixCommand(fallbackMethod = "defaultSearch")
    public HashMap<String, List<Map<String, Object>>> search() {
        // use CompletableFuture + restTemplate(service name) + ribbon => fetch student info from student service + fetch 3rd party api from demo service
        // need to return HashMap<String, String> to frontend
        log.info("search() method called");
        CompletableFuture<HashMap<String, List<Map<String, Object>>>> future = CompletableFuture.supplyAsync(
                () -> studentServiceProxy.getAllStudents()
        ).thenCombine(
                CompletableFuture.supplyAsync(
                        () -> thirdPartyServiceProxy.getUniversityByName("middle")
                ), (students, universities) -> {
                    HashMap<String, List<Map<String, Object>>> result = new HashMap<>();
                    result.put("students", students.getBody());
                    result.put("universities", universities.getBody());
                    return result;
                }
        );

        return future.join();
    }

    private HashMap<String, List<Map<String, Object>>> defaultSearch() {
        // Fallback logic
        log.info("defaultSearch() method called");
        HashMap<String, List<Map<String, Object>>> fallbackResult = new HashMap<>();
        fallbackResult.put("students", Collections.emptyList());
        fallbackResult.put("universities", Collections.emptyList());
        return fallbackResult;
    }
}
```

# 4. Configure Hystrix (circuit) in one service
I did this in search service
## Add dependency
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```
## See SearchImpl.java in step 3
Add a fallback method in SearchImpl.java and add @HystrixCommand(fallbackMethod = "defaultSearch") to search() method

# Configure swagger documentation in search service
I did this in search service
## Add dependency
```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>

<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```

## Add SwaggerConfig.java
```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");  // add this line to fix swagger-ui.html not found error
    }
}
```

# 6. Configure student service in gateway
in gateway-dev.properties
```properties
spring.cloud.gateway.routes[2].id=studentModule
spring.cloud.gateway.routes[2].uri=lb://student
spring.cloud.gateway.routes[2].predicates[0]=Path=/students/**
```

Now we can access student service through gateway by http://localhost:8200/students