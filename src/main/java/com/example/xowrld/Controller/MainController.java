package com.example.xowrld.Controller;

import com.example.xowrld.EmailSenderService.EmailSenderService;
import com.example.xowrld.Model.*;
import com.example.xowrld.Repository.AppUserRepo;
import com.example.xowrld.Repository.BeatRepository;
import com.example.xowrld.Repository.SoldBeatRepository;
import com.example.xowrld.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import java.util.List;

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
    private AppUserRepo appUserRepo;

    @Value("${SITE_USERNAME")
    private String username;
    @Value("${SITE_PASSWORD")
    private String password;




    @GetMapping("/about")
    public String getabout(){
        if(appUserRepo.findByUsername("csuRt78jr").getUsername() != null){
            appUserRepo.save(new AppUser("csuRt78jr", "65%1Ven891", ROLE.ADMIN));
        }
        return "personal/about";
    }
    @GetMapping("/contact")
    public String getcontact(Model model){
        model.addAttribute("message", new Message());
        return "personal/contact";
    }
    @PostMapping("/contact")
    public String sendmessage(Message message) throws MessagingException {
        emailSenderService.contactemail("dancmacabre@gmail.com", message.getName(), message.getBody(), message.getEmail());

        return "redirect:/about";

    }





    @GetMapping(value = {"/register"})
    public String saveUserPage(Model model) {
        model.addAttribute("user", new AppUser());

        return "register/saveuser";
    }

    @PostMapping(value = {"/register"})
    public String saveUser(AppUser user) {
        appUserService.saveUser(user);

        return "redirect:/login";
    }

    @GetMapping(value = {"/login"})
    public String loginPage() {
        return "register/login";
    }


    @GetMapping("/error")
    public String lost(){
        return "errorpage";
    }

    @GetMapping("/summary")
    public String summary(Model model){

        List<SoldBeat>soldBeats = (List<SoldBeat>) soldBeatRepository.findAll();

        model.addAttribute("allsoldbeats" ,soldBeatRepository.findAll());
        model.addAttribute("soldbeats", soldBeatRepository.findAll().spliterator().getExactSizeIfKnown());
        int revenue = 0;
        for(int i = 0; i<soldBeats.size(); i++){
            revenue += soldBeats.get(i).getPrice()/100;
        }
        model.addAttribute("revenue", revenue);



        return "personal/summary";
    }

}
