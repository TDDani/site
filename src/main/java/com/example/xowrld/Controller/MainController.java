package com.example.xowrld.Controller;

import com.example.xowrld.EmailSenderService.EmailSenderService;
import com.example.xowrld.Model.AppUser;
import com.example.xowrld.Model.Article;
import com.example.xowrld.Model.Message;
import com.example.xowrld.Model.SoldBeat;
import com.example.xowrld.Repository.ArticleRepository;
import com.example.xowrld.Repository.BeatRepository;
import com.example.xowrld.Repository.SoldBeatRepository;
import com.example.xowrld.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ArticleRepository articleRepository;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private SoldBeatRepository soldBeatRepository;




    @GetMapping("/about")
    public String getabout(){
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

    @GetMapping("/home")
    public String gethomepage(Model model){
        List<Article> articleList = (List<Article>) articleRepository.findAll();
        int i = articleList.size();
        model.addAttribute("art1", articleList.get(i-1));
        model.addAttribute("art2", articleList.get(i-2));
        model.addAttribute("art3", articleList.get(i-3));
        model.addAttribute("art4", articleList.get(i-4));
        model.addAttribute("art5", articleList.get(i-5));
        model.addAttribute("art6", articleList.get(i-6));
        model.addAttribute("art7", articleList.get(i-7));
        model.addAttribute("art8", articleList.get(i-8));
        model.addAttribute("art9", articleList.get(i-9));
        return "personal/home";
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
