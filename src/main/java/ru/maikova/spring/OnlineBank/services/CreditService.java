package ru.maikova.spring.OnlineBank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maikova.spring.OnlineBank.models.Credit;
import ru.maikova.spring.OnlineBank.repositories.CreditsRepository;

import java.util.List;

@Service
public class CreditService {
    private final CreditsRepository creditsRepository;

    @Autowired
    public CreditService(CreditsRepository creditsRepository) {
        this.creditsRepository = creditsRepository;
    }

    public List<Credit> findAllCreditsOfPerson(int personId) {
        return creditsRepository.findAllByPersonId(personId);
    }
}
