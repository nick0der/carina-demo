package com.nickpopyk.carina.demo.mobile.gui.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class ContentPageBase extends AbstractPage {

    public ContentPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract SidebarMenuPageBase openSidebarMenuPage();
}
