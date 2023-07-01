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
import com.example.xowrld.Service.StripeService;
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
    private StripeService paymentsService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private BeatRepository beatRepository;


    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private PurchaseRepo purchaseRepo;
    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @Value("PURCHASE_ID")
    private String purchasecode;


    @PostMapping("/charge2")
    public String charge2(ChargeRequest chargeRequest, Model model, @RequestParam("username") String username)
            throws StripeException, MessagingException {
        chargeRequest.setAmount(500);
        chargeRequest.setCurrency(ChargeRequest.Currency.USD);
        Charge charge = paymentsService.charge(chargeRequest);

        LocalDateTime nowdate = LocalDateTime.now();
        ;        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime formattedDateTime = LocalDateTime.parse(nowdate.format(formatter));

        Optional<AppUser> appUser = appUserRepo.findByUsername(username);
        appUser.get().setFloaters(appUser.get().getFloaters()+15);
        appUserRepo.save(appUser.get());

        purchaseRepo.save(new Purchase("Floater Purchase", "5$", formattedDateTime));


        return "personal/succesfullpurchase";
    }

    @PostMapping("/charge1")
    public String charge1(ChargeRequest chargeRequest, Model model, @RequestParam("username") String username)
            throws StripeException, MessagingException {
        chargeRequest.setAmount(250);
        chargeRequest.setCurrency(ChargeRequest.Currency.USD);
        Charge charge = paymentsService.charge(chargeRequest);

        LocalDateTime nowdate = LocalDateTime.now();
        ;        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime formattedDateTime = LocalDateTime.parse(nowdate.format(formatter));

        Optional<AppUser> appUser = appUserRepo.findByUsername(username);
        appUser.get().setFloaters(appUser.get().getFloaters()+5);
        appUserRepo.save(appUser.get());

        purchaseRepo.save(new Purchase("Floater Purchase", "2.5$", formattedDateTime));


        return "personal/succesfullpurchase";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }

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


        model.addAttribute("amount",  1000); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.USD);
        model.addAttribute("de", "Floater Purchase");

        return "personal/buyfloaters";
    }

    @GetMapping("/{purchaseid}/successfullpurchase")
    public String succesffulpurchase1(@PathVariable("purchaseid") String purchaseid){
        String id = purchasecode;
        if(purchaseid.equals("6728547824%92hhufh_:::.5643276542vgdhsfwuzet5432trus%576+975")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            AppUser currentUser = (AppUser) authentication.getPrincipal();
            Optional<AppUser> user = appUserRepo.findById(currentUser.getId());
            user.get().setFloaters(user.get().getFloaters() + 5);
            System.out.println("floateradded");
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

    @GetMapping("/{id}/successfullpurchase2")
    public String succesffulpurchase2(@PathVariable("id") String sessionid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser currentUser = (AppUser) authentication.getPrincipal();
        Optional<AppUser> user = appUserRepo.findById(currentUser.getId());
        user.get().setFloaters(user.get().getFloaters()+15);
        appUserRepo.save(user);


        return "redirect:/";
    }
}
