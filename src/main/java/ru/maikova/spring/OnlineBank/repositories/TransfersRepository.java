package ru.maikova.spring.OnlineBank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.maikova.spring.OnlineBank.models.Transfer;

import java.util.List;

@Repository
public interface TransfersRepository extends JpaRepository<Transfer, Integer> {
    List<Transfer> findAllByPersonId(final int personId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Transfer(name, card_from, card_to, sum, date, card_id, person_id) \n" +
            "VALUES(:name, :cardFrom, :cardTo, :sum, :date, :cardId, :personId)", nativeQuery = true)
    void addTransfer(@Param("name") String name, @Param("cardFrom") String cardFromNum,
                     @Param("cardTo") String cardToNum, @Param("sum") double sum,
                     @Param("date") String date, @Param("cardId") int cardId, @Param("personId") int personId);
}
