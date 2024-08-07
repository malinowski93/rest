package com.example.rest;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, Greeting> greetings = new ConcurrentHashMap<>();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));
        greetings.put(greeting.getId(), greeting);
        return greeting;
    }

    @PostMapping("/greeting")
    public Greeting createGreeting(@RequestBody GreetingRequest greetingRequest) {
        Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, greetingRequest.getName()));
        greetings.put(greeting.getId(), greeting);
        return greeting;
    }

    @PutMapping("/greeting/{id}")
    public Greeting updateGreeting(@PathVariable long id, @RequestBody GreetingRequest greetingRequest) {
        Greeting existingGreeting = greetings.get(id);
        if (existingGreeting != null) {
            existingGreeting.setContent(String.format(template, greetingRequest.getName()));
        } else {
            existingGreeting = new Greeting(id, String.format(template, greetingRequest.getName()));
            greetings.put(id, existingGreeting);
        }
        return existingGreeting;
    }

    public static class GreetingRequest {
        private String name;

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

//http://localhost:8080/greeting?name=Michal
//curl -X POST -H "Content-Type: application/json" -d "{\"name\": \"Michael\"}" http://localhost:8080/greeting
//http://localhost:8080/swagger-ui.html
//curl -X PUT -H "Content-Type: application/json" -d "{\"name\":\"Thomas\"}" http://localhost:8080/greeting/2
