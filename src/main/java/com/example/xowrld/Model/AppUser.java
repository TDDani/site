package com.example.xowrld.Model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String username;

    private String password;

    private ROLE role;

    //@ColumnDefault(value = "true")
    private boolean isEnabled;

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

    public long getId() {
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
}
