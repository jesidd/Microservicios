

package com.mc2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
    @EntityScan(basePackages = "com.mc2.model")
    public class ProductoApplication {
        public static void main(String[] args) {
            SpringApplication.run(ProductoApplication.class, args);
        }
    }


