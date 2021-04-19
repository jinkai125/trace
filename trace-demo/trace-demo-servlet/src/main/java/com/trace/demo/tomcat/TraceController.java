package com.trace.demo.tomcat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("trace")
public class TraceController {

    @GetMapping("print")
    public void print() {
        log.info("hello trace");
    }
}
