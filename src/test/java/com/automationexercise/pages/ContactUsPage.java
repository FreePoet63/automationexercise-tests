package com.automationexercise.pages;

import com.automationexercise.models.User;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.confirm;

public class ContactUsPage {

    private final SelenideElement name = $("[data-qa='name']");
    private final SelenideElement email = $("[data-qa='email']");
    private final SelenideElement subject = $("[data-qa='subject']");
    private final SelenideElement message = $("[data-qa='message']");
    private final SelenideElement fileInput = $("input[name='upload_file']");
    private final SelenideElement submitButton = $("[data-qa='submit-button']");
    private final SelenideElement successMessage = $(".status.alert-success");
    private final SelenideElement homeButton = $("#form-section a.btn-success");

    public ContactUsPage fillIn(User user, String subjectText, String messageText) {
        name.setValue(user.name());
        email.setValue(user.email());
        subject.setValue(subjectText);
        message.setValue(messageText);
        return this;
    }

    public ContactUsPage attachFile(String classpathFile) {
        fileInput.uploadFromClasspath(classpathFile);
        return this;
    }

    public ContactUsPage submit() {
        submitButton.click();
        confirm();
        return this;
    }

    public ContactUsPage shouldSeeSuccess(String expected) {
        successMessage.shouldBe(visible).shouldHave(text(expected));
        return this;
    }

    public HomePage returnToHome() {
        homeButton.click();
        return new HomePage();
    }
}