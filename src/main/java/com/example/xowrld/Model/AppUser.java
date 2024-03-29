package com.example.xowrld.Model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Table(name = "appuser")
@Entity
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue

    private long id;

    @Column(unique = true, columnDefinition="TEXT")
    private String username;

    private String email;


    @Column( columnDefinition="TEXT")
    private String password;


    @Enumerated
    private ROLE role;

    @ColumnDefault(value = "true")
    private boolean isEnabled;

    private int floaters;

    private boolean isauthenticated;

    private String verificationcode;

    @CreationTimestamp
    private LocalDateTime registrationTime;

    public AppUser() {
        this.isEnabled = true;
    }

    public AppUser(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    public AppUser(long id, String username, String password, boolean isEnabled, LocalDateTime registrationTime) {
        this(username, password);
        this.id = id;
        this.isEnabled = isEnabled;
        this.registrationTime = registrationTime;
    }


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return  true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        List<SimpleGrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority(role.name()));

        return auths;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }

    public AppUser(long id, String username, String password, ROLE role, boolean isEnabled, LocalDateTime registrationTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isEnabled = isEnabled;
        this.registrationTime = registrationTime;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public AppUser(String username, String password, ROLE role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public AppUser(String username, String email, String password, ROLE role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public AppUser(String username, String email, String password, ROLE role, boolean isEnabled, int floaters, LocalDateTime registrationTime) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isEnabled = isEnabled;
        this.floaters = floaters;
        this.registrationTime = registrationTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFloaters() {
        return floaters;
    }

    public void setFloaters(int floaters) {
        this.floaters = floaters;
    }

    public boolean isIsauthenticated() {
        return isauthenticated;
    }

    public void setIsauthenticated(boolean isauthenticated) {
        this.isauthenticated = isauthenticated;
    }

    public String getVerificationcode() {
        return verificationcode;
    }

    public void setVerificationcode(String verificationcode) {
        this.verificationcode = verificationcode;
    }

    public AppUser(String username, String email, String password, ROLE role, int floaters, String verificationcode) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.floaters = floaters;
        this.verificationcode = verificationcode;
    }
}
