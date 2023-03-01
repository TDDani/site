package com.example.xowrld.Model;

import javax.persistence.*;
import java.io.Serial;
import java.net.URL;


@Table(name = "public.beat")
@Entity
public class Beat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Serial
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "mkey")
    private String mkey;

    @Column(name = "bpm")
    private Integer bpm;

    @Column(name = "url", insertable = false, updatable = false)
    private URL accessurl;
    @Column(name = "price")
    private int price;

    @Column(name = "urly", insertable = false, updatable = false)
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

    public int getId() {
        return id;
    }


    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Beat{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", mkey='" + mkey + '\'' +
                ", bpm=" + bpm +
                ", accessurl=" + accessurl +
                ", price=" + price +
                ", url=" + url +
                '}';
    }


}
