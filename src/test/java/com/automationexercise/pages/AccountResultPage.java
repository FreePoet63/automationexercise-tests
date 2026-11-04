package com.automationexercise.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/** Page shown after "Account Created!" and "Account Deleted!". */
public class AccountResultPage {

    private final SelenideElement title = $("h2.title b");
    private final SelenideElement continueButton = $("[data-qa='continue-button']");

    public AccountResultPage shouldSeeMessage(String message) {
        title.shouldBe(visible).shouldHave(text(message));
        return this;
    }

    public HomePage clickContinue() {
        continueButton.click();
        return new HomePage();
    }
}