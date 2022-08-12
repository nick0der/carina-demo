package com.nickpopyk.wish.demo.mobile.gui.pages.common;

import com.nickpopyk.wish.demo.utils.WishAbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class CreateWishlistModalBase extends WishAbstractPage {
    public CreateWishlistModalBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isWishlistNameInputPresent();

    public abstract boolean isConfirmCreateWishlistButtonPresent();

    public abstract void typeWishlistNameInput(String wishlistName);

    public abstract void clickConfirmCreateWishlistButton();
}
