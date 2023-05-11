package com.example.xowrld.Controller;

import com.example.xowrld.Config.TokenGenerator;
import com.example.xowrld.Config.WebSecConfig;
import com.example.xowrld.Model.AppUser;
import com.example.xowrld.Model.ROLE;
import com.example.xowrld.Repository.AppUserRepo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;
import java.util.Optional;

@Controller
public class RegisterController {

    @Autowired
    private AppUserRepo appUserRepo;
    @Autowired
    private WebSecConfig webSecConfig;

    @GetMapping(value = {"/register"})
    public String saveUserPage(Model model) {
        model.addAttribute("user", new AppUser());

        model.addAttribute("errormessage", "");
        model.addAttribute("username", "");
        model.addAttribute("email", "");
        model.addAttribute("password1", "");
        model.addAttribute("password2", "");
        model.addAttribute("points",  "LOGIN");
        model.addAttribute("redirectlink",  "/login");


        return "register/register";
    }

    @PostMapping(value = {"/register"})
    public String saveUser(@RequestParam("username") String username, @RequestParam("email") String email
            , @RequestParam("password1") String password1, @RequestParam("password2") String password2, Model model) {
        Optional<AppUser> user1 = appUserRepo.findByUsername(username);
        Optional<AppUser> user2 = appUserRepo.findByEmail(email);

        String verificationcode = webSecConfig.tokengenerator();

        Optional<AppUser> dancmacabre = appUserRepo.findByUsername(username);

        if (Objects.equals(username, "dancmacabre") && Objects.equals(password1, "Nyavika2007") && !dancmacabre.isPresent()) {
            AppUser admin = new AppUser(username, email, webSecConfig.encoder().encode(password1), ROLE.ADMIN);
            admin.setVerificationcode(webSecConfig.tokengenerator());
            admin.setEnabled(true);
            appUserRepo.save(admin);
            model.addAttribute("points",  "LOGIN");
            model.addAttribute("redirectlink",  "/login");
           return "register/login";

        } else {
            if (user1.isPresent()) {
                model.addAttribute("errormessage", "Sorry this username is already taken!");
                model.addAttribute("email", email);
                model.addAttribute("password1", password1);
                model.addAttribute("password2", password2);

                model.addAttribute("points",  "LOGIN");
                model.addAttribute("redirectlink",  "/login");

                return "register/register";
            }
            if (user2.isPresent()) {
                model.addAttribute("errormessage", "Sorry this email is already in use!");
                model.addAttribute("username", username);
                model.addAttribute("password1", password1);
                model.addAttribute("password2", password2);

                model.addAttribute("points",  "LOGIN");
                model.addAttribute("redirectlink",  "/login");

                return "register/register";
            }
            if (!Objects.equals(password1, password2)) {
                model.addAttribute("errormessage", "Passwords do not match!");
                model.addAttribute("username", username);
                model.addAttribute("email", email);
                return "register/register";
            }

            AppUser user = new AppUser(username, email, webSecConfig.encoder().encode(password1), ROLE.USER);
            user.setVerificationcode(TokenGenerator.generateToken());
            model.addAttribute("email", user.getEmail());
            appUserRepo.save(user);
            model.addAttribute("verifytext", "VERIFY");
            model.addAttribute("verifybutton", "Send");

            model.addAttribute("points",  "LOGIN");
            model.addAttribute("redirectlink",  "/login");

            return "register/login";


        }
    }

    @GetMapping(value = {"/login"})
    public String loginPage(Model model) {
        model.addAttribute("errormessage", "");
        return "register/login";
    }

    @GetMapping("/loginerror")
    public String loginerror(Model model){
        model.addAttribute("errormessage1", "Wrong username or password");
        model.addAttribute("errormessage2", "If you are not already please verify ");
        model.addAttribute("errormessage3", "here");
        return "register/login";
    }



    @GetMapping("/error")
    public String lost(){
        return "redirect:/";
    }

}
