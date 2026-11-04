package com.automationexercise.steps;

import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.ProductsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartSteps {

    private final ProductsPage productsPage = new ProductsPage();
    private CartPage cartPage;

    @When("I open the cart from the confirmation dialog")
    public void iOpenTheCartFromTheConfirmationDialog() {
        cartPage = productsPage.openCartFromDialog();
    }

    @Then("the cart contains {int} product(s)")
    public void theCartContainsProducts(int count) {
        cartPage.shouldHaveProductsCount(count);
    }

    @Then("the cart contains the product {string}")
    public void theCartContainsTheProduct(String name) {
        cartPage.shouldContainProduct(name);
    }

    @Then("the quantity of {string} in the cart is {int}")
    public void theQuantityInTheCartIs(String name, int quantity) {
        cartPage.shouldHaveQuantity(name, quantity);
    }

    @Then("the total of {string} equals price multiplied by quantity")
    public void theTotalEqualsPriceMultipliedByQuantity(String name) {
        cartPage.shouldHaveTotalEqualToPriceTimesQuantity(name);
    }

    @When("I remove the product {string} from the cart")
    public void iRemoveTheProductFromTheCart(String name) {
        cartPage.remove(name);
    }

    @Then("the cart is empty")
    public void theCartIsEmpty() {
        cartPage.shouldBeEmpty();
    }
}