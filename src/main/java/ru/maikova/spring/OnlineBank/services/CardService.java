package ru.maikova.spring.OnlineBank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import ru.maikova.spring.OnlineBank.models.Card;
import ru.maikova.spring.OnlineBank.models.History;
import ru.maikova.spring.OnlineBank.models.Person;
import ru.maikova.spring.OnlineBank.repositories.CardsRepository;
import ru.maikova.spring.OnlineBank.common.CurrentDateTimeFormat;
import ru.maikova.spring.OnlineBank.common.TransferOptions;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    private final CardsRepository cardsRepository;

    @Autowired
    public CardService(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    public Card findCardById(int id) {
        Optional<Card> card = cardsRepository.findById(id);
        return card.get();
    }

    public void setCardSumById(double sum, int cardId) {
        cardsRepository.setCardSumById(sum, cardId);
    }

    public Card findMainCardByPersonId(int personID) {
        return cardsRepository.findMainCardByPersonId(personID);
    }

    public List<History> findAllOperationsByCard(int cardId) {
        PagedListHolder<History> pages = new PagedListHolder(cardsRepository.findAllOperationsByCardId(cardId));
        //TODO: set the number of posts per page

        int pageCount = pages.getPageCount();
        pages.setPage(0); //set current page number
        pages.setPageSize(100); // set the size of page
        return pages.getPageList();
    }

    public void transferMoneyBetweenCards(Card withdrawCard, Card transferCard, double sum,
                                          TransferOptions transferOptions, TransferService transferService,
                                          Person person) {
        setCardSumById(withdrawCard.getSum() - sum, withdrawCard.getId());
        setCardSumById(transferCard.getSum() + sum, transferCard.getId());

        String transferName = TransferOptions.options.get(transferOptions.getSelectedOption());
        transferService.addTransfer(transferName, withdrawCard.getNumber(), transferCard.getNumber(),
                sum, CurrentDateTimeFormat.currentDate, withdrawCard.getId(), person.getId());
    }
}
