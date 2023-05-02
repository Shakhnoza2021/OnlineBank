package ru.maikova.spring.OnlineBank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.maikova.spring.OnlineBank.models.PhoneCommunication;

import java.util.List;

@Repository
public interface PhoneCommunicationsRepository extends JpaRepository<PhoneCommunication, Integer> {
    List<PhoneCommunication> findAllByPersonId(final int personId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Phone_communication(name, provider, phone_number, sum, date, card_id, person_id) \n" +
            "VALUES(:name, :provider, :phoneNum, :sum, :date, :cardId, :personId)", nativeQuery = true)
    void addPhoneCommunication(@Param("name") String name, @Param("provider") String provider,
                               @Param("phoneNum") String phoneNum, @Param("sum") double sum,
                               @Param("date") String date, @Param("cardId") int cardId, @Param("personId") int personId);
}

