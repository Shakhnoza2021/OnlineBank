package ru.maikova.spring.OnlineBank.exceptions;

public class AccountsNotFoundException extends Exception {
    public AccountsNotFoundException(String message) {
        super(message);
    }
}
