package ru.maikova.spring.OnlineBank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maikova.spring.OnlineBank.exceptions.AccountsNotFoundException;
import ru.maikova.spring.OnlineBank.exceptions.CardsNotFoundException;
import ru.maikova.spring.OnlineBank.exceptions.CreditsNotFoundException;
import ru.maikova.spring.OnlineBank.exceptions.PersonNotFoundException;
import ru.maikova.spring.OnlineBank.models.*;
import ru.maikova.spring.OnlineBank.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Person findPersonByPhoneNumber(String phoneNum) throws PersonNotFoundException {
        Optional<Person> person = peopleRepository.findByPhoneNumber(phoneNum);

        if (person.isEmpty()){
            throw new PersonNotFoundException("Пользователь не найден");}

        return person.get();
    }

    public Person findPersonById(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        return person.get();
    }

    public List<Card> findAllNotNullCardsOfPerson(int personId) {
        return peopleRepository.findAllCardsByPersonId(personId).getCards();
    }

    public List<Card> findAllCardsOfPerson(int personId) throws CardsNotFoundException {
        try {
            return peopleRepository.findAllCardsByPersonId(personId).getCards();
        } catch (NullPointerException e) {
            throw new CardsNotFoundException("Карты не найдены");
        }
    }

    public List<Account> findAllAccountsOfPerson(int personId) throws AccountsNotFoundException {
        try {
            return peopleRepository.findAllAccountsByPersonId(personId).getAccounts();
        } catch (NullPointerException e) {
            throw new AccountsNotFoundException("Счета не найдены");
        }
    }

    public List<Credit> findAllCreditsOfPerson(int personId) throws CreditsNotFoundException {
        try {
            return peopleRepository.findAllCreditsByPersonId(personId).getCredits();
        } catch (NullPointerException e) {
            throw new CreditsNotFoundException("Кредиты не найдены");
        }
    }

}
