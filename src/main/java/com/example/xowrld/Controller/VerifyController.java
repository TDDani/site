package com.example.xowrld.Controller;


import com.example.xowrld.EmailSenderService.EmailSenderService;
import com.example.xowrld.Model.AppUser;
import com.example.xowrld.Repository.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.util.Optional;

@Controller
public class VerifyController {

    @Autowired
    private AppUserRepo appUserRepo;
    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping("/verify")
    public String verify(Model model){
        model.addAttribute("verifytext", "VERIFY");
        model.addAttribute("verifybutton", "Send");
        return "register/verify";
    }

    @PostMapping("/verify")
    public String verif(@RequestParam("code") String code, Model model, @RequestParam("email") String email){
        Optional<AppUser> user = appUserRepo.findByEmail(email);
        if(user.isPresent()){
            user.get().setIsauthenticated(true);
            return "register/login";
        } else {
            model.addAttribute("errormessage", "Wrong code");
            model.addAttribute("email", email);
            model.addAttribute("verifytext", "Email was sent");
            model.addAttribute("verifybutton", "Resend");
            return "register/verify";
        }
    }

    @PostMapping("/sendverification")
    public String sendverificatin(@RequestParam("email") String email, Model model) throws MessagingException {
        String code = appUserRepo.findByEmail(email).get().getVerificationcode();
        emailSenderService.verificationsender(email, code);
        model.addAttribute("email", email);
        model.addAttribute("verifytext", "Email was sent");
        model.addAttribute("verifybutton", "Resend");
        return "register/verify";
    }
}
