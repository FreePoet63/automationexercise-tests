package com.automationexercise.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage {

    private final SelenideElement consentButton = $(".fc-cta-consent");
    private final SelenideElement signupLoginLink = $("a[href='/login']");
    private final SelenideElement logoutLink = $("a[href='/logout']");
    private final SelenideElement loggedInAs = $x("//a[contains(., 'Logged in as')]");
    private final SelenideElement deleteAccountLink = $("a[href='/delete_account']");
    private final SelenideElement productsLink = $("a[href='/products']");
    private final SelenideElement contactUsLink = $("a[href='/contact_us']");
    private final SelenideElement slider = $("#slider");

    public HomePage open() {
        Selenide.open("/");
        acceptConsentIfShown();
        return this;
    }

    public LoginPage openSignupLogin() {
        signupLoginLink.click();
        return new LoginPage();
    }

    public HomePage shouldBeLoggedInAs(String name) {
        loggedInAs.shouldBe(visible).shouldHave(text(name));
        return this;
    }

    public LoginPage logout() {
        logoutLink.click();
        return new LoginPage();
    }

    public ProductsPage openProducts() {
        productsLink.click();
        return new ProductsPage();
    }

    public ContactUsPage openContactUs() {
        contactUsLink.click();
        return new ContactUsPage();
    }

    public HomePage shouldBeOpened() {
        slider.shouldBe(visible);
        return this;
    }

    public void deleteAccount() {
        deleteAccountLink.click();
    }

    private void acceptConsentIfShown() {
        try {
            consentButton.shouldBe(visible, Duration.ofSeconds(3)).click();
        } catch (AssertionError ignored) {
            // consent dialog did not appear
        }
    }
}