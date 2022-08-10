package com.nickpopyk.wish.demo.mobile.gui.pages.android;

import com.nickpopyk.wish.demo.mobile.gui.pages.common.MenuPageBase;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = MenuPageBase.class)
public class MenuPage extends MenuPageBase {
    public MenuPage(WebDriver driver) {
        super(driver);
    }
}
