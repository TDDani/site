package com.example.xowrld.Controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model) {
        return "main";
    }






}
