package com.nickpopyk.wish.demo.mobile.gui.pages.common;

import com.nickpopyk.wish.demo.utils.Pages;
import com.nickpopyk.wish.demo.utils.WishAbstractPage;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class NavigationPageBase extends WishAbstractPage {

    public NavigationPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isNavigationItemPresent(Pages page);

    public abstract <T extends AbstractPage> T navigateToPage(Pages page);

}
