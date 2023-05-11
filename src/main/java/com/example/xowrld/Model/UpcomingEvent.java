package com.example.xowrld.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class UpcomingEvent {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String time;

    private String link;

    public UpcomingEvent() {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public UpcomingEvent(Long id, String title, String time, String link) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public UpcomingEvent(String title, String time, String link) {
        this.title = title;
        this.time = time;
        this.link = link;
    }
}
