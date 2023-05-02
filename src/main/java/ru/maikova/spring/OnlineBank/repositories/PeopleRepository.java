package ru.maikova.spring.OnlineBank.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.maikova.spring.OnlineBank.models.Person;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByPhoneNumber(String phoneNumber);

    @Query("SELECT p FROM Person p JOIN FETCH p.cards WHERE p.id=:personId")
    @EntityGraph(attributePaths = {"cards"})
    Person findAllCardsByPersonId(int personId);

    @Query("SELECT p FROM Person p JOIN FETCH p.accounts WHERE p.id=:personId")
    @EntityGraph(attributePaths = {"accounts"})
    Person findAllAccountsByPersonId(int personId);

    @Query("SELECT p FROM Person p JOIN FETCH p.credits WHERE p.id=:personId")
    @EntityGraph(attributePaths = {"credits"})
    Person findAllCreditsByPersonId(int personId);

    @Query("SELECT p FROM Person p JOIN FETCH p.communalServices WHERE p.id=:personId")
    @EntityGraph(attributePaths = {"communalServices"})
    Person findAllCommunalServicesByPersonId(int personId);

    @Query("SELECT p FROM Person p JOIN FETCH p.fines WHERE p.id=:personId")
    @EntityGraph(attributePaths = {"fines"})
    Person findAllFinesByPersonId(int personId);

    @Query("SELECT p FROM Person p JOIN FETCH p.phoneCommunications WHERE p.id=:personId")
    @EntityGraph(attributePaths = {"phoneCommunications"})
    Person findAllPhoneCommsByPersonId(int personId);

    @Query("SELECT p FROM Person p JOIN FETCH p.transfers WHERE p.id=:personId")
    @EntityGraph(attributePaths = {"transfers"})
    Person findAllTransfersByPersonId(int personId);
}
