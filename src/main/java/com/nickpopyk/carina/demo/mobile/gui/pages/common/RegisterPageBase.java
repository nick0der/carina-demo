package com.nickpopyk.carina.demo.mobile.gui.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class RegisterPageBase extends AbstractPage {
    public RegisterPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isBackButtonPresent();

    public abstract boolean isNameInputPresent();

    public abstract boolean isPasswordInputPresent();

    public abstract boolean isAgreeCheckboxPresent();

    public abstract boolean isSignUpButtonPresent();

    public abstract boolean isSignUpButtonClickable();

    public abstract void clickRadioButtonByText(String text);

    public abstract boolean isRadioButtonByTextActive(String text);

    public abstract void checkAgreeCheckbox(boolean status);

    public abstract boolean isAgreeCheckboxChecked();

    public abstract boolean isItemByTextPresent(String text);

    public abstract void fillFormExceptNameField();

    public abstract void fillFormExceptPasswordField();

    public abstract void fillFormExceptRadioButtons();

    public abstract void fillFormExceptCheckbox();

    public abstract void fillForm();

    public abstract WebViewPageBase clickSignUpButton();
}
