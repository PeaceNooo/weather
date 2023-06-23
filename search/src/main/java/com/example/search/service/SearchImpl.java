package com.example.search.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

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
