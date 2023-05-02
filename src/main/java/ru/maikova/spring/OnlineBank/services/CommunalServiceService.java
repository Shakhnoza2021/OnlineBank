package ru.maikova.spring.OnlineBank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maikova.spring.OnlineBank.repositories.CommunalServicesRepository;

@Service
public class CommunalServiceService {
    private final CommunalServicesRepository comServRepository;

    @Autowired
    public CommunalServiceService(CommunalServicesRepository comServRepository) {
        this.comServRepository = comServRepository;
    }

    public void addCommService(String name, double sum, String company, String personalAcc,
                               String docNum, String date, int cardId, int personId) {
        comServRepository.addCommService(name, sum, company, personalAcc, docNum, date, cardId, personId);
    }
}
