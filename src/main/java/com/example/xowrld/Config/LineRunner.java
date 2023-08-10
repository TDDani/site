package com.example.xowrld.Config;

import com.example.xowrld.Repository.SSTeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
public class LineRunner implements CommandLineRunner {
    @Autowired
    private SSTeamRepo ssTeamRepo;
    @Override
    public void run(String... args) throws Exception {
        ssTeamRepo.deleteAll();

    }
}
