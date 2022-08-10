package com.nickpopyk.wish.demo.mobile.gui.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class ProductPageBase extends AbstractPage {
    public ProductPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract String addToCart();

    public abstract CartPageBase navigateToCart();

    public abstract String readProductTitle();
}
