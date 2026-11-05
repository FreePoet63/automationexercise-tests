package com.automationexercise.pages;

import com.automationexercise.models.User;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutPage {

    private final SelenideElement deliveryAddress = $("#address_delivery");
    private final SelenideElement commentField = $("textarea[name='message']");
    private final SelenideElement placeOrderLink = $("a[href='/payment']");

    public CheckoutPage shouldShowDeliveryAddress(User user) {
        deliveryAddress.shouldBe(visible).shouldHave(
                text(user.firstName()),
                text(user.lastName()),
                text(user.address()),
                text(user.city()));
        return this;
    }

    public CheckoutPage shouldContainProduct(String name) {
        $$("#cart_info tbody tr").findBy(text(name)).shouldBe(visible);
        return this;
    }

    public PaymentPage placeOrder(String comment) {
        commentField.setValue(comment);
        placeOrderLink.click();
        return new PaymentPage();
    }
}