package com.nickpopyk.carina.demo.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class RegisterPageBase extends AbstractPage {
    public RegisterPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isBackBtnPresent();

    public abstract boolean isNameInputPresent();

    public abstract boolean isPasswordInputPresent();

    public abstract boolean isCheckboxPresent();

    public abstract boolean isSignUpBtnPresent();

    public abstract boolean clickItemByText(String text);

    public abstract boolean clickCheckbox();

    public abstract boolean isItemByTextPresent(String text);
}
