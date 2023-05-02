package ru.maikova.spring.OnlineBank.models;

import java.util.Date;

public interface History {
    String getName();
    double getSum();
    String getCardName();
    Date getDate();
}
