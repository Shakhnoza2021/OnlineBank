package ru.maikova.spring.OnlineBank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.maikova.spring.OnlineBank.models.Card;
import ru.maikova.spring.OnlineBank.models.History;

import java.util.List;

@Repository
public interface CardsRepository extends JpaRepository<Card, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE card SET card.sum =:sum WHERE card.id = :cardId", nativeQuery = true)
    void setCardSumById(@Param("sum") double sum, @Param("cardId") int cardId);

    String cardHistoryQuery =
            "SELECT h.name as name, h.sum as sum, card.name as cardName, h.date as date FROM (\n" +
            "SELECT name, sum, card_id, date FROM transfer \n" +
            "UNION All SELECT name,sum,card_id, date FROM phone_communication \n" +
            "UNION All SELECT name, sum,card_id, date FROM fine \n" +
            "UNION All SELECT name, sum,card_id, date FROM communal_service) \n" +
            "as h \n" +
            "JOIN card ON card.id=h.card_id \n" +
            "WHERE h.card_id = :cardId \n" +
            "ORDER BY date DESC;";

    //История операций по карте
    @Query(value = cardHistoryQuery, nativeQuery = true)
    List<History> findAllOperationsByCardId(int cardId);

    @Query(value = "SELECT * FROM Card WHERE person_id=:personId AND main_card=1", nativeQuery = true)
    Card findMainCardByPersonId(int personId);

//    @Query(value = "UPDATE Card SET card.sum=:sum WHERE person_id=:personId AND main_card=1", nativeQuery = true)
//    Card setMainCardSumByPersonId(@Param("sum") double sum, @Param("personId") int personId);
}
