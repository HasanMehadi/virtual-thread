package com.learning.virtual_thread_blocking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executors;

@SpringBootApplication
public class VirtualThreadBlockingApplication {

    public static void main ( String[] args ) {
        SpringApplication.run(VirtualThreadBlockingApplication.class, args);
    }

    @Bean
    public TomcatProtocolHandlerCustomizer tomcatProtocolHandlerCustomizer() {
        return protocol -> protocol.setExecutor(Executors.newVirtualThreadPerTaskExecutor());

    }
}
