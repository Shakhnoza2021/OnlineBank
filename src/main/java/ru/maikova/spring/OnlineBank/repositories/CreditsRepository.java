package ru.maikova.spring.OnlineBank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maikova.spring.OnlineBank.models.Credit;

import java.util.List;

@Repository
public interface CreditsRepository extends JpaRepository<Credit, Integer> {
    List<Credit> findAllByPersonId(final int personId);
}
