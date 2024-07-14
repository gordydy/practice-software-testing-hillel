package org.example.asserts;

import org.example.pages.Header;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class HeaderAsserts extends Header {
    public HeaderAsserts isSignedIn(String fullUserName) {
        $(PROFILE_MENU).shouldHave(text(fullUserName));
        return this;
    }

}