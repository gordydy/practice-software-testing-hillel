package org.example.asserts;

import org.example.pages.HomePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HomePageAsserts extends HomePage {

    public void hasCorrectName(String productName){
        $$(CARDS_COLLECTION).first().shouldHave(text(productName));
    }

    public String getThePrice(){
        String priceText = $$(PRODUCT_PRICE_HP).first().shouldBe(visible).text();
        return priceText.replace("$", "").trim();
    }

}