package com.nickpopyk.carina.demo.mobile.gui.pages.android;

import com.nickpopyk.carina.demo.mobile.gui.pages.common.RegisterPageBase;
import com.nickpopyk.carina.demo.mobile.gui.pages.common.WebViewPageBase;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.nickpopyk.carina.demo.utils.IConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = RegisterPageBase.class)
public class RegisterPage extends RegisterPageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(id = "backButton")
    ExtendedWebElement backButton;

    @FindBy(xpath = "//*[@text = '%s']")
    private ExtendedWebElement itemByText;

    @FindBy(id = "name")
    ExtendedWebElement nameInput;

    @FindBy(id = "password")
    ExtendedWebElement passwordInput;

    @FindBy(id = "checkbox")
    ExtendedWebElement agreeCheckbox;

    @FindBy(id = "login_button")
    ExtendedWebElement signUpButton;

    @FindBy(id = "loginForm")
    ExtendedWebElement loginForm;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return itemByText.format("CARINA").isElementPresent(IConstants.THREE_SECONDS_TIMEOUT) &&
                loginForm.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isBackButtonPresent() {
        return backButton.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isNameInputPresent() {
        return nameInput.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isPasswordInputPresent() {
        return passwordInput.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isAgreeCheckboxPresent() {
        return agreeCheckbox.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isSignUpButtonPresent() {
        return signUpButton.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isSignUpButtonClickable() {
        return signUpButton.isClickable(IConstants.THREE_SECONDS_TIMEOUT);
    }

    @Override
    public void clickRadioButtonByText(String text) {
        itemByText.format(text).check();
    }

    @Override
    public boolean isRadioButtonByTextActive(String text) {
        return itemByText.format(text).isChecked();
    }

    @Override
    public void checkAgreeCheckbox(boolean status) {
        if (status)
            agreeCheckbox.check();
        else
            agreeCheckbox.uncheck();
        LOGGER.info("The Agree Checkbox check status is: {} ", agreeCheckbox.isChecked());
    }

    @Override
    public boolean isAgreeCheckboxChecked(){
        return agreeCheckbox.isChecked();
    }

    @Override
    public boolean isItemByTextPresent(String text) {
        return itemByText.format(text).isElementPresent(IConstants.THREE_SECONDS_TIMEOUT);
    }

    @Override
    public void fillFormExceptNameField() {
        passwordInput.type("123456789");
        itemByText.format("Male").check();
        agreeCheckbox.check();
    }

    @Override
    public void fillFormExceptPasswordField() {
        nameInput.type("John");
        itemByText.format("Male").check();
        agreeCheckbox.check();
    }

    @Override
    public void fillFormExceptRadioButtons() {
        nameInput.type("John");
        passwordInput.type("123456789");
        agreeCheckbox.check();
    }

    @Override
    public void fillFormExceptCheckbox() {
        nameInput.type("John");
        passwordInput.type("123456789");
        itemByText.format("Male").check();
    }

    @Override
    public void fillForm() {
        nameInput.type("John");
        passwordInput.type("123456789");
        itemByText.format("Male").check();
        agreeCheckbox.check();
    }

    @Override
    public WebViewPageBase clickSignUpButton() {
        signUpButton.click();
        return initPage(getDriver(), WebViewPageBase.class);
    }
}
