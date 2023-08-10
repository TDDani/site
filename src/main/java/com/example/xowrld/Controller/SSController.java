package com.example.xowrld.Controller;

import com.example.xowrld.Model.AppUser;
import com.example.xowrld.Model.SSTeam;
import com.example.xowrld.Repository.AppUserRepo;
import com.example.xowrld.Repository.SSTeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SSController {

    @Autowired
    private SSTeamRepo ssTeamRepo;

    @Autowired
    private AppUserRepo appUserRepo;

    @GetMapping("/survivorsshowdowntable")
    public String ssttable(Model model){

        model.addAttribute("teams", ssTeamRepo.findAll());

        return "survivorsshowdown/table";

    }

    @GetMapping("/survivorsshowdown")
    public String ssmain(Model model){
        List<SSTeam> ssTeams = (List<SSTeam>) ssTeamRepo.findAll();
        boolean isfull = ssTeams.size() > 3;
        model.addAttribute("isfull", isfull);
        return "survivorsshowdown/main";
    }

    @GetMapping("/survivorsshowdown/signup")
    public String signup1(Model model){
        model.addAttribute("errortext", "");

        return "survivorsshowdown/signup1";
    }

    @PostMapping("/survivorsshowdownsignin")
    public String sssignin(Model model,@RequestParam("username") String username, @RequestParam("password") String password){
        Optional<AppUser> appUser = appUserRepo.findByUsername(username);

        if(appUser.isPresent()){
            model.addAttribute("username", username);
            model.addAttribute("ssteam", new SSTeam());
            return "survivorsshowdown/signup2";
        }
        else{
            model.addAttribute("errortext", "Invalid Credintals");
            return "survivorsshowdown/signup1";
        }
    }

    @PostMapping("/survivorsshowdownsignupteam")
    public String signupteam(SSTeam ssTeam, Model model,@RequestParam("username") String username){
        model.addAttribute("ssteam", ssTeam);
        model.addAttribute("user", appUserRepo.findByUsername(username).get());
        if(appUserRepo.findByUsername(username).get().getFloaters()>4){
            model.addAttribute("buttontext", "Pay");
            model.addAttribute("redirectlink", "/joinedsurvivorsshowdown");
        } else{
            model.addAttribute("buttontext", "Buy More");
            model.addAttribute("redirectlink", "/buyfloaters");
        }
        return "survivorsshowdown/signup3";
    }

    @PostMapping("/survivorsshowdownreload")
    public String signuptam(SSTeam ssTeam, Model model,@RequestParam("username") String username){
        model.addAttribute("ssteam", ssTeam);
        model.addAttribute("user", appUserRepo.findByUsername(username).get());
        if(appUserRepo.findByUsername(username).get().getFloaters()>4){
            model.addAttribute("buttontext", "Pay");
            model.addAttribute("redirectlink", "/joinedsurvivorsshowdown");
        } else{
            model.addAttribute("buttontext", "Buy More");
            model.addAttribute("redirectlink", "/buyfloaters");
        }
        return "survivorsshowdown/signup3";
    }

    @PostMapping("/joinedsurvivorsshowdown")
    public String joinedss(SSTeam ssTeam, @RequestParam("username") String username, Model model){
        System.out.println(ssTeam.getTeamname() + " joined the competition!");
        appUserRepo.findByUsername(username).get().setFloaters(appUserRepo.findByUsername(username).get().getFloaters()-5);
        ssTeamRepo.save(ssTeam);
        model.addAttribute("teamname", ssTeam.getTeamname());
        return "survivorsshowdown/joined";
    }

}
