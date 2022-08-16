package com.nickpopyk.wish.demo.mobile.gui.pages.common;

import com.nickpopyk.wish.demo.utils.WishAbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class NotificationPageBase extends WishAbstractPage {
    public NotificationPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isPermissionAllowButtonPresent();

    public abstract boolean isPermissionDenyButtonPresent();

    public abstract LoginPageBase clickPermissionAllowButton();

    public abstract LoginPageBase clickPermissionDenyButton();
}
