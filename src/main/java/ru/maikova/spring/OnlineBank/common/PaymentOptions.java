package ru.maikova.spring.OnlineBank.common;

import java.util.ArrayList;
import java.util.List;

public final class PaymentOptions {
    public String selectedPaymentOption;
    public String selectedPhoneCommOption;
    public String selectedCommServOption;
    public String selectedCommServCompany;
    public String selectedFineOption;

    public List<String> paymentOptions;
    public List<String> phoneCommOptions;
    public List<String> commServOptions;
    public List<String> commServCompanies;
    public List<String> fineOptions;

    public PaymentOptions() {
        //TODO: transfer to database
        this.paymentOptions = new ArrayList<>();
        this.paymentOptions.add("Телефонная связь");
        this.paymentOptions.add("Штрафы");
        this.paymentOptions.add("ЖКХ");

        this.phoneCommOptions = new ArrayList<>();
        this.phoneCommOptions.add("Мегафон");
        this.phoneCommOptions.add("МТС");
        this.phoneCommOptions.add("Билайн");

        this.commServOptions = new ArrayList<>();
        this.commServOptions.add("Электроэнергия");
        this.commServOptions.add("Холодная вода");
        this.commServOptions.add("Горячая вода");
        this.commServOptions.add("Отопление");
        this.commServOptions.add("Вывоз мусора");

        this.commServCompanies = new ArrayList<>();
        this.commServCompanies.add("НСК РТС");
        this.commServCompanies.add("Водоканал");
        this.commServCompanies.add("УК Гарант");

        this.fineOptions = new ArrayList<>();
        this.fineOptions.add("Штрафы (ГИБДД, МВД)");
        this.fineOptions.add("Администрация");
        this.fineOptions.add("ФССП(судебные)");
    }

    public String getSelectedPaymentOption() {
        return selectedPaymentOption;
    }

    public void setSelectedPaymentOption(String selectedPaymentOption) {
        this.selectedPaymentOption = selectedPaymentOption;
    }

    public String getSelectedPhoneCommOption() {
        return selectedPhoneCommOption;
    }

    public void setSelectedPhoneCommOption(String selectedPhoneCommOption) {
        this.selectedPhoneCommOption = selectedPhoneCommOption;
    }

    public String getSelectedCommServOption() {
        return selectedCommServOption;
    }

    public void setSelectedCommServOption(String selectedCommServOption) {
        this.selectedCommServOption = selectedCommServOption;
    }

    public String getSelectedCommServCompany() {
        return selectedCommServCompany;
    }

    public String getSelectedFineOption() {
        return selectedFineOption;
    }

    public void setSelectedCommServCompany(String selectedCommServCompany) {
        this.selectedCommServCompany = selectedCommServCompany;
    }

    public void setSelectedFineOption(String selectedFineOption) {
        this.selectedFineOption = selectedFineOption;
    }

    public List<String> getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(List<String> paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public List<String> getPhoneCommOptions() {
        return phoneCommOptions;
    }

    public void setPhoneCommOptions(List<String> phoneCommOptions) {
        this.phoneCommOptions = phoneCommOptions;
    }

    public List<String> getCommServOptions() {
        return commServOptions;
    }

    public void setCommServOptions(List<String> commServOptions) {
        this.commServOptions = commServOptions;
    }

    public List<String> getCommServCompanies() {
        return commServCompanies;
    }

    public void setCommServCompanies(List<String> commServCompanies) {
        this.commServCompanies = commServCompanies;
    }

    public List<String> getFineOptions() {
        return fineOptions;
    }

    public void setFineOptions(List<String> fineOptions) {
        this.fineOptions = fineOptions;
    }
}
