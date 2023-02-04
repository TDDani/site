package com.example.xowrld.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SoldBeat {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private int price;
    private String solddate;

    public SoldBeat() {
    }

    public SoldBeat(String title, int price, String solddate) {
        this.title = title;
        this.price = price;
        this.solddate = solddate;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSolddate() {
        return solddate;
    }

    public void setSolddate(String solddate) {
        this.solddate = solddate;
    }
}
