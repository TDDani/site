package com.example.xowrld.Controller;

import com.example.xowrld.EmailSenderService.EmailSenderService;
import com.example.xowrld.Model.ChargeRequest;
import com.example.xowrld.Model.SoldBeat;
import com.example.xowrld.Repository.BeatRepository;
import com.example.xowrld.Repository.SoldBeatRepository;
import com.example.xowrld.Service.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.time.LocalDate;

@Controller
public class ChargeController {

    @Autowired
    private StripeService paymentsService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private BeatRepository beatRepository;

    @Autowired
    private SoldBeatRepository soldBeatRepository;

    @GetMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model, @RequestParam("title") Long id)
            throws StripeException, MessagingException {
        chargeRequest.setCurrency(ChargeRequest.Currency.USD);
        Charge charge = paymentsService.charge(chargeRequest);
        charge.setDescription(chargeRequest.getDescription());
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        model.addAttribute("receipt",chargeRequest.getStripeEmail());
        emailSenderService.beatbuyemail(chargeRequest.getStripeEmail(), "", "Th", beatRepository.getById(id).get().getAccessurl());
        LocalDate localD = LocalDate.now();
        soldBeatRepository.save(new SoldBeat(beatRepository.getById(id).get().getTitle(), beatRepository.getById(id).get().getPrice(), localD.toString()));
        if(beatRepository.getById(id).isPresent()){
            beatRepository.delete(beatRepository.getById(id).get());
        }





        return "beat/thanks4purchase";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}