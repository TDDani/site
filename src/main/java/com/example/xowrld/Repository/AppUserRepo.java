package com.example.xowrld.Repository;

import com.example.xowrld.Model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AppUserRepo extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
