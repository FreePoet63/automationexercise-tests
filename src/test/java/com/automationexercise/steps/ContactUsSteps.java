package com.automationexercise.steps;

import com.automationexercise.context.ScenarioContext;
import com.automationexercise.models.User;
import com.automationexercise.pages.ContactUsPage;
import com.automationexercise.pages.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactUsSteps {

    private final ScenarioContext context;

    private final HomePage homePage = new HomePage();
    private ContactUsPage contactUsPage = new ContactUsPage();

    public ContactUsSteps(ScenarioContext context) {
        this.context = context;
    }

    @When("I open the Contact Us page")
    public void iOpenTheContactUsPage() {
        contactUsPage = homePage.openContactUs();
    }

    @When("I fill in the contact form with the subject {string} and the message {string}")
    public void iFillInTheContactForm(String subject, String message) {
        User sender = User.random();
        context.setUser(sender);
        contactUsPage.fillIn(sender, subject, message);
    }

    @When("I attach the file {string}")
    public void iAttachTheFile(String classpathFile) {
        contactUsPage.attachFile(classpathFile);
    }

    @When("I submit the contact form")
    public void iSubmitTheContactForm() {
        contactUsPage.submit();
    }

    @Then("I see the contact success message {string}")
    public void iSeeTheContactSuccessMessage(String expected) {
        contactUsPage.shouldSeeSuccess(expected);
    }

    @When("I return to the home page from the contact page")
    public void iReturnToTheHomePageFromTheContactPage() {
        contactUsPage.returnToHome();
    }

    @Then("I am on the home page")
    public void iAmOnTheHomePage() {
        homePage.shouldBeOpened();
    }
}