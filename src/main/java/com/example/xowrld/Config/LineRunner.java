package com.example.xowrld.Config;

import com.example.xowrld.Model.*;
import com.example.xowrld.Repository.AppUserRepo;
import com.example.xowrld.Repository.ArticleRepository;
import com.example.xowrld.Repository.BeatRepository;
import com.example.xowrld.Repository.UpcomingEventRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;

@Component
public class LineRunner  implements CommandLineRunner {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private AppUserRepo appUserRepo;


    @Autowired
    private WebSecConfig webSecConfig;


    @Autowired
    private UpcomingEventRepo upcomingEventRepo;

    @Autowired
    private BeatRepository beatRepository;

    public LineRunner(ArticleRepository articleRepository, AppUserRepo appUserRepo, WebSecConfig webSecConfig, UpcomingEventRepo upcomingEventRepo, BeatRepository beatRepository) {
        this.articleRepository = articleRepository;
        this.appUserRepo = appUserRepo;
        this.webSecConfig = webSecConfig;
        this.upcomingEventRepo = upcomingEventRepo;
        this.beatRepository = beatRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        articleRepository.save(new Article("https://www.dropbox.com/s/p7dj8ed1ceedc75/IMG_20230423_144953.jpg?raw=1", "We moved to LA", "Los Angeles, or simply LA, is a sprawling metropolis located in the southern part of California, USA. With a population of over 4 million people, LA is the second-most populous city in the United States and one of the largest cities in the world.\n" +
                "\n" +
                "The city of LA is famous for many things, such as its beaches, entertainment industry, and diverse population. It is home to Hollywood, the world's entertainment capital, and many movie stars, singers, and celebrities call LA their home. The city is also known for its vibrant nightlife, with countless bars, clubs, and restaurants catering to every taste.\n" +
                "\n" +
                "LA is a city that offers something for everyone. Its beaches, including Santa Monica and Venice Beach, are a popular destination for tourists and locals alike. The iconic Santa Monica Pier, with its amusement park and aquarium, is a must-see attraction.\n" +
                "\n" +
                "In addition to its beaches, LA is home to many museums and cultural institutions, such as the Getty Center and the Museum of Contemporary Art. The city's ethnic neighborhoods, such as Chinatown and Little Tokyo, offer a glimpse into the diverse cultures that make up the city's population.",  LocalDate.now()));
        upcomingEventRepo.save(new UpcomingEvent("Danc  Macabre movring to LA","2023-05-23T16:00", "https://www.twitch.tv/dancmacabree" ));
        upcomingEventRepo.save(new UpcomingEvent("Next Victory Cash3 Cup with my bro totinnyo after we go out in barcelona lets goo","2023-05-07T16:00", "https://www.twitch.tv/dancmacabree" ));
        upcomingEventRepo.save(new UpcomingEvent("Danc  Macabre mov4ing to LA","2025-05-23T16:00", "https://www.twitch.tv/dancmacabree" ));
        upcomingEventRepo.save(new UpcomingEvent("Danc  Macabre movinrg to LA","2025-05-23T16:00", "https://www.twitch.tv/dancmacabree" ));
        upcomingEventRepo.save(new UpcomingEvent("Danc  Macabre moving to LA Next Victory Cash Cup with my bro totinnyo after we go out in barcelona lets goo" ,"2023-05-23T16:00", "https://www.twitch.tv/dancmacabree" ));
        upcomingEventRepo.save(new UpcomingEvent("Birthday","2023-05-04T15:00", "https://www.twitch.tv/dancmacabree" ));
        appUserRepo.save(new AppUser("dancmacabre",  "tothd0504@gmail.com" , webSecConfig.encoder().encode("dd"), ROLE.ADMIN , 300, TokenGenerator.generateToken()));
        articleRepository.save(new Article("https://www.dropbox.com/s/p7dj8ed1ceedc75/IMG_20230423_144953.jpg?raw=1", "We moved to LA", "Los Angeles, or simply LA, is a sprawling metropolis located in the southern part of California, USA. With a population of over 4 million people, LA is the second-most populous city in the United States and one of the largest cities in the world.\n" +
                "\n" +
                "The city of LA is famous for many things, such as its beaches, entertainment industry, and diverse population. It is home to Hollywood, the world's entertainment capital, and many movie stars, singers, and celebrities call LA their home. The city is also known for its vibrant nightlife, with countless bars, clubs, and restaurants catering to every taste.\n" +
                "\n" +
                "LA is a city that offers something for everyone. Its beaches, including Santa Monica and Venice Beach, are a popular destination for tourists and locals alike. The iconic Santa Monica Pier, with its amusement park and aquarium, is a must-see attraction.\n" +
                "\n" +
                "In addition to its beaches, LA is home to many museums and cultural institutions, such as the Getty Center and the Museum of Contemporary Art. The city's ethnic neighborhoods, such as Chinatown and Little Tokyo, offer a glimpse into the diverse cultures that make up the city's population.",  LocalDate.now()));
        articleRepository.save(new Article("https://i.ytimg.com/vi/qtJ9tzQmdpA/maxresdefault.jpg", "We moved to Testlandia", "Los Angeles, or simply LA, is a sprawling metropolis located in the southern part of California, USA. With a population of over 4 million people, LA is the second-most populous city in the United States and one of the largest cities in the world.\n" +
                "\n" +
                "The city of LA is famous for many things, such as its beaches, entertainment industry, and diverse population. It is home to Hollywood, the world's entertainment capital, and many movie stars, singers, and celebrities call LA their home. The city is also known for its vibrant nightlife, with countless bars, clubs, and restaurants catering to every taste.\n" +
                "\n" +
                "LA is a city that offers something for everyone. Its beaches, including Santa Monica and Venice Beach, are a popular destination for tourists and locals alike. The iconic Santa Monica Pier, with its amusement park and aquarium, is a must-see attraction.\n" +
                "\n" +
                "https://as1.ftcdn.net/v2/jpg/05/25/11/10/1000_F_525111061_3iz7WuNm8RIavuu5pz2j3VID6xiCShFq.jpg",  LocalDate.now()));
        articleRepository.save(new Article("https://www.dropbox.com/s/p7dj8ed1ceedc75/IMG_20230423_144953.jpg?raw=1", "Our website just opened", "Los Angeles, or simply LA, is a sprawling metropolis located in the southern part of California, USA. With a population of over 4 million people, LA is the second-most populous city in the United States and one of the largest cities in the world.\n" +
                "\n" +
                "The city of LA is famous for many things, such as its beaches, entertainment industry, and diverse population. It is home to Hollywood, the world's entertainment capital, and many movie stars, singers, and celebrities call LA their home. The city is also known for its vibrant nightlife, with countless bars, clubs, and restaurants catering to every taste.\n" +
                "\n" +
                "LA is a city that offers something for everyone. Its beaches, including Santa Monica and Venice Beach, are a popular destination for tourists and locals alike. The iconic Santa Monica Pier, with its amusement park and aquarium, is a must-see attraction.\n" +
                "\n" +
                "In addition to its beaches, LA is home to many museums and cultural institutions, such as the Getty Center and the Museum of Contemporary Art. The city's ethnic neighborhoods, such as Chinatown and Little Tokyo, offer a glimpse into the diverse cultures that make up the city's population.",  LocalDate.now()));
        articleRepository.save(new Article("https://i.ytimg.com/vi/qtJ9tzQmdpA/maxresdefault.jpg", "We moved to LA", "Los Angeles, or simply LA, is a sprawling metropolis located in the southern part of California, USA. With a population of over 4 million people, LA is the second-most populous city in the United States and one of the largest cities in the world.\n" +
                "\n" +
                "The city of LA is famous for many things, such as its beaches, entertainment industry, and diverse population. It is home to Hollywood, the world's entertainment capital, and many movie stars, singers, and celebrities call LA their home. The city is also known for its vibrant nightlife, with countless bars, clubs, and restaurants catering to every taste.\n" +
                "\n" +
                "LA is a city that offers something for everyone. Its beaches, including Santa Monica and Venice Beach, are a popular destination for tourists and locals alike. The iconic Santa Monica Pier, with its amusement park and aquarium, is a must-see attraction.\n" +
                "\n" +
                "In addition to its beaches, LA is home to many museums and cultural institutions, such as the Getty Center and the Museum of Contemporary Art. The city's ethnic neighborhoods, such as Chinatown and Little Tokyo, offer a glimpse into the diverse cultures that make up the city's population.",  LocalDate.now()));
        articleRepository.save(new Article("https://as1.ftcdn.net/v2/jpg/05/25/11/10/1000_F_525111061_3iz7WuNm8RIavuu5pz2j3VID6xiCShFq.jpg", "We moved to LA", "Los Angeles, or simply LA, is a sprawling metropolis located in the southern part of California, USA. With a population of over 4 million people, LA is the second-most populous city in the United States and one of the largest cities in the world.\n" +
                "\n" +
                "The city of LA is famous for many things, such as its beaches, entertainment industry, and diverse population. It is home to Hollywood, the world's entertainment capital, and many movie stars, singers, and celebrities call LA their home. The city is also known for its vibrant nightlife, with countless bars, clubs, and restaurants catering to every taste.\n" +
                "\n" +
                "LA is a city that offers something for everyone. Its beaches, including Santa Monica and Venice Beach, are a popular destination for tourists and locals alike. The iconic Santa Monica Pier, with its amusement park and aquarium, is a must-see attraction.\n" +
                "\n" +
                "In addition to its beaches, LA is home to many museums and cultural institutions, such as the Getty Center and the Museum of Contemporary Art. The city's ethnic neighborhoods, such as Chinatown and Little Tokyo, offer a glimpse into the diverse cultures that make up the city's population.",  LocalDate.now()));
        beatRepository.save(new Beat("https://www.dropbox.com/s/i0uyi507wegrhsx/343938814_183853841230465_3308902580229225840_n.jpg?raw=1", "Life is  fast","A maj", 120, 15,"https://www.dropbox.com/s/r38ebpjludhm0jk/DANC%20-%20Actually%20basic%20plugnb%20%40140bpm.mp3?raw=1", "https://www.dropbox.com/s/i0uyi507wegrhsx/343938814_183853841230465_3308902580229225840_n.jpg?raw=1"));
        beatRepository.save(new Beat("https://www.dropbox.com/s/i0uyi507wegrhsx/343938814_183853841230465_3308902580229225840_n.jpg?raw=1", "Life is  fast","A maj", 120, 15,"https://www.dropbox.com/s/r38ebpjludhm0jk/DANC%20-%20Actually%20basic%20plugnb%20%40140bpm.mp3?raw=1", "https://www.dropbox.com/s/i0uyi507wegrhsx/343938814_183853841230465_3308902580229225840_n.jpg?raw=1"));
        beatRepository.save(new Beat("https://www.dropbox.com/s/i0uyi507wegrhsx/343938814_183853841230465_3308902580229225840_n.jpg?raw=1", "Life is  fast","A maj", 120, 15,"https://www.dropbox.com/s/r38ebpjludhm0jk/DANC%20-%20Actually%20basic%20plugnb%20%40140bpm.mp3?raw=1", "https://www.dropbox.com/s/i0uyi507wegrhsx/343938814_183853841230465_3308902580229225840_n.jpg?raw=1"));
        beatRepository.save(new Beat("https://www.dropbox.com/s/i0uyi507wegrhsx/343938814_183853841230465_3308902580229225840_n.jpg?raw=1", "Life is  fast","A maj", 120, 15,"https://www.dropbox.com/s/r38ebpjludhm0jk/DANC%20-%20Actually%20basic%20plugnb%20%40140bpm.mp3?raw=1", "https://www.dropbox.com/s/i0uyi507wegrhsx/343938814_183853841230465_3308902580229225840_n.jpg?raw=1"));
        beatRepository.save(new Beat("https://www.dropbox.com/s/i0uyi507wegrhsx/343938814_183853841230465_3308902580229225840_n.jpg?raw=1", "Life is  fast","A maj", 120, 15,"https://www.dropbox.com/s/r38ebpjludhm0jk/DANC%20-%20Actually%20basic%20plugnb%20%40140bpm.mp3?raw=1", "https://www.dropbox.com/s/i0uyi507wegrhsx/343938814_183853841230465_3308902580229225840_n.jpg?raw=1"));
        beatRepository.save(new Beat("https://www.dropbox.com/s/i0uyi507wegrhsx/343938814_183853841230465_3308902580229225840_n.jpg?raw=1", "Life is  fast","A maj", 120, 15,"https://www.dropbox.com/s/r38ebpjludhm0jk/DANC%20-%20Actually%20basic%20plugnb%20%40140bpm.mp3?raw=1", "https://www.dropbox.com/s/i0uyi507wegrhsx/343938814_183853841230465_3308902580229225840_n.jpg?raw=1"));
        beatRepository.save(new Beat("https://www.dropbox.com/s/i0uyi507wegrhsx/343938814_183853841230465_3308902580229225840_n.jpg?raw=1", "Life is  fast","A maj", 120, 15,"https://www.dropbox.com/s/r38ebpjludhm0jk/DANC%20-%20Actually%20basic%20plugnb%20%40140bpm.mp3?raw=1", "https://www.dropbox.com/s/i0uyi507wegrhsx/343938814_183853841230465_3308902580229225840_n.jpg?raw=1"));
        beatRepository.save(new Beat("https://www.dropbox.com/s/i0uyi507wegrhsx/343938814_183853841230465_3308902580229225840_n.jpg?raw=1", "Life is  fast","A maj", 120, 15,"https://www.dropbox.com/s/r38ebpjludhm0jk/DANC%20-%20Actually%20basic%20plugnb%20%40140bpm.mp3?raw=1", "https://www.dropbox.com/s/i0uyi507wegrhsx/343938814_183853841230465_3308902580229225840_n.jpg?raw=1"));
        articleRepository.save(new Article("https://as1.ftcdn.net/v2/jpg/05/25/11/10/1000_F_525111061_3iz7WuNm8RIavuu5pz2j3VID6xiCShFq.jpg", "We moved to LA", "Los Angeles, or simply LA, is a sprawling metropolis located in the southern part of California, USA. With a population of over 4 million people, LA is the second-most populous city in the United States and one of the largest cities in the world.\n" +
                "\n" +
                "The city of LA is famous for many things, such as its beaches, entertainment industry, and diverse population. It is home to Hollywood, the world's entertainment capital, and many movie stars, singers, and celebrities call LA their home. The city is also known for its vibrant nightlife, with countless bars, clubs, and restaurants catering to every taste.\n" +
                "\n" +
                "LA is a city that offers something for everyone. Its beaches, including Santa Monica and Venice Beach, are a popular destination for tourists and locals alike. The iconic Santa Monica Pier, with its amusement park and aquarium, is a must-see attraction.\n" +
                "\n" +
                "In addition to its beaches, LA is home to many museums and cultural institutions, such as the Getty Center and the Museum of Contemporary Art. The city's ethnic neighborhoods, such as Chinatown and Little Tokyo, offer a glimpse into the diverse cultures that make up the city's population.",  LocalDate.now()));


    }
}
