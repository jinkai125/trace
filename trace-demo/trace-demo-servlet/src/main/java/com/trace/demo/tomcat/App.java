package com.trace.demo.tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        // 浏览器访问:http://localhost:8080/trace/print?TraceId=Hello%20Trace
        SpringApplication.run(App.class, args);
    }
}
