package com.joegasewicz.sniffy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SniffyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SniffyApplication.class, args);
    }

}
