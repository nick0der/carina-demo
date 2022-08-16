package com.nickpopyk.wish.demo.mobile.gui.pages.common;

import com.nickpopyk.wish.demo.utils.Categories;
import com.nickpopyk.wish.demo.utils.WishAbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class CategoriesPageBase extends WishAbstractPage {
    public CategoriesPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract CategoryPageBase selectCategory(Categories category);
}
