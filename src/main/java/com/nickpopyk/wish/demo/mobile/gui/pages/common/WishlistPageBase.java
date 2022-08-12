package com.nickpopyk.wish.demo.mobile.gui.pages.common;

import com.nickpopyk.wish.demo.utils.WishAbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class WishlistPageBase extends WishAbstractPage {
    public WishlistPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isDeleteButtonPresent();

    public abstract void deleteWishlist();
}
