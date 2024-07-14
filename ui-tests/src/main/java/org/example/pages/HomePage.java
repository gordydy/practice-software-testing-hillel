package org.example.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.cssSelector;

public class HomePage {

    private static final By BANNER = className("img-fluid");
    private static final By QUERY_FIELD = byId("search-query");
    private static final By SEARCH_BTN = cssSelector(".input-group .btn-secondary");
    protected static final By CARDS_COLLECTION = className("card-title");
    private static final By CARD_BODY = className("card-img-top");
    protected static final By PRODUCT_PRICE_HP = cssSelector(".float-end.text-muted [data-test='product-price']");

    public HomePage isLoaded() {
        $(BANNER).shouldBe(visible);
        return this;
    }

    public void searchProductByName(String productName) {
        $(QUERY_FIELD).val(productName);
        $(SEARCH_BTN).shouldBe(clickable).click();
    }

    public void goOnProductPage() {
        $(CARD_BODY).shouldBe(clickable).click();
    }

}