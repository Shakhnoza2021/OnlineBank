package ru.maikova.spring.OnlineBank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maikova.spring.OnlineBank.models.Card;
import ru.maikova.spring.OnlineBank.models.Person;
import ru.maikova.spring.OnlineBank.services.CardService;
import ru.maikova.spring.OnlineBank.services.CommunalServiceService;
import ru.maikova.spring.OnlineBank.services.PeopleService;
import ru.maikova.spring.OnlineBank.common.CurrentDateTimeFormat;
import ru.maikova.spring.OnlineBank.common.PaymentOptions;
import ru.maikova.spring.OnlineBank.common.CommonService;

@Controller
@RequestMapping("/user/payments")
public class CommunalServiceController {
    private final CardService cardService;
    private final PeopleService peopleService;
    private final CommunalServiceService commServService;
    private final CommonService commonService;

    @Autowired
    public CommunalServiceController(CardService cardService, PeopleService peopleService,
                                     CommunalServiceService commServService, CommonService commonService) {
        this.cardService = cardService;
        this.peopleService = peopleService;
        this.commServService = commServService;
        this.commonService = commonService;
    }

    @GetMapping("/commService")
    public String commServices(Model model,
                               @ModelAttribute("card") Card card,
                               @ModelAttribute("paymentOptions") PaymentOptions paymentOptions) {
        Person person = commonService.getAuthPerson();
        model.addAttribute("person", person);
        model.addAttribute("cards", peopleService.findAllNotNullCardsOfPerson(person.getId()));
        return "user/paymentsCommServices";
    }

    @PostMapping("/commService")
    public String payCommService(Model model,
                                 @RequestParam("personalAccount") String personalAccount,
                                 @RequestParam("docNum") String docNum,
                                 @RequestParam("sum") Double sum,
                                 @ModelAttribute("card") Card card,
                                 @ModelAttribute("paymentOptions") PaymentOptions paymentOptions) {
        Person person = commonService.getAuthPerson();

        commServService.addCommService(paymentOptions.getSelectedCommServOption(), sum,
                paymentOptions.getSelectedCommServCompany(), personalAccount, docNum,
                CurrentDateTimeFormat.currentDate, card.getId(), person.getId());

        card = cardService.findCardById(card.getId());
        cardService.setCardSumById(card.getSum() - sum, card.getId());

        model.addAttribute("person", person);
        model.addAttribute("cards", peopleService.findAllNotNullCardsOfPerson(person.getId()));
        return "redirect:/user/payments";
    }
}
