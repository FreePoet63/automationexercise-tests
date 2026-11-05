package com.automationexercise.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartPage {

    private final ElementsCollection rows = $$("#cart_info_table tbody tr");
    private final SelenideElement emptyMessage = $("#empty_cart");
    private final SelenideElement checkoutButton = $(".check_out");
    private final SelenideElement checkoutModalLoginLink = $("#checkoutModal a[href='/login']");

    public CartPage shouldHaveProductsCount(int count) {
        rows.shouldHave(size(count));
        return this;
    }

    public CartPage shouldContainProduct(String name) {
        row(name).shouldBe(visible);
        return this;
    }

    public CartPage shouldHaveQuantity(String name, int quantity) {
        row(name).$(".cart_quantity button").shouldHave(exactText(String.valueOf(quantity)));
        return this;
    }

    public CartPage shouldHaveTotalEqualToPriceTimesQuantity(String name) {
        SelenideElement row = row(name);
        int price = digits(row.$(".cart_price p").shouldBe(visible).getText());
        int quantity = digits(row.$(".cart_quantity button").getText());
        int total = digits(row.$(".cart_total_price").getText());
        assertEquals(price * quantity, total, "Total for " + name);
        return this;
    }

    public CartPage remove(String name) {
        row(name).$(".cart_quantity_delete").click();
        return this;
    }

    public CartPage shouldBeEmpty() {
        emptyMessage.shouldBe(visible).shouldHave(text("Cart is empty!"));
        return this;
    }

    public CheckoutPage proceedToCheckout() {
        checkoutButton.click();
        return new CheckoutPage();
    }

    public CartPage proceedToCheckoutAsGuest() {
        checkoutButton.click();
        return this;
    }

    public CartPage shouldAskToRegisterOrLogin() {
        checkoutModalLoginLink.shouldBe(visible).shouldHave(text("Register / Login"));
        return this;
    }

    private SelenideElement row(String name) {
        return rows.findBy(text(name));
    }

    private static int digits(String value) {
        return Integer.parseInt(value.replaceAll("\\D", ""));
    }
}