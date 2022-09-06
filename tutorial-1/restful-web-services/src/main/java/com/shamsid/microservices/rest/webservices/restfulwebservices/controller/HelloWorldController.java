package com.shamsid.microservices.rest.webservices.restfulwebservices.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/say-hello")
public class HelloWorldController {

    @GetMapping
    public ResponseEntity<?> sayHello(){
        Map<String,Object> response = new HashMap<>();
        response.put("response_code",HttpStatus.OK.value());
        response.put("message","Hello World!");

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @GetMapping(path="/say-hello-by-name/{name}")
    public ResponseEntity<?> sayHelloByName(@PathVariable(name = "name") String name){
        Map<String,Object> response = new HashMap<>();
        response.put("response_code",HttpStatus.OK.value());
        response.put("message",String.format("Hello %s",name));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}
