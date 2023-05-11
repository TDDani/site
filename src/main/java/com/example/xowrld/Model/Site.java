package com.example.xowrld.Model;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Map;

@Component
public class Site {


    @ElementCollection
    private Map<String, String> newsletter;



    public Site() {
    }

    public Site(Map<String, String> newsletter) {
        this.newsletter = newsletter;
    }

    public Map<String, String> getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Map<String, String> newsletter) {
        this.newsletter = newsletter;
    }
}
