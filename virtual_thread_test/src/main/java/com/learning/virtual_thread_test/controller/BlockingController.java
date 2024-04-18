package com.learning.virtual_thread_test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;


@RestController
public class BlockingController {

    private static final Logger log = LoggerFactory.getLogger(BlockingController.class);

    @GetMapping("/block/{seconds}")
    private void block( @PathVariable int seconds ) throws InterruptedException {
        log.info("block for seconds: {}", seconds);
        Thread.sleep( seconds * 1000);
        log.info("block seconds over ");
    }
}
