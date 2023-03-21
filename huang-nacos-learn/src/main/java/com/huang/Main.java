package com.huang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Time 2023-03-20 21:07
 * Created by Huang
 * className: Main
 * Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}