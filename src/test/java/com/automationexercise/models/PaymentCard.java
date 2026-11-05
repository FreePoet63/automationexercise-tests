package com.automationexercise.models;

public record PaymentCard(String nameOnCard, String number, String cvc, String month, String year) {

    public static PaymentCard test(String nameOnCard) {
        return new PaymentCard(nameOnCard, "4111111111111111", "123", "12", "2035");
    }
}