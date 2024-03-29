package com.example.xowrld.Controller;


import com.example.xowrld.Model.AppUser;
import com.example.xowrld.Model.Beat;
import com.example.xowrld.Model.ChargeRequest;
import com.example.xowrld.Repository.AppUserRepo;
import com.example.xowrld.Repository.ArticleRepository;
import com.example.xowrld.Repository.BeatRepository;
import com.example.xowrld.Service.AppUserService;
import com.example.xowrld.Service.RawGoogleDriveLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private BeatRepository beatRepository;

    @Autowired
    private RawGoogleDriveLink googleDriveLink;

    @Autowired
    private ArticleRepository articleRepositor;

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/editbeat/{title}")
    public String editbeat(@PathVariable("title")  Long title, Model model){
       Optional<Beat> beat =  beatRepository.getById(title);
       model.addAttribute("beat", beat.get());

       return "beat/editbeat";
    }

    @PostMapping("/editbeat/{id}")
    public String editbeat(Beat beat, @PathVariable("id") Long id) throws IOException {
        Beat beat1 = beat;
        Optional<Beat> originalbeat = beatRepository.getById(id);
        if(!beat.getPreviewurl().equals(originalbeat.get().getPreviewurl())){
            String previewurl =  googleDriveLink.getRawLink(beat1.getPreviewurl());
            beat1.setPreviewurl(previewurl);
        }
        if(!beat.getHeader().equals(originalbeat.get().getHeader())){
            String header =  googleDriveLink.getRawLink(beat1.getHeader());
            beat1.setHeader(header);
        }
        Optional<Beat> temp = beatRepository.getById(id);
        beatRepository.delete(temp.get());

        beatRepository.save(beat1);

        return "redirect:/findbeat";
    }

    @PostMapping("removebeat/{title}")
    public String removebeat(@PathVariable("title") Long title){
        beatRepository.delete(beatRepository.getById(title).get());

        return "redirect:/findbeat";
    }

    @GetMapping("/choosepaymentmethod1")
    public String choosepayment1(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser currentUser = (AppUser) authentication.getPrincipal();
        Optional<AppUser> user = appUserRepo.findById(currentUser.getId());

        model.addAttribute("username", user.get().getUsername());
        model.addAttribute("points", user.get().getFloaters());
        model.addAttribute("email", user.get().getEmail());

        return "personal/paymentmethod1";
    }

    @GetMapping("/choosepaymentmethod2")
    public String choosepayment2(Model model){


        return "personal/paymentmethod2";
    }

    @GetMapping("/usersummary")
    public String usersummary(Model model){
        List<AppUser> users = (List<AppUser>) appUserRepo.findAll();
        model.addAttribute("users", users);
        return "admin/usersummary";
    }

    @GetMapping("/banuser/{id}")
    public String banuser (@PathVariable("id") Long id) {

        Optional<AppUser> appUser = appUserRepo.findById(id);

        appUserRepo.delete(appUser.get());

        return "redirect:/usersummary";
    }

    @PostMapping ("/searchuser")
    public String searchuser(Model model, @RequestParam("textyn") String data){
        List<AppUser> users = new ArrayList<>();
        List<AppUser> allusers = (List<AppUser>) appUserRepo.findAll();

        for(int i = 0; i<allusers.size(); i++){
            if(appUserService.checkText(appUserRepo, allusers.get(i).getUsername(), data) != null){
                allusers.add(allusers.get(i));
            }
        }

        for(int i = 0; i<allusers.size(); i++){
            if(appUserService.checkText(appUserRepo, allusers.get(i).getEmail(), data) != null){
                allusers.add(allusers.get(i));
            }
        }

        model.addAttribute("users", users);

        return "admin/usersummary";
    }

}
