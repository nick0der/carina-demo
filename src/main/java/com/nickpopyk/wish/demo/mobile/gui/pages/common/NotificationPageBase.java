package com.nickpopyk.wish.demo.mobile.gui.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class NotificationPageBase extends AbstractPage {
    public NotificationPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isPermissionAllowButtonPresent();

    public abstract boolean isPermissionDenyButtonPresent();

    public abstract LoginPageBase clickPermissionAllowButton();

    public abstract LoginPageBase clickPermissionDenyButton();
}
