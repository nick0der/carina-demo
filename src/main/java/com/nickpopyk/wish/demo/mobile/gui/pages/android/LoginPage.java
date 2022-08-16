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
    private ExtendedWebElement emailInput;

    @FindBy(id = "com.contextlogic.wish:id/passwordText")
    private ExtendedWebElement passwordInput;

    @FindBy(id = "com.contextlogic.wish:id/signInButton")
    private ExtendedWebElement singInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return singInButton.isElementPresent(TEN_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isEmailInputPresent(){
        return emailInput.isElementPresent(THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isPasswordInputPresent(){
        return passwordInput.isElementPresent(THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isSignInButtonPresent(){
        return singInButton.isElementPresent(THREE_SECONDS_TIMEOUT);
    }

    @Override
    public HomePageBase login(String email, String password) {
        emailInput.type(email);
        passwordInput.type(password);
        singInButton.click();
        return initPage(getDriver(), HomePageBase.class);
    }
}
