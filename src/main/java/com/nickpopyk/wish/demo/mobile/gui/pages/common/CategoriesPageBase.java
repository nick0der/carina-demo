package com.nickpopyk.wish.demo.mobile.gui.pages.common;

import com.nickpopyk.wish.demo.utils.Categories;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class CategoriesPageBase extends AbstractPage {
    public CategoriesPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract CategoryPageBase selectCategory(Categories category);
}
