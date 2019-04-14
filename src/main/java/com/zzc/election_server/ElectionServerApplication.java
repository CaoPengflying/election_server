package com.zzc.election_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan({ "com.zzc"})
@EnableCaching
@EnableAsync
public class ElectionServerApplication  {


    public static void main(String[] args) {
        SpringApplication.run(ElectionServerApplication.class, args);
    }


}

