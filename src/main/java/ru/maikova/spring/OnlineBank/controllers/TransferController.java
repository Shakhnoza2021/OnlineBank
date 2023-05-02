package ru.maikova.spring.OnlineBank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maikova.spring.OnlineBank.exceptions.CardsNotFoundException;
import ru.maikova.spring.OnlineBank.exceptions.PersonNotFoundException;
import ru.maikova.spring.OnlineBank.models.Card;
import ru.maikova.spring.OnlineBank.models.Person;
import ru.maikova.spring.OnlineBank.services.*;
import ru.maikova.spring.OnlineBank.common.CommonService;
import ru.maikova.spring.OnlineBank.common.TransferCards;
import ru.maikova.spring.OnlineBank.common.TransferOptions;

@Controller
@RequestMapping("/user")
public class TransferController {
    private final CardService cardService;
    private final PeopleService peopleService;
    private final TransferService transferService;
    private final CommonService commonService;

    @Autowired
    public TransferController(CardService cardService, PeopleService peopleService,
                              TransferService transferService, CommonService commonService) {
        this.cardService = cardService;
        this.peopleService = peopleService;
        this.transferService = transferService;
        this.commonService = commonService;
    }

    @GetMapping("/transfer")
    public String transfer(Model model, @ModelAttribute("transferOptions") TransferOptions transferOptions) {
        Person person = commonService.getAuthPerson();
        model.addAttribute("person", person);
        try {
            model.addAttribute("cards", peopleService.findAllCardsOfPerson(person.getId()));
        } catch (CardsNotFoundException e) {
            model.addAttribute("cardsErr", e.getMessage());
            return "user/main";
        }
        return "user/transfer";
    }

    @GetMapping("/transfer/options")
    public String options(Model model, @ModelAttribute("transferOptions") TransferOptions transferOptions) {
        Person person = commonService.getAuthPerson();
        model.addAttribute("person", person);
        model.addAttribute("cards", peopleService.findAllNotNullCardsOfPerson(person.getId()));

        if (transferOptions.getSelectedOption() == 1)
            return "redirect:/user/transferBtwAcc";
        else return "redirect:/user/transferToPerson";
    }

    @GetMapping("/transferBtwAcc")
    public String betweenAccounts(Model model,
                                  @ModelAttribute("transferCards") TransferCards transferCards,
                                  @ModelAttribute("transferOptions") TransferOptions transferOptions) {
        Person person = commonService.getAuthPerson();
        model.addAttribute("person", person);
        model.addAttribute("cards", peopleService.findAllNotNullCardsOfPerson(person.getId()));
        return "user/transferBtwAcc";
    }

    @PostMapping("/transferBtwAcc")
    public String transferBetweenAccounts(Model model,
                                          @RequestParam(required = false, name = "sum") Double sum,
                                          @ModelAttribute("transferCards") TransferCards transferCards,
                                          @ModelAttribute("transferOptions") TransferOptions transferOptions) {
        Person person = commonService.getAuthPerson();
        Card withdrawCard = cardService.findCardById(transferCards.getWithdrawCard());
        Card transferCard = cardService.findCardById(transferCards.getTransferCard());

        cardService.transferMoneyBetweenCards(withdrawCard, transferCard, sum, transferOptions ,transferService ,person);

        model.addAttribute("person", person);
        model.addAttribute("cards", peopleService.findAllNotNullCardsOfPerson(person.getId()));
        return "redirect:/user/transfer";
    }

    @GetMapping("/transferToPerson")
    public String toPerson(Model model,
                           @ModelAttribute("transferPerson") Person transferPerson,
                           @ModelAttribute("transferOptions") TransferOptions transferOptions) {
        Person person = commonService.getAuthPerson();
        model.addAttribute("person", person);
        model.addAttribute("cards", peopleService.findAllNotNullCardsOfPerson(person.getId()));
        return "user/transferToPerson";
    }

    @GetMapping("/transferToPerson/findByPhone")
    public String getTransferPerson(Model model,
                                    @ModelAttribute("transferPerson") Person transferPerson,
                                    @ModelAttribute("transferOptions") TransferOptions transferOptions,
                                    @ModelAttribute("card") Card card) {
        Person person = commonService.getAuthPerson();

        model.addAttribute("person", person);
        model.addAttribute("cards", peopleService.findAllNotNullCardsOfPerson(person.getId()));

        try {
            transferPerson= peopleService.findPersonByPhoneNumber(transferPerson.getPhoneNumber());

        } catch (PersonNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "user/transferToPerson";
        }

        model.addAttribute("transferPerson", transferPerson);
        return "user/transferToPersonForm";
    }

    @PostMapping("/transferToPerson")
    public String transferToPerson(Model model,
                                   @RequestParam(required = false, name = "sum") Double sum,
                                   @RequestParam(required = false, name = "transferPersonId") Integer transferPersonId,
                                   @ModelAttribute("card") Card card,
                                   @ModelAttribute("transferOptions") TransferOptions transferOptions) {
        Person person = commonService.getAuthPerson();
        Person transferPerson = peopleService.findPersonById(transferPersonId);

        Card personCard = cardService.findCardById(card.getId());
        Card transferCard = cardService.findMainCardByPersonId(transferPerson.getId());

        cardService.transferMoneyBetweenCards(personCard, transferCard, sum, transferOptions, transferService, person);

        model.addAttribute("person", person);
        model.addAttribute("cards", peopleService.findAllNotNullCardsOfPerson(person.getId()));
        return "redirect:/user/transfer";
    }
}
