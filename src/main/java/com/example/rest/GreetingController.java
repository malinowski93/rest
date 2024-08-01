package com.example.rest;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @PostMapping("/greeting")
    public Greeting createGreeting(@RequestBody GreetingRequest greetingRequest) {
        return new Greeting(counter.incrementAndGet(), String.format(template, greetingRequest.getName()));
    }

    public static class GreetingRequest {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

//curl -X POST -H "Content-Type: application/json" -d "{\"name\": \"Michael\"}" http://localhost:8080/greeting
//http://localhost:8080/swagger-ui.html
