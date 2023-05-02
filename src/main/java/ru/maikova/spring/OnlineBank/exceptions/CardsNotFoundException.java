package ru.maikova.spring.OnlineBank.exceptions;

public class CardsNotFoundException extends Exception {
    public CardsNotFoundException(String message) {
        super(message);
    }
}
