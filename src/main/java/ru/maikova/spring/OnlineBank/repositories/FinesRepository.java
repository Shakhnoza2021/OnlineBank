package ru.maikova.spring.OnlineBank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.maikova.spring.OnlineBank.models.Fine;

import java.util.List;

@Repository
public interface FinesRepository extends JpaRepository<Fine, Integer> {
    List<Fine> findAllByPersonId(final int personId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Fine(name, company, sum, doc_number, date, card_id, person_id) \n" +
            "VALUES('Штрафы', :company, :sum, :docNum, :date, :cardId, :personId)", nativeQuery = true)
    void addFine(@Param("company") String company, @Param("sum") double sum, @Param("docNum") String docNum,
                 @Param("date") String date, @Param("cardId") int cardId, @Param("personId") int personId);
}
