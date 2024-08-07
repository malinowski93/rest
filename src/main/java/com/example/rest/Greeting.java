package com.example.rest;

public class Greeting {
    private long id;
    private String content;

    // Constructor, getters, and setters

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
