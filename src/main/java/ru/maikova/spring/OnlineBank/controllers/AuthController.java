package ru.maikova.spring.OnlineBank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maikova.spring.OnlineBank.models.Person;
import ru.maikova.spring.OnlineBank.security.PersonDetails;
import ru.maikova.spring.OnlineBank.common.CommonService;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final CommonService commonService;

    @Autowired
    public AuthController(CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping("/login")
    public String loginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof PersonDetails){
            Person person = ((PersonDetails) principal).getPerson();
            if (person.getRole().getName().equals("ROLE_MANAGER"))
                return "redirect:/manager/main";
            else return "redirect:/user/main";
        }
        return "login";
    }

    @GetMapping("/loginSuccess")
    public String homePage() {
        Person person = commonService.getAuthPerson();
            if (person.getRole().getName().equals("ROLE_MANAGER"))
                return "redirect:/manager/main";
            else return "redirect:/user/main";
    }

//    @GetMapping("/login_error")
//    public String loginError(HttpServletRequest request, Model model) {
//        HttpSession session = request.getSession(false);
//        String errorMessage = null;
//        if (session != null) {
//            AuthenticationException ex = (AuthenticationException) session
//                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//            if (ex != null) {
//                errorMessage = ex.getMessage();
//            }
//        }
//        model.addAttribute("errorMessage", errorMessage);
//        return "login";
//    }
}
