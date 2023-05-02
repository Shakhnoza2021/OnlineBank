package ru.maikova.spring.OnlineBank.exceptions;

public class CreditsNotFoundException extends Exception {
    public CreditsNotFoundException(String message) {
        super(message);
    }
}
