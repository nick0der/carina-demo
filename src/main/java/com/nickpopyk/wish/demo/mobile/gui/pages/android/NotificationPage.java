package com.nickpopyk.wish.demo.mobile.gui.pages.android;

import com.nickpopyk.carina.demo.utils.IConstants;
import com.nickpopyk.wish.demo.mobile.gui.pages.common.LoginPageBase;
import com.nickpopyk.wish.demo.mobile.gui.pages.common.NotificationPageBase;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = NotificationPageBase.class)
public class NotificationPage extends NotificationPageBase {

    @FindBy(id = "com.android.permissioncontroller:id/grant_dialog")
    private ExtendedWebElement dialogWindow;

    @FindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    private ExtendedWebElement permissionAllowButton;

    @FindBy(id = "com.android.permissioncontroller:id/permission_deny_button")
    private ExtendedWebElement permissionDenyButton;

    public NotificationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return dialogWindow.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isPermissionAllowButtonPresent(){
        return permissionAllowButton.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isPermissionDenyButtonPresent(){
        return permissionDenyButton.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT);
    }

    @Override
    public LoginPageBase clickPermissionAllowButton(){
        permissionAllowButton.click();
        return initPage(getDriver(), LoginPageBase.class);
    }

    @Override
    public LoginPageBase clickPermissionDenyButton(){
        permissionDenyButton.click();
        return initPage(getDriver(), LoginPageBase.class);
    }
}
