package com.automationexercise.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsPage {

    private final SelenideElement searchInput = $("#search_product");
    private final SelenideElement searchButton = $("#submit_search");
    private final SelenideElement title = $(".features_items h2.title");
    private final SelenideElement addedModal = $("#cartModal");
    private final SelenideElement continueShoppingButton = $("#cartModal .close-modal");
    private final SelenideElement viewCartLink = $("#cartModal a[href='/view_cart']");

    public ProductsPage search(String term) {
        searchInput.setValue(term);
        searchButton.click();
        return this;
    }

    public ProductsPage shouldShowSearchTitle() {
        title.shouldBe(visible).shouldHave(text("Searched Products"));
        return this;
    }

    public ProductsPage shouldContainProduct(String name) {
        $$(".productinfo p").findBy(exactText(name)).shouldBe(visible);
        return this;
    }

    public ProductsPage shouldHaveNoProducts() {
        $$(".features_items .product-image-wrapper").shouldHave(size(0));
        return this;
    }

    public ProductsPage addToCart(String name) {
        SelenideElement card = $$(".productinfo p").findBy(exactText(name))
                .closest(".product-image-wrapper");
        card.scrollIntoView("{block: 'center'}").$(".productinfo .add-to-cart").click();
        addedModal.shouldBe(visible);
        return this;
    }

    public ProductsPage continueShopping() {
        continueShoppingButton.click();
        addedModal.shouldNotBe(visible);
        return this;
    }

    public CartPage openCartFromDialog() {
        viewCartLink.click();
        return new CartPage();
    }
}