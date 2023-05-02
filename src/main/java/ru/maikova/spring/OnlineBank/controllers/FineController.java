package ru.maikova.spring.OnlineBank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maikova.spring.OnlineBank.models.Card;
import ru.maikova.spring.OnlineBank.models.Person;
import ru.maikova.spring.OnlineBank.services.CardService;
import ru.maikova.spring.OnlineBank.services.FineService;
import ru.maikova.spring.OnlineBank.services.PeopleService;
import ru.maikova.spring.OnlineBank.common.CurrentDateTimeFormat;
import ru.maikova.spring.OnlineBank.common.PaymentOptions;
import ru.maikova.spring.OnlineBank.common.CommonService;

@Controller
@RequestMapping("/user/payments")
public class FineController {
    private final PeopleService peopleService;
    private final FineService fineService;
    private final CardService cardService;
    private final CommonService commonService;

    @Autowired
    public FineController(PeopleService peopleService, FineService fineService,
                          CardService cardService, CommonService commonService) {
        this.fineService = fineService;
        this.peopleService = peopleService;
        this.cardService = cardService;
        this.commonService = commonService;
    }

    @GetMapping("/fine")
    public String fines(Model model,
                        @ModelAttribute("card") Card card,
                        @ModelAttribute("paymentOptions") PaymentOptions paymentOptions) {
        Person person = commonService.getAuthPerson();
        model.addAttribute("person", person);
        model.addAttribute("cards", peopleService.findAllNotNullCardsOfPerson(person.getId()));
        return "user/paymentsFines";
    }

    @PostMapping("/fine")
    public String payFine(Model model,
                          @RequestParam("docNum") String docNum,
                          @RequestParam("sum") Double sum,
                          @ModelAttribute("card") Card card,
                          @ModelAttribute("paymentOptions") PaymentOptions paymentOptions) {
        Person person = commonService.getAuthPerson();

        fineService.addFine(paymentOptions.getSelectedFineOption(), sum, docNum,
                CurrentDateTimeFormat.currentDate, card.getId(), person.getId());

        card = cardService.findCardById(card.getId());
        cardService.setCardSumById(card.getSum() - sum, card.getId());

        model.addAttribute("person", person);
        model.addAttribute("cards", peopleService.findAllNotNullCardsOfPerson(person.getId()));
        return "redirect:/user/payments";
    }
}
