package com.nickpopyk.carina.demo.mobile.gui.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.nickpopyk.carina.demo.mobile.gui.pages.common.HomePageBase;
import com.nickpopyk.carina.demo.mobile.gui.pages.common.RegisterPageBase;
import com.nickpopyk.carina.demo.utils.IConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

    @FindBy(id = "carina_logo")
    private ExtendedWebElement carinaLogo;

    @FindBy(id = "next_button")
    ExtendedWebElement nextButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isNextButtonPresent() {
        return nextButton.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isCarinaLogoPresent() {
        return carinaLogo.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isPageOpened(){
        return carinaLogo.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT) && nextButton.isElementPresent();
    }

    @Override
    public RegisterPageBase clickNextButton() {
        nextButton.click();
        return initPage(getDriver(), RegisterPageBase.class);
    }
}
