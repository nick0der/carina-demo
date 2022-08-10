package com.nickpopyk.wish.demo.mobile.gui.pages.common;

import com.nickpopyk.wish.demo.utils.Pages;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class NavigationPageBase extends AbstractPage {

    public NavigationPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract <T extends AbstractPage> T navigateToPage(Pages page);

}
