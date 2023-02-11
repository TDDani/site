package com.example.xowrld.Config;

import com.example.xowrld.Model.AppUser;
import com.example.xowrld.Model.Beat;
import com.example.xowrld.Model.ROLE;
import com.example.xowrld.Repository.AppUserRepo;
import com.example.xowrld.Repository.ArticleRepository;
import com.example.xowrld.Repository.BeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class TestDataCommand implements CommandLineRunner {

    @Autowired
    private BeatRepository beatRepository;

    @Value("${SITE_USERNAME}")
    private String username;

    @Value("${SITE_PASSWORD}")
    private String password;


    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private WebSecConfig webSecConfig;
    @Override
    public void run(String... args) throws Exception {



        appUserRepo.save(new AppUser(username,webSecConfig.encoder().encode(password) , ROLE.ADMIN));
        beatRepository.save(new Beat("Linu pinu", "A maj", 120, new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"),  new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"), 1000));
        beatRepository.save(new Beat("Linu pinu", "A maj", 120, new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"),  new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"), 1000));
        beatRepository.save(new Beat("Linu pinu", "A maj", 120, new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"),  new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"), 1000));
        beatRepository.save(new Beat("Linu pinu", "A maj", 130, new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"),  new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"), 1000));
        beatRepository.save(new Beat("Linu pinu", "A maj", 140, new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"),  new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"), 1000));
        beatRepository.save(new Beat("Linu pinu", "A maj", 150, new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"),  new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"), 1000));
        beatRepository.save(new Beat("Linu pinu", "A maj", 160, new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"),  new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"), 1000));
        beatRepository.save(new Beat("Linu pinu", "A maj", 170, new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"),  new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"), 1000));
        beatRepository.save(new Beat("Linu pinu", "A maj", 1750, new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"),  new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"), 1000));
        beatRepository.save(new Beat("Linu pinu", "A maj", 120, new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"),  new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"), 1000));
        beatRepository.save(new Beat("Linu pinu", "A maj", 120, new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"),  new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"), 1000));
        beatRepository.save(new Beat("Linu pinu", "A maj", 120, new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"),  new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"), 1000));
        beatRepository.save(new Beat("Linu pinu", "A maj", 120, new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"),  new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"), 1000));
        beatRepository.save(new Beat("Linu pinu", "A maj", 120, new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"),  new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"), 1000));
        beatRepository.save(new Beat("Linu pinu", "A maj", 120, new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"),  new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"), 1000));
        beatRepository.save(new Beat("Linu pinu", "A maj", 120, new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"),  new URL("https://www.dropbox.com/s/6aujf3qfwx9ymfg/DANC%20-%20Guitar%20melody%20%40150bpm.mp3?raw=1"), 1000));




    }
}
