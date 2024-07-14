package org.example.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductPage {

    private static final By PRODUCT_PRICE = By.cssSelector("[data-test='unit-price']");

    public void comparePrice(String priceFromHomePage) {
        String price = $(PRODUCT_PRICE).shouldBe(visible).text();
        assertThat(price).isEqualTo(priceFromHomePage);
    }
}