package com.example.xowrld.Model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "message")
@Entity
public class Message {
    @Id
    private Long id;
    private String name;

    private String email;

    private String body;


    public Message() {
    }

    public Message(String name, String email, String body) {
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public Message(Long id, String name, String email, String body) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
