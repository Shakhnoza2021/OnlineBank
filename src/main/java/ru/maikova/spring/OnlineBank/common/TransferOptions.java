package ru.maikova.spring.OnlineBank.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public final class TransferOptions {
    public int selectedOption;

    public static HashMap<Integer, String> options;

    static {
        options = new HashMap<>();
        options.put(1, "Перевод между своими счетами");
        options.put(2, "Перевод клиенту банка");
    }
}
