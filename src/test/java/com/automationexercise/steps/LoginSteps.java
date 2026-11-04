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

    @When("I log in with the registered email and a {string} password")
    public void iLogInWithTheRegisteredEmailAndAPassword(String variant) {
        User user = context.getUser();
        String real = user.password();
        String password = switch (variant) {
            case "incorrect" -> real + "x";
            case "uppercase" -> real.toUpperCase();
            case "lowercase" -> real.toLowerCase();
            default -> throw new IllegalArgumentException("Unknown variant: " + variant);
        };
        loginPage.login(user.email(), password);
    }

    @When("I log in with an unregistered email")
    public void iLogInWithAnUnregisteredEmail() {
        User stranger = User.random();
        loginPage.login(stranger.email(), stranger.password());
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