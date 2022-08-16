package com.nickpopyk.wish.demo.mobile.gui.pages.common;

import com.nickpopyk.wish.demo.utils.WishAbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class CategoryPageBase extends WishAbstractPage {
    public CategoryPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isProductListPresent();

    public abstract String getCategoryTitle();

    public abstract ProductPageBase chooseProduct(int index);
}
