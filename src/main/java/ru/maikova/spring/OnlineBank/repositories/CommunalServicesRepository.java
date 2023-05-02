package ru.maikova.spring.OnlineBank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.maikova.spring.OnlineBank.models.CommunalService;

import java.util.List;

@Repository
public interface CommunalServicesRepository extends JpaRepository<CommunalService, Integer> {
    List<CommunalService> findAllByPersonId(final int personId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Communal_service(name, sum, company, personal_account, doc_number, date, card_id, person_id)\n" +
            "    VALUES(:name, :sum, :company, :personalAcc, :docNum, :date, :cardId, :personId)", nativeQuery = true)
    void addCommService(@Param("name") String name, @Param("sum") double sum,
                        @Param("company") String company, @Param("personalAcc") String personalAcc,
                        @Param("docNum") String docNum, @Param("date") String date,
                        @Param("cardId") int cardId, @Param("personId") int personId);
}
