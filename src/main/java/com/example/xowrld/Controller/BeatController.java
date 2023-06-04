package com.example.xowrld.Controller;

import com.example.xowrld.Config.TokenGenerator;
import com.example.xowrld.EmailSenderService.EmailSenderService;
import com.example.xowrld.Model.*;
import com.example.xowrld.Repository.AppUserRepo;
import com.example.xowrld.Repository.BeatRepository;
import com.example.xowrld.Repository.PurchaseRepo;
import com.example.xowrld.Service.RawGoogleDriveLink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class BeatController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @Autowired
    private AppUserRepo appUserRepo;



    @Autowired
    private BeatRepository beatRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private PurchaseRepo purchaseRepo;

    @Autowired
    private RawGoogleDriveLink googleDriveLink;

    public BeatController(RawGoogleDriveLink googleDriveLink) {
        this.googleDriveLink = googleDriveLink;
    }

    @GetMapping("/newbeat")
    private String createnew(Model model){
        model.addAttribute("beat", new Beat());

        return "beat/newbeat";
    }

    @PostMapping("/newbeat")
    private String savenew(Beat beat) throws IOException {
        Beat beat1 = beat;
       String previewurl =  googleDriveLink.getRawLink(beat1.getPreviewurl());
       beat1.setPreviewurl(previewurl);
        String header =  googleDriveLink.getRawLink(beat1.getHeader());
        beat1.setHeader(header);
        beatRepository.save(beat1);
        return "redirect:/";
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



    @GetMapping("/viewbeat/{id}")
    public String spectatebeat(@PathVariable("id") Long id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Beat> beat = beatRepository.getById(id);
        if(beat.isPresent()){
            model.addAttribute("beat", beat.get());
            model.addAttribute("amount",  beat.get().getPrice()*1000); // in cents
            model.addAttribute("stripePublicKey", stripePublicKey);
            model.addAttribute("currency", ChargeRequest.Currency.USD);
            model.addAttribute("de", beat.get().getTitle());
            if(authentication instanceof UsernamePasswordAuthenticationToken) {
                AppUser currentUser = (AppUser) authentication.getPrincipal();
                Optional<AppUser> user = appUserRepo.findById(currentUser.getId());
                model.addAttribute("points",  user.get().getFloaters());
                model.addAttribute("userid",  currentUser.getId());
                model.addAttribute("redirectlink",  "/myaccount");
                return "beat/spectatebeat";
            } else {
                model.addAttribute("points", "LOGIN");
                model.addAttribute("redirectlink",  "/login");
                model.addAttribute("myDateTime", "2023-04-23T16:00:00");
                model.addAttribute("userid", "");
                return "beat/spectatebeat";
            }
        }

            return "redirect:/";




    }

    @GetMapping("/mixing")
    public String getmixing(){
        return "beat/mixing";
    }

    @GetMapping(value = {"/beats"})
    public String gethomepage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String points;
        String redirectlink;
        if(authentication instanceof UsernamePasswordAuthenticationToken) {
            AppUser currentUser = (AppUser) authentication.getPrincipal();
            Optional<AppUser> user = appUserRepo.findById(currentUser.getId());
            points = String.valueOf(user.get().getFloaters());
            redirectlink = "/myaccount";
        } else {

            points =  "LOGIN";
            redirectlink = "/myaccount";

        }
        model.addAttribute("points", points);
        model.addAttribute("redirectlink",  redirectlink);

        List<Beat> allbeats = (List<Beat>) beatRepository.findAll();
        int i = allbeats.size();
        boolean islarge = allbeats.size()>16;
        model.addAttribute("searchmessage", "");
        model.addAttribute("beat1", allbeats.get(i-1));
        model.addAttribute("beat2", allbeats.get(i-2));
        model.addAttribute("beat3", allbeats.get(i-3));
        model.addAttribute("beat4", allbeats.get(i-4));
        model.addAttribute("beat5", allbeats.get(i-5));
        model.addAttribute("beat6", allbeats.get(i-6));
        model.addAttribute("beat6", allbeats.get(i-6));
        model.addAttribute("beat6", allbeats.get(i-6));
        model.addAttribute("beat7", allbeats.get(i-7));
        model.addAttribute("beat8", allbeats.get(i-8));
        model.addAttribute("islarge", islarge);
        model.addAttribute("false", false);


        return "beat/beats1";
    }

    @GetMapping("/filterbeats")
    public String filter(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String points;
        String redirectlink;
        if(authentication instanceof UsernamePasswordAuthenticationToken) {
            AppUser currentUser = (AppUser) authentication.getPrincipal();
            Optional<AppUser> user = appUserRepo.findById(currentUser.getId());
            points = String.valueOf(user.get().getFloaters());
            redirectlink = "/myaccount";
        } else {

            points =  "LOGIN";
            redirectlink = "/myaccount";

        }
        model.addAttribute("points", points);
        model.addAttribute("redirectlink",  redirectlink);

        model.addAttribute("searchmessage", "MOST FITTING SEARCH RESULTS....");
        List<Beat> allbeats = (List<Beat>) beatRepository.findAll();
        int i = allbeats.size();
        boolean islarge = allbeats.size()>16;
        model.addAttribute("beat1", allbeats.get(i-1));
        model.addAttribute("beat2", allbeats.get(i-2));
        model.addAttribute("beat3", allbeats.get(i-3));
        model.addAttribute("beat4", allbeats.get(i-4));
        model.addAttribute("beat5", allbeats.get(i-5));
        model.addAttribute("beat6", allbeats.get(i-6));
        model.addAttribute("beat6", allbeats.get(i-6));
        model.addAttribute("beat7", allbeats.get(i-7));
        model.addAttribute("beat8", allbeats.get(i-8));

        model.addAttribute("islarge", islarge);
        model.addAttribute("false", false);

        return "beat/beats1";
    }

    @GetMapping(value = {"/sales"})
    public String getsales(Model model) {


        return "beat/sales";
    }

    @GetMapping("/verifypurchase/{id}/{userid}")
    private String verifypurchase(@PathVariable("id") Long id, @PathVariable("userid") Long userid, Model model) throws MessagingException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String points;
        String redirectlink;
        if(authentication instanceof UsernamePasswordAuthenticationToken) {
            AppUser currentUser = (AppUser) authentication.getPrincipal();
            Optional<AppUser> user = appUserRepo.findById(currentUser.getId());
            points = String.valueOf(user.get().getFloaters());
            redirectlink = "/myaccount";
        } else {

            points =  "LOGIN";
            redirectlink = "/myaccount";

        }
        model.addAttribute("points", points);
        model.addAttribute("redirectlink",  redirectlink);

        Optional<Beat> beat = beatRepository.findById(id);
        Optional<AppUser> appUser = appUserRepo.findById(userid);
        model.addAttribute("beat", beat.get());
        model.addAttribute("user", appUser.get());
        model.addAttribute("errormessage",  "");
        emailSenderService.verifypurchase(appUser.get().getEmail(), appUser.get().getVerificationcode());
        return "register/confirmpurchase";

    }

    @PostMapping("/verifypurchase/{id}")
    private String send(@PathVariable("id") Long id, Model model, @RequestParam("code") String code, @RequestParam("username") String username) throws MessagingException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String points;
        String redirectlink;
        if(authentication instanceof UsernamePasswordAuthenticationToken) {
            AppUser currentUser = (AppUser) authentication.getPrincipal();
            Optional<AppUser> user = appUserRepo.findById(currentUser.getId());
            points = String.valueOf(user.get().getFloaters());
            redirectlink = "/myaccount";
        } else {

            points =  "LOGIN";
            redirectlink = "/myaccount";

        }
        model.addAttribute("points", points);
        model.addAttribute("redirectlink",  redirectlink);

        Optional<Beat> beat = beatRepository.findById(id);
        Optional<AppUser> appUser = appUserRepo.findByUsername(username);
        if(Objects.equals(appUser.get().getVerificationcode(), code)) {
            model.addAttribute("user", appUser.get());
            model.addAttribute("beat", beat.get());
            emailSenderService.beatbuyemail(appUser.get().getEmail(), "Succesfull purchase", beat.get().getAccessurl());
            appUser.get().setFloaters(appUser.get().getFloaters() - beat.get().getPrice());
            appUserRepo.save(appUser.get());
            LocalDateTime nowdate = LocalDateTime.now();
            ;        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime formattedDateTime = LocalDateTime.parse(nowdate.format(formatter));
            purchaseRepo.save(new Purchase("Sold beat: " + beat.get().getTitle(), "15", formattedDateTime));
            return "beat/thanks4purchase";
        }
        else{


            model.addAttribute("user", appUser.get());
            model.addAttribute("beat", beat.get());
            model.addAttribute("errormessage",  "Wrong code");
            appUser.get().setVerificationcode(TokenGenerator.generateToken());
            appUserRepo.save(appUser.get());
            emailSenderService.verifypurchase(appUser.get().getEmail(), appUser.get().getVerificationcode());
           return  "register/confirmpurchase";
        }

    }


}
