package com.example.xowrld.Controller;

import com.example.xowrld.EmailSenderService.EmailSenderService;
import com.example.xowrld.Model.AppUser;
import com.example.xowrld.Model.Article;
import com.example.xowrld.Model.Site;
import com.example.xowrld.Repository.ArticleRepository;
import com.example.xowrld.Service.RawGoogleDriveLink;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private Site site1;

    @Autowired
    private RawGoogleDriveLink rawGoogleDriveLink;


    Map<String, String> idkwhattodo = new HashMap<>();
    Site site = new Site(idkwhattodo);

    @GetMapping("/newarticle")
    public String newarticle(Model model){

        model.addAttribute("article", new Article());

        return "article/newarticle";
    }

    @PostMapping("/newarticle")
    public String addnewarticle(Article article) throws IOException {
        Article article1 = article;
        article1.setCreated(LocalDate.now());
        article1.setHeader(rawGoogleDriveLink.getRawLink(article1.getHeader()));
        articleRepository.save(article1);
        return "redirect:/";
    }

    @PostMapping("/subscribetonewsletter")
    public String subscribetonewsletter(@RequestParam("name") String name, @RequestParam("email") String email){

site.getNewsletter().put(email, name);

        return "redirect:/";
    }

    @GetMapping("/sendnewsletter")
    public String newsletter(){

        return "article/newsletter";
    }

    @PostMapping("/sendnewsletter")
    public String sendnewsletter(@RequestParam("title") String title, @RequestParam("body")String body) throws MessagingException {
        Map<String, String> users = site.getNewsletter();

        for (Map.Entry<String, String> entry : users.entrySet()) {
            String name = entry.getValue();
            String email = entry.getKey();
            emailSenderService.newsletteremail(name, email, title, body);
        }

        return "redirect:/";
    }

    @GetMapping("/readarticle/{id}")
    public String readarticle(@PathVariable("id") Long id, Model model){
        Optional<Article> article = articleRepository.findById(id);
        model.addAttribute("article", article.get());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof UsernamePasswordAuthenticationToken) {
            AppUser currentUser = (AppUser) authentication.getPrincipal();
            model.addAttribute("points",  currentUser.getFloaters());
            model.addAttribute("redirectlink",  "/myaccount");
            return "article/spectatearticle";
        } else {
            model.addAttribute("points", "LOGIN");
            model.addAttribute("redirectlink",  "/login");
            model.addAttribute("myDateTime", "2023-04-23T16:00:00");
            return "article/spectatearticle";
        }




    }
}
