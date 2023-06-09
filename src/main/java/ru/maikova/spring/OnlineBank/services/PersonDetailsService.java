package ru.maikova.spring.OnlineBank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.maikova.spring.OnlineBank.models.Person;
import ru.maikova.spring.OnlineBank.repositories.PeopleRepository;
import ru.maikova.spring.OnlineBank.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByPhoneNumber(phoneNumber);

        if (person.isEmpty())
            throw new UsernameNotFoundException("Пользователь не найден");

        return new PersonDetails(person.get());
    }
}
