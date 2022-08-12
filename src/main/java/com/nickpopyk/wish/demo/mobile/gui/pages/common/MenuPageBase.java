package com.nickpopyk.wish.demo.mobile.gui.pages.common;

import com.nickpopyk.wish.demo.utils.WishAbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class MenuPageBase extends WishAbstractPage {
    public MenuPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isWishlistButtonPresent();

    public abstract WishlistsPageBase openWishlistsPage();
}
