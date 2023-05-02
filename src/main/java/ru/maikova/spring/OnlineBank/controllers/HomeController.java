package ru.maikova.spring.OnlineBank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maikova.spring.OnlineBank.security.PersonDetails;
import ru.maikova.spring.OnlineBank.common.CommonService;

@Controller
@RequestMapping("/main")
public class HomeController {
    private final CommonService commonService;

    @Autowired
    public HomeController(CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping("/home")
    public String index() {
        return "index";
    }

    @GetMapping("/deposit")
    public String deposit() {
        return "deposit";
    }

    @GetMapping("/credit")
    public String credit() {
        return "credit";
    }

    @GetMapping("/hypothec")
    public String hypothec() {
        return "hypothec";
    }

    @GetMapping("/card")
    public String card() {
        return "card";
    }

    @GetMapping("/autocredit")
    public String autocredit() {
        return "autocredit";
    }

    @GetMapping("/payment")
    public String payment() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof PersonDetails)
            return "redirect:/user/payments";
        return "redirect:/auth/login";
    }

    @GetMapping("/transfer")
    public String transfer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof PersonDetails)
            return "redirect:/user/transfer";
        return "redirect:/auth/login";
    }
}
