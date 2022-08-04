package com.nickpopyk.carina.demo.mobile.gui.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class MenuPageBase extends AbstractPage {
    public MenuPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isMenuItemByTextPresent(String text);

    public abstract WebViewPageBase openWebViewPage();

    public abstract ChartsPageBase openChartsPage();

    public abstract MapPageBase openMapPage();

    public abstract UIElementsPageBase openUIElementsPage();
}
