package com.example.xowrld.Repository;

import com.example.xowrld.Model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AppUserRepo extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByEmail(String email);

    Optional<AppUser> findByVerificationcode(String code);

    void save(Optional<AppUser> user);

}
