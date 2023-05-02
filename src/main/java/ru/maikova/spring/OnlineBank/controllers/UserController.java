package ru.maikova.spring.OnlineBank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.maikova.spring.OnlineBank.exceptions.AccountsNotFoundException;
import ru.maikova.spring.OnlineBank.exceptions.CardsNotFoundException;
import ru.maikova.spring.OnlineBank.exceptions.CreditsNotFoundException;
import ru.maikova.spring.OnlineBank.models.Card;
import ru.maikova.spring.OnlineBank.models.History;
import ru.maikova.spring.OnlineBank.models.Person;
import ru.maikova.spring.OnlineBank.services.*;
import ru.maikova.spring.OnlineBank.common.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final CardService cardService;
    private final PeopleService peopleService;
    private final CommonService commonService;

    @Autowired
    public UserController(CardService cardService, PeopleService peopleService, CommonService commonService) {
        this.cardService = cardService;
        this.peopleService = peopleService;
        this.commonService = commonService;
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("person", commonService.getAuthPerson());
        return "user/main";
    }

    @GetMapping("/history/cardSelection")
    public String cardSelection(Model model, @ModelAttribute Card card) {
        Person person = commonService.getAuthPerson();
        model.addAttribute("person", person);
        try {
            model.addAttribute("cards", peopleService.findAllCardsOfPerson(person.getId()));
        } catch (CardsNotFoundException e) {
            model.addAttribute("cardsErr", e.getMessage());
            return "user/main";
        }
        return "user/history";
    }

    @GetMapping("/history")
    public String history(Model model, @ModelAttribute Card card) {
        Person person = commonService.getAuthPerson();
        model.addAttribute("person", person);
        model.addAttribute("cards", peopleService.findAllNotNullCardsOfPerson(person.getId()));

        List<History> history = cardService.findAllOperationsByCard(card.getId());

        model.addAttribute("histories", history);
        return "user/history";
    }

    @GetMapping("/cards")
    public String cards(Model model) {
        Person person = commonService.getAuthPerson();
        model.addAttribute("person", person);

        try {
            model.addAttribute("cards", peopleService.findAllCardsOfPerson(person.getId()));
        } catch (CardsNotFoundException e) {
            model.addAttribute("cardsErr", e.getMessage());
            return "user/main";
        }
        return "user/cards";
    }

    @GetMapping("/accounts")
    public String accounts(Model model) {
        Person person = commonService.getAuthPerson();
        model.addAttribute("person", person);

        try {
            model.addAttribute("accounts", peopleService.findAllAccountsOfPerson(person.getId()));
        } catch (AccountsNotFoundException e) {
            model.addAttribute("accountsErr", e.getMessage());
        }
        return "user/accounts";
    }

    @GetMapping("/credits")
    public String credits(Model model) {
        Person person = commonService.getAuthPerson();
        model.addAttribute("person", person);

        try {
            model.addAttribute("credits", peopleService.findAllCreditsOfPerson(person.getId()));
        } catch (CreditsNotFoundException e) {
            model.addAttribute("creditsErr", e.getMessage());
        }
        return "user/credits";
    }

    @GetMapping("/payments")
    public String payments(Model model,
                           @ModelAttribute("card") Card card,
                           @ModelAttribute("paymentOptions") PaymentOptions paymentOptions) {
        Person person = commonService.getAuthPerson();
        model.addAttribute("person", person);
        try {
            model.addAttribute("cards", peopleService.findAllCardsOfPerson(person.getId()));
        } catch (CardsNotFoundException e) {
            model.addAttribute("cardsErr", e.getMessage());
            return "user/main";
        }
        return "user/payments";
    }

    @GetMapping("/payments/options")
    public String paymentsOptions(Model model,
                                  @ModelAttribute("card") Card card,
                                  @ModelAttribute("paymentOptions") PaymentOptions paymentOptions,
                                  @ModelAttribute("phoneCommOptions") PhoneCommOptions phoneCommOptions,
                                  RedirectAttributes redirectAttributes) {
        Person person = commonService.getAuthPerson();

        model.addAttribute("person", person);
        model.addAttribute("cards", peopleService.findAllNotNullCardsOfPerson(person.getId()));
        model.addAttribute("paymentOption", paymentOptions);
        redirectAttributes.addFlashAttribute(paymentOptions);

        if (paymentOptions.selectedPaymentOption.equals(paymentOptions.getPaymentOptions().get(0)))
            return "redirect:/user/payments/phoneComm";
        else if (paymentOptions.selectedPaymentOption.equals(paymentOptions.getPaymentOptions().get(1)))
            return "redirect:/user/payments/fine";
        else return "redirect:/user/payments/commService";
    }
}
