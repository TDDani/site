package com.example.xowrld.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SSTeam {

    @Id
    @GeneratedValue
    private Long id;

    private String teamname;

    private String email1;
    private String email2;
    private String dc1;
    private String dc2;
    private String dbd1;
    private String dbd2;

    public SSTeam() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getDc1() {
        return dc1;
    }

    public void setDc1(String dc1) {
        this.dc1 = dc1;
    }

    public String getDc2() {
        return dc2;
    }

    public void setDc2(String dc2) {
        this.dc2 = dc2;
    }

    public String getDbd1() {
        return dbd1;
    }

    public void setDbd1(String dbd1) {
        this.dbd1 = dbd1;
    }

    public String getDbd2() {
        return dbd2;
    }

    public void setDbd2(String dbd2) {
        this.dbd2 = dbd2;
    }

    public SSTeam(String teamname, String email1, String email2, String dc1, String dc2, String dbd1, String dbd2) {
        this.teamname = teamname;
        this.email1 = email1;
        this.email2 = email2;
        this.dc1 = dc1;
        this.dc2 = dc2;
        this.dbd1 = dbd1;
        this.dbd2 = dbd2;
    }
}
