package com.automationexercise.pages;

import com.automationexercise.models.PaymentCard;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {

    private final SelenideElement nameOnCard = $("[data-qa='name-on-card']");
    private final SelenideElement cardNumber = $("[data-qa='card-number']");
    private final SelenideElement cvc = $("[data-qa='cvc']");
    private final SelenideElement expiryMonth = $("[data-qa='expiry-month']");
    private final SelenideElement expiryYear = $("[data-qa='expiry-year']");
    private final SelenideElement payButton = $("[data-qa='pay-button']");

    public void pay(PaymentCard card) {
        nameOnCard.setValue(card.nameOnCard());
        cardNumber.setValue(card.number());
        cvc.setValue(card.cvc());
        expiryMonth.setValue(card.month());
        expiryYear.setValue(card.year());
        payButton.click();
    }
}