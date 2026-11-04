package com.automationexercise.steps;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.ProductsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductSteps {

    private final HomePage homePage = new HomePage();
    private final ProductsPage productsPage = new ProductsPage();

    @When("I open the Products page")
    public void iOpenTheProductsPage() {
        homePage.openProducts();
    }

    @When("I search for {string}")
    public void iSearchFor(String term) {
        productsPage.search(term);
    }

    @Then("I see the search results title")
    public void iSeeTheSearchResultsTitle() {
        productsPage.shouldShowSearchTitle();
    }

    @Then("the results contain the product {string}")
    public void theResultsContainTheProduct(String name) {
        productsPage.shouldContainProduct(name);
    }

    @Then("no products are found")
    public void noProductsAreFound() {
        productsPage.shouldHaveNoProducts();
    }

    @When("I add the product {string} to the cart")
    public void iAddTheProductToTheCart(String name) {
        productsPage.addToCart(name);
    }

    @When("I continue shopping")
    public void iContinueShopping() {
        productsPage.continueShopping();
    }
}