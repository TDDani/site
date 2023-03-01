package com.example.xowrld.Controller;

import com.example.xowrld.Model.AppUser;
import com.example.xowrld.Model.Beat;
import com.example.xowrld.Model.ChargeRequest;
import com.example.xowrld.Model.ROLE;
import com.example.xowrld.Repository.AppUserRepo;
import com.example.xowrld.Repository.BeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class BeatController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @Autowired
    private AppUserRepo appUserRepo;

    @Value("${SITE_USERNAME")
    private String username;
    @Value("${SITE_PASSWORD")
    private String password;

    @Autowired
    private BeatRepository beatRepository;

    public BeatController( AppUserRepo appUserRepo, BeatRepository beatRepository) {

        this.appUserRepo = appUserRepo;
        this.beatRepository = beatRepository;
    }

    @GetMapping("/newbeat")
    private String createnew(Model model){
        model.addAttribute("beat", new Beat());

        return "beat/newbeat";
    }

    @PostMapping("/newbeat")
    private String savenew(Beat beat) throws IOException {
        Beat beat1 = beat;
        beatRepository.save(beat1);
        return "redirect:/findbeat";
    }

    /*@GetMapping(value = "/photo/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] downloadPhoto(@PathVariable long id) {
        Beat beat = beatRepository.getById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found.")
        );

        return beat.getBeatData();
    }

    @GetMapping(value = "/coverphoto/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] x(@PathVariable long id) {
        Beat beat = beatRepository.getById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found.")
        );

        return beat.getBeatcoverdata();
    } */



    @GetMapping("/view/{id}")
    public String spectatebeat(@PathVariable Long id, Model model){
        Optional<Beat> beat = beatRepository.getById(id);
        if(beat.isPresent()){
            model.addAttribute("beat", beat.get());
            model.addAttribute("beatprice", beat.get().getPrice()/100);
            model.addAttribute("amount",  1000); // in cents
            model.addAttribute("stripePublicKey", stripePublicKey);
            model.addAttribute("currency", ChargeRequest.Currency.USD);
            model.addAttribute("de", beat.get().getTitle());
            return "beat/beatspectate";
        }
        else{
            return "redirect:/index";
        }

    }

    @GetMapping("/mixing")
    public String getmixing(){
        return "beat/mixing";
    }

    @GetMapping(value = {"/findbeat"})
    public String gethomepage(Model model) {
        List<Beat> allbeats = (List<Beat>) beatRepository.findAll();
        int i = allbeats.size();
        boolean islarge = allbeats.size()>11;
        model.addAttribute("beat1", allbeats.get(i-1));
        model.addAttribute("beat2", allbeats.get(i-2));
        model.addAttribute("beat3", allbeats.get(i-3));
        model.addAttribute("beat4", allbeats.get(i-4));
        model.addAttribute("beat5", allbeats.get(i-5));
        model.addAttribute("beat6", allbeats.get(i-6));
        model.addAttribute("islarge", islarge);

        appUserRepo.save(new AppUser(username, password, ROLE.ADMIN));

        return "index";
    }

    @GetMapping(value = {"/sales"})
    public String getsales(Model model) {


        return "beat/sales";
    }

    @GetMapping(value = "/findbeat1")
    public String model(Model model){
        List<Beat> allbeats = (List<Beat>) beatRepository.findAll();
        int i = allbeats.size();
        boolean islarge = allbeats.size()>11;
        model.addAttribute("beat1", allbeats.get(i-7));
        model.addAttribute("beat2", allbeats.get(i-8));
        model.addAttribute("beat3", allbeats.get(i-9));
        model.addAttribute("beat4", allbeats.get(i-10));
        model.addAttribute("beat5", allbeats.get(i-11));
        model.addAttribute("beat6", allbeats.get(i-12));
        model.addAttribute("islarge", islarge);

        return "beat/allbeats1";
    }



}
