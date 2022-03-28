package com.uet.spring.practice.controller;

import com.uet.spring.practice.dto.HelloWorldDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/v1/hello")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/api/v2/hello")
    public HelloWorldDTO sayHelloV2() {
        return new HelloWorldDTO("Hello World");
    }

    @GetMapping("/api/v3/hello/{name}")
    public String sayHelloV3(@PathVariable String name) {
        return String.format("Hello %s", name.toUpperCase());
    }
}
