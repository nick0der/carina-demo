package com.nickpopyk.carina.demo.mobile.gui.pages.common;

import com.nickpopyk.carina.demo.utils.Pages;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class SidebarMenuPageBase extends AbstractPage {
    public SidebarMenuPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isMenuItemPresent(Pages pages);

    public abstract ContentPageBase openContentPage(Pages page);
}
