package org.example.asserts;

import org.example.pages.ProductPage;

import static com.codeborne.selenide.Selenide.webdriver;

public class ProductPageAsserts extends ProductPage {

    public boolean isRedirected(String productId) {
        String currentUrl = webdriver().driver().url();
        return (currentUrl).contains(productId);
    }

}