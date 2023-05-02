package ru.maikova.spring.OnlineBank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maikova.spring.OnlineBank.models.Fine;
import ru.maikova.spring.OnlineBank.repositories.FinesRepository;

import java.util.List;

@Service
public class FineService {
    private final FinesRepository finesRepository;

    @Autowired
    public FineService(FinesRepository finesRepository) {
        this.finesRepository = finesRepository;
    }

    public List<Fine> findAllFinesOfPerson(int personId) {
        return finesRepository.findAllByPersonId(personId);
    }

    public void addFine(String company, double sum, String docNum, String date, int cardId, int personId) {
        finesRepository.addFine(company, sum, docNum, date, cardId, personId);
    }
}
