package com.nickpopyk.wish.demo.mobile.gui.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class CategoryPageBase extends AbstractPage {
    public CategoryPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract String readCategoryTitle();

    public abstract ProductPageBase chooseProduct(int index);
}
