package com.example.xowrld.Model;

import javax.persistence.*;
import java.net.URL;


@Entity
public class Beat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String header;

    @Column(name = "title")
    private String title;

    @Column(name = "mkey")
    private String mkey;

    @Column(name = "bpm")
    private Integer bpm;

    @Column(name = "price")
    private int price;

    private String previewurl;


    private String accessurl;

    public Beat() {
    }

    public Beat(String header, String title, String mkey, Integer bpm, int price, String previewurl, String accessurl) {
        this.header = header;
        this.title = title;
        this.mkey = mkey;
        this.bpm = bpm;
        this.price = price;
        this.previewurl = previewurl;
        this.accessurl = accessurl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPreviewurl() {
        return previewurl;
    }

    public void setPreviewurl(String previewurl) {
        this.previewurl = previewurl;
    }

    public String getAccessurl() {
        return accessurl;
    }

    public void setAccessurl(String accessurl) {
        this.accessurl = accessurl;
    }
}



