package com.example.xowrld.Controller;


import com.example.xowrld.Config.WebSecConfig;
import com.example.xowrld.EmailSenderService.EmailSenderService;
import com.example.xowrld.Model.AppUser;
import com.example.xowrld.Repository.AppUserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.util.Objects;
import java.util.Optional;

@Controller
public class ForgotPasswordController {
    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private WebSecConfig webSecConfig;

    @Autowired
    private EmailSenderService emailSenderService;


    @GetMapping("/forgotpassword")
    public String forgotpassword(Model model) {

            model.addAttribute("errormessage", "FORGOT PASSWORD?");

        return "register/forgotpassword1";
    }

    @PostMapping("/forgotpassword")
    public String forgot(@RequestParam("email") String email, Model model) throws MessagingException {
        Optional<AppUser> user = appUserRepo.findByEmail(email);
        if (user.isPresent()) {
            String code = user.get().getVerificationcode();
            emailSenderService.forgotpasswordsender(email, code);
            model.addAttribute("email", email);
            return "register/forgotpassword2";
        } else {
            model.addAttribute("errormessage", "No account with this email");
            return "register/forgotpassword1";

        }
    }

    @PostMapping("/setnewpassword")
    public String setnewpassword(@RequestParam("code") String code, @RequestParam("email") String email, Model model) {
        if (Objects.equals(appUserRepo.findByEmail(email).get().getVerificationcode(), code)) {
            model.addAttribute("email", email);
            return "register/forgotpassword3";
        }
        return "register/forgotpassword2";
    }

    @PostMapping("/changepassword")
    public String changepassword(@RequestParam("password1") String password1,@RequestParam("email") String email, @RequestParam("password2") String password2) {
        Optional<AppUser> user = appUserRepo.findByEmail(email);
        if(password1.equals(password2) && user.isPresent()){
            user.get().setPassword(webSecConfig.encoder().encode(password1));
            appUserRepo.delete(user.get());
            appUserRepo.save(user.get());
            return "redirect:/login";
        }

      return   "register/forgotpassword3";
    }

    @GetMapping("/changeusername")
    public String changeusername(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser currentUser = (AppUser) authentication.getPrincipal();
        Optional<AppUser> user = appUserRepo.findById(currentUser.getId());

        model.addAttribute("email", user.get().getEmail());
        return "register/changeusername";
    }

    @PostMapping("/changeusername")
    public String changeusern(@RequestParam("username") String username, @RequestParam("email") String email){
        Optional<AppUser> appUser = appUserRepo.findByEmail(email);
        appUser.get().setUsername(username);
        appUserRepo.save(appUser.get());

        return "redirect:/myaccount";
    }

    @GetMapping("/changeemail")
    public String changeemail(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser currentUser = (AppUser) authentication.getPrincipal();
        Optional<AppUser> user = appUserRepo.findById(currentUser.getId());

        model.addAttribute("email", user.get().getEmail());
        return "register/changeemail";
    }

    @PostMapping("/changeemail")
    public String changeemail(@RequestParam("email1") String username, @RequestParam("email") String email){
        Optional<AppUser> appUser = appUserRepo.findByEmail(email);
        appUser.get().setEmail(username);
        appUserRepo.save(appUser.get());

        return "redirect:/myaccount";
    }





}
