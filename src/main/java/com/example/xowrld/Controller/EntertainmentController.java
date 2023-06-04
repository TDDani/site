package com.example.xowrld.Controller;


import com.example.xowrld.EmailSenderService.EmailSenderService;
import com.example.xowrld.Model.AppUser;
import com.example.xowrld.Model.Purchase;
import com.example.xowrld.Repository.AppUserRepo;
import com.example.xowrld.Repository.PurchaseRepo;
import com.example.xowrld.Service.SlotService;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@Controller
public class EntertainmentController {
    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private SlotService slotService;

    @Autowired
    private PurchaseRepo purchaseRepo;




    @Autowired
    private EmailSenderService emailSenderService;
    @GetMapping("/floatermachine")
    public String floatermachine(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof UsernamePasswordAuthenticationToken) {
            AppUser currentUser = (AppUser) authentication.getPrincipal();
            Optional<AppUser> user = appUserRepo.findById(currentUser.getId());
            model.addAttribute("points",  user.get().getFloaters());
            model.addAttribute("userid",  user.get().getId());
            model.addAttribute("redirectlink",  "/myaccount");
            return "entertainment/slotmachine";
        } else {
            model.addAttribute("points", "LOGIN");
            model.addAttribute("redirectlink",  "/login");
            model.addAttribute("myDateTime", "2023-04-23T16:00:00");
            model.addAttribute("userid",  1);
            return "entertainment/slotmachine";
        }

    }


    @GetMapping("/won")
    public String sendoneshot(@RequestParam("userid") Long id, @RequestParam("prize") String prize) throws MessagingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser currentUser = (AppUser) authentication.getPrincipal();
        Optional<AppUser> user = appUserRepo.findById(currentUser.getId());
        Optional<AppUser> appUser = appUserRepo.findById(id);
        appUser.get().setFloaters(appUser.get().getFloaters()-1);
        LocalDateTime nowdate = LocalDateTime.now();
;        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime formattedDateTime = LocalDateTime.parse(nowdate.format(formatter));

        purchaseRepo.save(new Purchase("Floater Machine", "1", formattedDateTime));
        appUserRepo.save(appUser.get());
        if(Objects.equals(user.get().getId(), id)){
            if(prize.equals("lost")){
                return "redirect:/floatermachine";
            } else if(prize.equals("10floater")) {
                appUser.get().setFloaters(appUser.get().getFloaters() + 10);
                appUserRepo.save(appUser.get());
                return "redirect:/floatermachine";
            }
            else if(prize.equals("5floater")) {
                appUser.get().setFloaters(appUser.get().getFloaters() + 5);
                appUserRepo.save(appUser.get());
                return "redirect:/floatermachine";
            }
            else if(prize.equals("loop")) {
                emailSenderService.floatermachine(appUser.get().getEmail(), slotService.randomloop());

                return "entertainment/slotmachinewin";
            }
            else if(prize.equals("minioneshot")) {
                emailSenderService.floatermachine(appUser.get().getEmail(), slotService.randomoneshot());
                return "entertainment/slotmachinewin";
            }
            else if(prize.equals("beat")) {
                emailSenderService.floatermachine(appUser.get().getEmail(), slotService.randombeat());
                return "entertainment/slotmachinewin";
            }
        }
        return "redirect:/floatermachine";
    }

    @PostMapping("/newprizeloop")
    public String newprizeloop(@RequestParam("loop") String loop){
        slotService.getLoops().add(loop);

        return "redirect:/controlprizes";
    }

    @PostMapping("/newprizebeat")
    public String newprizebeta(@RequestParam("beat") String beat){
        slotService.getBeats().add(beat);

        return "redirect:/controlprizes";
    }

    @PostMapping("/newprizeoneshot")
    public String newprizeoneshot(@RequestParam("oneshot") String oneshot){
        slotService.getOneshots().add(oneshot);

        return "redirect:/controlprizes";
    }

    @GetMapping("/controlprizes")
    public String controlprzes(Model model){
        model.addAttribute("loop", slotService.getLoops() );
        model.addAttribute("beat", slotService.getBeats() );
        model.addAttribute("oneshot", slotService.getOneshots() );

        return "entertainment/controlprizes";
    }

    @GetMapping("/removeloop/{id}")
    public String removeprizeloop(@PathVariable("id") int id){
        String temp = slotService.getLoops().get(id);
        slotService.getLoops().remove(temp);

        return "redirect:/controlprizes";
    }

    @GetMapping("/removebeat/{id}")
    public String removeprizebeat(@PathVariable("id") int id){
        String temp = slotService.getBeats().get(id);
        slotService.getBeats().remove(temp);

        return "redirect:/controlprizes";
    }

    @GetMapping("/removeoneshot/{id}")
    public String removeoneshot(@PathVariable("id") int id){
        String temp = slotService.getOneshots().get(id);
        slotService.getOneshots().remove(temp);
        return "redirect:/controlprizes";
    }




}
