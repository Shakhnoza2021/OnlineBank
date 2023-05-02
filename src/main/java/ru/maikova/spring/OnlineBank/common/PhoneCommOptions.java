package ru.maikova.spring.OnlineBank.common;

import java.util.ArrayList;
import java.util.List;

public final class PhoneCommOptions {
    public String selectedOption;

    public List<String> options;

    public PhoneCommOptions() {
        //TODO: transfer to database
        options = new ArrayList<>();
        options.add("Мегафон");
        options.add("МТС");
        options.add("Билайн");
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
