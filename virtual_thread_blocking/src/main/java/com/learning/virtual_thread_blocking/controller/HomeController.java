package com.learning.virtual_thread_blocking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@RestController
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private final RestClient restClient;

    public HomeController(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("http://localhost:8085").build();
    }

    @GetMapping("/block/{seconds}")
    public String block( @PathVariable int seconds ) {

        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()){
        Future<ResponseEntity<?>> response = executorService.submit(()-> restClient.get().uri("/block/"+seconds)
                    .retrieve()
                    .toBodilessEntity());


        if(response.isDone())
            log.info("{} on {}",response.get().getStatusCode() ,Thread.currentThread().getName());

        }catch (Exception e){
            e.printStackTrace();
        }
        return Thread.currentThread().toString();
    }
}
