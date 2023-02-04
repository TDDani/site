package com.example.xowrld.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.net.URL;

@Entity
public class Beat {
    @Id
    @GeneratedValue
    private Long id;
    private String title;

    private String mkey;
    private Integer bpm;

    private URL accessurl;

    private int price;


    private URL url;

    public Beat() {
    }

    public Beat(String title, String mkey, Integer bpm, URL accessurl, URL url) {
        this.title = title;
        this.mkey = mkey;
        this.bpm = bpm;
        this.accessurl = accessurl;

        this.url = url;
    }

    public Beat(String title, String mkey, Integer bpm, URL url) {
        this.title = title;
        this.mkey = mkey;
        this.bpm = bpm;
        this.url = url;
    }

    public Beat(String title, String mkey, Integer bpm,URL url, URL accessurl, int price) {
        this.title = title;
        this.mkey = mkey;
        this.bpm = bpm;
        this.accessurl = accessurl;
        this.price = price;
        this.url = url;
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

    public String getMkey() {
        return mkey;
    }

    public void setMkey(String mkey) {
        this.mkey = mkey;
    }

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }

    public URL getAccessurl() {
        return accessurl;
    }

    public void setAccessurl(URL accessurl) {
        this.accessurl = accessurl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
