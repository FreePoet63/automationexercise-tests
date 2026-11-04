package com.automationexercise.pages;

import com.automationexercise.models.User;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SignupPage {

    private final SelenideElement header = $(".login-form h2");
    private final SelenideElement titleMr = $("#id_gender1");
    private final SelenideElement password = $("[data-qa='password']");
    private final SelenideElement day = $("[data-qa='days']");
    private final SelenideElement month = $("[data-qa='months']");
    private final SelenideElement year = $("[data-qa='years']");
    private final SelenideElement newsletter = $("#newsletter");
    private final SelenideElement offers = $("#optin");
    private final SelenideElement firstName = $("[data-qa='first_name']");
    private final SelenideElement lastName = $("[data-qa='last_name']");
    private final SelenideElement company = $("[data-qa='company']");
    private final SelenideElement address = $("[data-qa='address']");
    private final SelenideElement country = $("[data-qa='country']");
    private final SelenideElement state = $("[data-qa='state']");
    private final SelenideElement city = $("[data-qa='city']");
    private final SelenideElement zipcode = $("[data-qa='zipcode']");
    private final SelenideElement mobile = $("[data-qa='mobile_number']");
    private final SelenideElement createAccountButton = $("[data-qa='create-account']");

    public SignupPage shouldBeOpened() {
        header.shouldBe(visible);
        return this;
    }

    public AccountResultPage fillAccountInfoAndSubmit(User user) {
        titleMr.click();
        password.setValue(user.password());
        day.selectOption(user.day());
        month.selectOption(user.month());
        year.selectOption(user.year());
        newsletter.click();
        offers.click();
        firstName.setValue(user.firstName());
        lastName.setValue(user.lastName());
        company.setValue(user.company());
        address.setValue(user.address());
        country.selectOption(user.country());
        state.setValue(user.state());
        city.setValue(user.city());
        zipcode.setValue(user.zipcode());
        mobile.setValue(user.mobile());
        createAccountButton.click();
        return new AccountResultPage();
    }
}