package com.example.xowrld.Controller;

import com.example.xowrld.EmailSenderService.EmailSenderService;
import com.example.xowrld.Model.AppUser;
import com.example.xowrld.Model.ChargeRequest;
import com.example.xowrld.Model.Purchase;
import com.example.xowrld.Model.SoldBeat;
import com.example.xowrld.Repository.AppUserRepo;
import com.example.xowrld.Repository.BeatRepository;
import com.example.xowrld.Repository.PurchaseRepo;
import com.example.xowrld.Repository.SoldBeatRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
public class ChargeController {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private BeatRepository beatRepository;


    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private PurchaseRepo purchaseRepo;
    @Value("PURCHASE_ID")
    private String purchasecode;



    @GetMapping("/buyfloaters")
    public String buyfloaters(Model model){

        List<AppUser> appUserList = (List<AppUser>) appUserRepo.findAll();

        System.out.println(appUserList.size());



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser currentUser = (AppUser) authentication.getPrincipal();
        Optional<AppUser> user = appUserRepo.findById(currentUser.getId());

        model.addAttribute("username", user.get().getUsername());
        model.addAttribute("points", user.get().getFloaters());
        model.addAttribute("email", user.get().getEmail());



        return "personal/buyfloaters";
    }

    @GetMapping("/{purchaseid}/successfullpurchase1")
    public String succesffulpurchase1(@PathVariable("purchaseid") String purchaseid){
        if(purchaseid.equals(purchasecode)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            AppUser currentUser = (AppUser) authentication.getPrincipal();
            Optional<AppUser> user = appUserRepo.findById(currentUser.getId());
            user.get().setFloaters(user.get().getFloaters() + 5);
            appUserRepo.save(user.get());

            return "redirect:/succesfullpurchase";
        }
        return "redirect:/tryagainpurchase";
    }

    @GetMapping("/{purchaseid}/successfullpurchase2")
    public String succesffulpurchase2(@PathVariable("purchaseid") String purchaseid){
        if(purchaseid.equals(purchasecode)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            AppUser currentUser = (AppUser) authentication.getPrincipal();
            Optional<AppUser> user = appUserRepo.findById(currentUser.getId());
            user.get().setFloaters(user.get().getFloaters() + 15);
            appUserRepo.save(user.get());

            return "redirect:/succesfullpurchase";
        }
        return "redirect:/tryagainpurchase";
    }

    @GetMapping("/tryagainpurchase")
    public String tryagain(){
        return "personal/tryagainpurchase";
    }

    @GetMapping("/successfullpurchase")
    public String success(){
        return "personal/succesfullpurchase";
    }

}
