package com.automationexercise.steps;

import com.automationexercise.api.UserApi;
import com.automationexercise.context.ScenarioContext;
import com.automationexercise.models.User;
import com.automationexercise.pages.AccountResultPage;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.pages.SignupPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AuthSteps {

    private final ScenarioContext context;

    private final HomePage homePage = new HomePage();
    private final LoginPage loginPage = new LoginPage();
    private final SignupPage signupPage = new SignupPage();
    private final AccountResultPage resultPage = new AccountResultPage();

    public AuthSteps(ScenarioContext context) {
        this.context = context;
    }

    @Given("a registered user exists")
    public void aRegisteredUserExists() {
        User user = User.random();
        UserApi.create(user);
        context.setUser(user);
        context.setApiUserCreated(true); // removed in the @After hook
    }

    @Given("I open the home page")
    public void iOpenTheHomePage() {
        homePage.open();
    }

    @When("I open the Signup and Login page")
    public void iOpenTheSignupAndLoginPage() {
        homePage.openSignupLogin().shouldBeOpened();
    }

    @When("I sign up with a new unique user")
    public void iSignUpWithANewUniqueUser() {
        context.setUser(User.random());
        loginPage.signUp(context.getUser());
        signupPage.shouldBeOpened();
    }

    @When("I try to sign up with the registered user email")
    public void iTryToSignUpWithTheRegisteredUserEmail() {
        User user = context.getUser();
        loginPage.signUp("another_name", user.email());
    }

    @When("I fill in the account information and create the account")
    public void iFillInTheAccountInformationAndCreateTheAccount() {
        signupPage.fillAccountInfoAndSubmit(context.getUser());
    }

    @Then("I see the message {string}")
    public void iSeeTheMessage(String message) {
        resultPage.shouldSeeMessage(message);
    }

    @Then("I see the signup error {string}")
    public void iSeeTheSignupError(String message) {
        loginPage.shouldSeeSignupError(message);
    }

    @When("I continue to the home page")
    public void iContinueToTheHomePage() {
        resultPage.clickContinue();
    }

    @Then("I am logged in with my user name")
    public void iAmLoggedInWithMyUserName() {
        homePage.shouldBeLoggedInAs(context.getUser().name());
    }

    @When("I delete my account")
    public void iDeleteMyAccount() {
        homePage.deleteAccount();
    }
}