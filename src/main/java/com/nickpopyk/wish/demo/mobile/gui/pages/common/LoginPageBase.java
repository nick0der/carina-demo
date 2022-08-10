package com.nickpopyk.wish.demo.mobile.gui.pages.common;

import com.nickpopyk.wish.demo.utils.WishAbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class LoginPageBase extends WishAbstractPage {
    public LoginPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract HomePageBase login(String email, String password);
}
