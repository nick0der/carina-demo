package com.nickpopyk.wish.demo.mobile.gui.pages.common;

import com.nickpopyk.wish.demo.utils.WishAbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class ProductPageBase extends WishAbstractPage {
    public ProductPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract String addToCart();

    public abstract CartPageBase navigateToCart();

    public abstract String readProductTitle();
}
