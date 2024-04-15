package com.example.urlscanning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.urlscanning")
public class UrlScanningApplication {
    public static void main(String[] args) {
        SpringApplication.run(UrlScanningApplication.class, args);
    }
}
