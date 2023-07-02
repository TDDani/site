package com.example.xowrld.Controller;

import com.example.xowrld.Config.WebSecConfig;
import com.example.xowrld.EmailSenderService.EmailSenderService;
import com.example.xowrld.Model.*;
import com.example.xowrld.Repository.*;
import com.example.xowrld.Service.AppUserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.time.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private BeatRepository beatRepository;


    @Autowired
    private AppUserService appUserService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private SoldBeatRepository soldBeatRepository;

    @Autowired
    private UpcomingEventRepo upcomingEventRepo;

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private WebSecConfig webSecConfig;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private PurchaseRepo purchaseRepo;

    @Value("${SITE_USERNAME")
    private String username;
    @Value("${SITE_PASSWORD")
    private String password;


    @GetMapping("/about")
    public String getabout(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof UsernamePasswordAuthenticationToken) {
            AppUser currentUser = (AppUser) authentication.getPrincipal();
            Optional<AppUser> user = appUserRepo.findById(currentUser.getId());
            model.addAttribute("points",  user.get().getFloaters());
            model.addAttribute("redirectlink",  "/myaccount");
        } else {
            model.addAttribute("points", "LOGIN");
            model.addAttribute("redirectlink",  "/login");
        }
        return "personal/about";

    }

    @GetMapping("/contact")
    public String getcontact(Model model) {
        model.addAttribute("message", new Message());
        return "personal/contact";
    }

    @PostMapping("/contact")
    public String sendmessage(Message message) throws MessagingException {
        emailSenderService.contactemail("dancmacabre@gmail.com", message.getName(), message.getBody(), message.getEmail());

        return "redirect:/about";

    }




    @GetMapping("/")
    public String gethomepage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<UpcomingEvent> upcomingEvents = (List<UpcomingEvent>) upcomingEventRepo.findAllByOrderByTimeDesc();
        int i = upcomingEvents.size();
        List<Article> articles = (List<Article>) articleRepository.findAll();
        int x = articles.size();
        if(i>0) {
            model.addAttribute("ue1", upcomingEvents.get(i - 1));
        }
        if(i>1){
            model.addAttribute("ue2", upcomingEvents.get(i-2));
        }
        if(i>2){
            model.addAttribute("ue3", upcomingEvents.get(i-3));
        }

        if(i>3){
            model.addAttribute("ue4", upcomingEvents.get(i-4));
        }
        if(i>4){
            model.addAttribute("ue5", upcomingEvents.get(i-5));
        }
        model.addAttribute("ar1", articles.get(x-1));

        model.addAttribute("ar2", articles.get(x-2));

        model.addAttribute("ar3", articles.get(x-3));

        model.addAttribute("ar4", articles.get(x-4));

        model.addAttribute("ar5", articles.get(x-5));
        model.addAttribute("ar6", articles.get(x-6));
        model.addAttribute("numofue", upcomingEventRepo.findAllByOrderByTimeDesc().size());
        if(authentication instanceof UsernamePasswordAuthenticationToken) {
            AppUser currentUser = (AppUser) authentication.getPrincipal();
            Optional<AppUser> user = appUserRepo.findById(currentUser.getId());
            if(user.get().getUsername().equals("dancmacabre")){
                model.addAttribute("adminlink", "/adminhub");
                model.addAttribute("adminbutton", "Adminhub");
            } else {
                model.addAttribute("adminlink", "");
                model.addAttribute("adminbutton", "");
            }
            model.addAttribute("points",  user.get().getFloaters());
            model.addAttribute("userid",  user.get().getId());
            model.addAttribute("redirectlink",  "/myaccount");
            return "homepage";
        } else {
            model.addAttribute("points", "LOGIN");
            model.addAttribute("redirectlink",  "/login");
            model.addAttribute("myDateTime", "2023-04-23T16:00:00");
            return "homepage";
        }


    }

    @GetMapping("/newupcomingevent")
    public String newupcevent(Model model){
        model.addAttribute("event", new UpcomingEvent());

        return "article/newupcomingevent";
    }

    @PostMapping("/newupcomingevent")
    public String newupc(@RequestParam("title") String title, @RequestParam("date") String date, @RequestParam("link") String link){
        UpcomingEvent upcomingEvent = new UpcomingEvent();
        upcomingEvent.setLink(link);
        upcomingEvent.setTitle(title);
        upcomingEvent.setTime(date);
        upcomingEventRepo.save(upcomingEvent);

        return "redirect:/";
    }

    @GetMapping("/controlevents")
    public String controlevent(Model model){
       model.addAttribute("uclist", upcomingEventRepo.findAllByOrderByTimeAsc());
        return "article/controlevent";
    }

    @GetMapping("/remove/upcomingevent/{id}")
    public String remove(@PathVariable("id") Long id){
        Optional<UpcomingEvent> uc = upcomingEventRepo.findById(id);

        upcomingEventRepo.delete(uc.get());

        return "redirect:/controlevents";
    }

    @GetMapping("/adminhub")
    public String adminhub(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String points;
        String redirectlink;
        if(authentication instanceof UsernamePasswordAuthenticationToken) {
            AppUser currentUser = (AppUser) authentication.getPrincipal();
            Optional<AppUser> user = appUserRepo.findById(currentUser.getId());
            points = String.valueOf(user.get().getFloaters());
            redirectlink = "/myaccount";
        } else {

            points =  "LOGIN";
            redirectlink = "/myaccount";

        }
        model.addAttribute("points", points);
        model.addAttribute("redirectlink",  redirectlink);


        return "admin/adminhub";
    }

    @GetMapping("/summary")
    public String summary(Model model){



        model.addAttribute("purchases", purchaseRepo.findAllByOrderByTime());
        return "personal/summary";
    }

    @GetMapping("/myaccount")
    public String myaccount(Model model){



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser currentUser = (AppUser) authentication.getPrincipal();
        Optional<AppUser> user = appUserRepo.findById(currentUser.getId());
        model.addAttribute("username", user.get().getUsername());
        model.addAttribute("user", user.get());
        model.addAttribute("points",  user.get().getFloaters());
        model.addAttribute("redirectlink",  "/myaccount");


        return "personal/myaccount";
    }

    @PostMapping("/sendfloaters")
    public String sendfloaters(@RequestParam("username") String username, Model model){




        model.addAttribute("errormessage", "");

        model.addAttribute("username", username);

        return "personal/sendfloaters";
    }

    @PostMapping("/sendfloaters2")
    public String sendfloa(@RequestParam("username") String username, Model model, @RequestParam("amount") int amount, @RequestParam("tousername") String tousername){
        Optional<AppUser> appUser1 = appUserRepo.findByUsername(username);
        Optional<AppUser> appUser2 = appUserRepo.findByUsername(tousername);
        if(!appUser2.isPresent()) {
            model.addAttribute("username", username);
            model.addAttribute("errormessage", "User " + tousername + " cannot be found");
            return "personal/sendfloaters";
        } else if(appUser1.get().getFloaters()<amount){
            model.addAttribute("username", username);
            model.addAttribute("errormessage", "*Insufficent funds");
            return "personal/sendfloaters";
        }
        appUser1.get().setFloaters(appUser1.get().getFloaters()-amount);
        appUserRepo.save(appUser1.get());

        appUser2.get().setFloaters(appUser2.get().getFloaters()+amount);
        appUserRepo.save(appUser2.get());

        return "redirect:/myaccount";
    }

    @GetMapping("/removearticle/{id}")
    public String removearticle(@PathVariable("id") Long id){
        articleRepository.delete(articleRepository.findById(id).get());

        return "redirect:/";
    }









}
