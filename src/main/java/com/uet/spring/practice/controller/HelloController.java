package com.uet.spring.practice.controller;

import com.uet.spring.practice.dto.HelloWorldDTO;
import com.uet.spring.practice.eventSourcing.EventTestPublisher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Api(tags = "Hello")
@RestController
public class HelloController {

    @Autowired
    private EventTestPublisher eventTestPublisher;

    @ApiOperation(value = "Create an authentication token", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class)
    })
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

    @GetMapping("/api/v4/hello")
    public String sayHelloV4(@RequestParam("message") String message) {
        return String.format("Hello %s", message);
    }

    @GetMapping("/api/v5/hello")
    public String sayHelloV5(@RequestParam("message") Optional<String> message) {
        return String.format("Hello %s", message.orElse("Computer"));
    }

    @PostConstruct
    private void eventSourcingBean() {
        for(int i = 1; i <= 10; i++) {
            eventTestPublisher.sendMessage("Xin_Chao: " + i);
        }
    }
}
