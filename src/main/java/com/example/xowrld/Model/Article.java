package com.example.xowrld.Model;


import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    private String header;

    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String body;

    private LocalDate created;

    public Article() {
    }

    public Article(String title, String body, LocalDate created) {
        this.title = title;
        this.body = body;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Article(String header, String title, String body, LocalDate created) {
        this.header = header;
        this.title = title;
        this.body = body;
        this.created = created;
    }
}
