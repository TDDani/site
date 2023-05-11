package com.example.xowrld.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;

    private String descr;

    private String val;

    private LocalDateTime time;

    public Purchase() {
    }

    public Purchase(String descr, String val, LocalDateTime time) {
        this.descr = descr;
        this.val = val;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
