package ru.maikova.spring.OnlineBank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maikova.spring.OnlineBank.common.CommonService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    private final CommonService commonService;

    public ManagerController(CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("person", commonService.getAuthPerson());
        return "manager/main";
    }
}
