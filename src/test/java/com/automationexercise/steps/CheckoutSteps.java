package com.automationexercise.steps;

import com.automationexercise.context.ScenarioContext;
import com.automationexercise.models.PaymentCard;
import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.CheckoutPage;
import com.automationexercise.pages.PaymentPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutSteps {

    private final ScenarioContext context;

    private final CartPage cartPage = new CartPage();
    private CheckoutPage checkoutPage = new CheckoutPage();
    private PaymentPage paymentPage = new PaymentPage();

    public CheckoutSteps(ScenarioContext context) {
        this.context = context;
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        checkoutPage = cartPage.proceedToCheckout();
    }

    @When("I proceed to checkout as a guest")
    public void iProceedToCheckoutAsAGuest() {
        cartPage.proceedToCheckoutAsGuest();
    }

    @Then("I am asked to register or log in")
    public void iAmAskedToRegisterOrLogIn() {
        cartPage.shouldAskToRegisterOrLogin();
    }

    @Then("I see my delivery address on the checkout page")
    public void iSeeMyDeliveryAddressOnTheCheckoutPage() {
        checkoutPage.shouldShowDeliveryAddress(context.getUser());
    }

    @Then("the order review contains the product {string}")
    public void theOrderReviewContainsTheProduct(String name) {
        checkoutPage.shouldContainProduct(name);
    }

    @When("I place the order with the comment {string}")
    public void iPlaceTheOrderWithTheComment(String comment) {
        paymentPage = checkoutPage.placeOrder(comment);
    }

    @When("I pay with a test card")
    public void iPayWithATestCard() {
        String holder = context.getUser().firstName() + " " + context.getUser().lastName();
        paymentPage.pay(PaymentCard.test(holder));
    }
}