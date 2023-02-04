package com.example.xowrld.Repository;

import com.example.xowrld.Model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepo extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
