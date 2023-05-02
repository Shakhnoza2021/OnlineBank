package ru.maikova.spring.OnlineBank.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.maikova.spring.OnlineBank.models.Person;
import ru.maikova.spring.OnlineBank.security.PersonDetails;

@Component
public class CommonService {
    //Авторизованный пользователь
    public Person getAuthPerson() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getPerson();
    }
}
