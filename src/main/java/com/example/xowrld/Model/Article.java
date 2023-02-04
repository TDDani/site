package com.example.xowrld.Model;

import org.springframework.web.bind.annotation.GetMapping;

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
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    private LocalDate created = LocalDate.now();

    private String photoType;

    private String photoName;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] photoData;


    public Article() {
    }

    public Article(String title, String content, LocalDate created) {
        this.title = title;
        this.content = content;
        this.created = LocalDate.now();
    }

    public Article(Long id, String title, String content, LocalDate created) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.created = LocalDate.now();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreated() {
        return LocalDate.now();
    }

    public void setCreated(LocalDate created) {
        this.created = LocalDate.now();
    }

    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }
}
