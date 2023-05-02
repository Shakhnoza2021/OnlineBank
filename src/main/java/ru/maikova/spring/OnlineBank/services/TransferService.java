package ru.maikova.spring.OnlineBank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maikova.spring.OnlineBank.models.Transfer;
import ru.maikova.spring.OnlineBank.repositories.TransfersRepository;

import java.util.List;

@Service
public class TransferService {
    private final TransfersRepository transfersRepository;

    @Autowired
    public TransferService(TransfersRepository transfersRepository) {
        this.transfersRepository = transfersRepository;
    }

    public List<Transfer> findAllTransfersOfPerson(int personId) {
        return transfersRepository.findAllByPersonId(personId);
    }

    public void addTransfer(String name, String cardFromNum, String cardToNum, double sum,
                            String date, int cardId, int personId) {
        transfersRepository.addTransfer(name, cardFromNum, cardToNum, sum, date, cardId, personId);
    }
}
