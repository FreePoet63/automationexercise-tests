package com.automationexercise.steps;

import com.automationexercise.context.ScenarioContext;
import com.automationexercise.models.User;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private final ScenarioContext context;

    private final HomePage homePage = new HomePage();
    private final LoginPage loginPage = new LoginPage();

    public LoginSteps(ScenarioContext context) {
        this.context = context;
    }

    @When("I log in with the registered user credentials")
    public void iLogInWithTheRegisteredUserCredentials() {
        User user = context.getUser();
        loginPage.login(user.email(), user.password());
    }

    @When("I log in with the registered email and password {string}")
    public void iLogInWithTheRegisteredEmailAndPassword(String password) {
        loginPage.login(context.getUser().email(), password);
    }

    @When("I log in with email {string} and password {string}")
    public void iLogInWithEmailAndPassword(String email, String password) {
        loginPage.login(email, password);
    }

    @When("I log out")
    public void iLogOut() {
        homePage.logout();
    }

    @Then("I am on the login page")
    public void iAmOnTheLoginPage() {
        loginPage.shouldBeOpened();
    }

    @Then("I see the login error {string}")
    public void iSeeTheLoginError(String message) {
        loginPage.shouldSeeLoginError(message);
    }
}