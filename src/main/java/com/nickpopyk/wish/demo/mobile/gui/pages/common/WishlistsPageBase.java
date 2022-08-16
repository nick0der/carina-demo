package com.nickpopyk.wish.demo.mobile.gui.pages.common;

import com.nickpopyk.wish.demo.utils.WishAbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class WishlistsPageBase extends WishAbstractPage {
    public WishlistsPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isNoWishlistsTitlePresent();

    public abstract boolean isListViewPresent();

    public abstract void createNewWishlist(String wishlistName);

    public abstract void renameWishlist(String name, String rename);

    public abstract void deleteWishlist(String name);

    public abstract List<String> getWishlistsTitles();

    public abstract boolean isWishlistTitlePresentIncludingInvisible(String title);

    public abstract boolean isWishlistTitlePresent(String title);
}
