package com.nickpopyk.carina.demo.mobile.gui.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class HomePageBase extends AbstractPage {

    public HomePageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isNextButtonPresent();

    public abstract boolean isCarinaLogoPresent();

    public abstract RegisterPageBase clickNextButton();
}
