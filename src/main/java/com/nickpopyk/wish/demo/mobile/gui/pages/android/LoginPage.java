package com.nickpopyk.wish.demo.mobile.gui.pages.android;

import com.nickpopyk.wish.demo.mobile.gui.pages.common.HomePageBase;
import com.nickpopyk.wish.demo.mobile.gui.pages.common.LoginPageBase;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase {

    @FindBy(id = "com.contextlogic.wish:id/input")
    ExtendedWebElement emailInput;

    @FindBy(id = "com.contextlogic.wish:id/passwordText")
    ExtendedWebElement passwordInput;

    @FindBy(id = "com.contextlogic.wish:id/signInButton")
    ExtendedWebElement singInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HomePageBase login(String email, String password) {
        emailInput.type(email);
        passwordInput.type(password);
        singInButton.click();
        return initPage(getDriver(), HomePageBase.class);
    }
}
