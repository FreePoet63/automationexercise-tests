package com.automationexercise.pages;

import com.automationexercise.models.User;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    // Signup form
    private final SelenideElement signupHeader = $(".signup-form h2");
    private final SelenideElement signupName = $("[data-qa='signup-name']");
    private final SelenideElement signupEmail = $("[data-qa='signup-email']");
    private final SelenideElement signupButton = $("[data-qa='signup-button']");
    private final SelenideElement signupError = $(".signup-form p");

    // Login form
    private final SelenideElement loginEmail = $("[data-qa='login-email']");
    private final SelenideElement loginPassword = $("[data-qa='login-password']");
    private final SelenideElement loginButton = $("[data-qa='login-button']");
    private final SelenideElement loginError = $(".login-form p");

    public LoginPage shouldBeOpened() {
        signupHeader.shouldBe(visible);
        return this;
    }

    public SignupPage signUp(User user) {
        return signUp(user.name(), user.email());
    }

    public SignupPage signUp(String name, String email) {
        signupName.setValue(name);
        signupEmail.setValue(email);
        signupButton.click();
        return new SignupPage();
    }

    public HomePage login(String email, String password) {
        loginEmail.setValue(email);
        loginPassword.setValue(password);
        loginButton.click();
        return new HomePage();
    }

    public LoginPage shouldSeeLoginError(String message) {
        loginError.shouldBe(visible).shouldHave(text(message));
        return this;
    }

    public LoginPage shouldSeeSignupError(String message) {
        signupError.shouldBe(visible).shouldHave(text(message));
        return this;
    }
}