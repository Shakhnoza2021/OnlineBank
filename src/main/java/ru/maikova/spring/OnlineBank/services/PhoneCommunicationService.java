package ru.maikova.spring.OnlineBank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maikova.spring.OnlineBank.models.PhoneCommunication;
import ru.maikova.spring.OnlineBank.repositories.PhoneCommunicationsRepository;

import java.util.List;

@Service
public class PhoneCommunicationService {
    private final PhoneCommunicationsRepository phoneCommRepository;

    @Autowired
    public PhoneCommunicationService(PhoneCommunicationsRepository phoneCommRepository) {
        this.phoneCommRepository = phoneCommRepository;
    }

    public List<PhoneCommunication> findAllPhoneCommOfPerson(int personId) {
        return phoneCommRepository.findAllByPersonId(personId);
    }

    public void addPhoneCommunication(String name, String provider, String phoneNum, double sum,
                                      String date, int cardId, int personId) {
        phoneCommRepository.addPhoneCommunication(name, provider, phoneNum, sum, date, cardId, personId);
    }
}
