package com.nickpopyk.carina.demo.pages.android;

import com.nickpopyk.carina.demo.pages.common.RegisterPageBase;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.nickpopyk.carina.demo.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = RegisterPageBase.class)
public class RegisterPage extends RegisterPageBase {

    @FindBy(id = "backButton")
    ExtendedWebElement backBtn;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.TextView")
    ExtendedWebElement carinaLogo;

    @FindBy(xpath = "//*[@text = '%s']")
    private ExtendedWebElement itemByText;

    @FindBy(id = "name")
    ExtendedWebElement nameInput;

    @FindBy(id = "password")
    ExtendedWebElement passwordInput;

    @FindBy(id = "checkbox")
    ExtendedWebElement checkbox;

    @FindBy(id = "login_button")
    ExtendedWebElement signUpBtn;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return itemByText.format("CARINA").isElementPresent(Constants.TIMEOUT);
    }

    @Override
    public boolean isBackBtnPresent() {
        return backBtn.isElementPresent(Constants.TIMEOUT);
    }

    @Override
    public boolean isNameInputPresent() {
        return nameInput.isElementPresent(Constants.TIMEOUT);
    }

    @Override
    public boolean isPasswordInputPresent() {
        return passwordInput.isElementPresent(Constants.TIMEOUT);
    }

    @Override
    public boolean isCheckboxPresent() {
        return checkbox.isElementPresent(Constants.TIMEOUT);
    }

    @Override
    public boolean isSignUpBtnPresent() {
        return signUpBtn.isElementPresent(Constants.TIMEOUT);
    }

    @Override
    public boolean clickItemByText(String text) {
        itemByText.format(text).click();
        return itemByText.format(text).isChecked();
    }

    @Override
    public boolean clickCheckbox() {
        checkbox.click();
        return checkbox.isChecked();
    }

    @Override
    public boolean isItemByTextPresent(String text) {
        return itemByText.format(text).isElementPresent(Constants.TIMEOUT);
    }
}
