package ru.maikova.spring.OnlineBank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maikova.spring.OnlineBank.models.Card;
import ru.maikova.spring.OnlineBank.models.Person;
import ru.maikova.spring.OnlineBank.services.*;
import ru.maikova.spring.OnlineBank.common.*;

@Controller
@RequestMapping("/user/payments")
public class PhoneCommunicationController {
    private final CardService cardService;
    private final PeopleService peopleService;
    private final CommonService commonService;
    private final PhoneCommunicationService phoneCommService;

    @Autowired
    public PhoneCommunicationController(CardService cardService, PeopleService peopleService,
                                        CommonService commonService, PhoneCommunicationService phoneCommService) {
        this.cardService = cardService;
        this.peopleService = peopleService;
        this.commonService = commonService;
        this.phoneCommService = phoneCommService;
    }

    @GetMapping("/phoneComm")
    public String paymentsPhoneComm(Model model,
                                    @ModelAttribute("card") Card card,
                                    @ModelAttribute("phoneCommOptions") PhoneCommOptions phoneCommOptions,
                                    @ModelAttribute("paymentOptions") PaymentOptions paymentOptions) {
        Person person = commonService.getAuthPerson();
        model.addAttribute("person", person);
        model.addAttribute("cards", peopleService.findAllNotNullCardsOfPerson(person.getId()));
        return "user/paymentsPhoneComm";
    }

    @PostMapping("/phoneComm")
    public String payPhoneComm(Model model,
                               @RequestParam("phoneNumber") String phoneNumber,
                               @RequestParam("sum") Double sum,
                               @ModelAttribute("card") Card card,
                               @ModelAttribute("paymentOptions") PaymentOptions paymentOptions) {
        Person person = commonService.getAuthPerson();

        model.addAttribute("person", person);
        model.addAttribute("cards", peopleService.findAllNotNullCardsOfPerson(person.getId()));

        card = cardService.findCardById(card.getId());
        cardService.setCardSumById(card.getSum() - sum, card.getId());
        phoneCommService.addPhoneCommunication(paymentOptions.getSelectedPaymentOption(),
                paymentOptions.getSelectedPhoneCommOption(), phoneNumber, sum, CurrentDateTimeFormat.currentDate,
                card.getId(), person.getId());

        return "redirect:/user/payments";
    }
}
