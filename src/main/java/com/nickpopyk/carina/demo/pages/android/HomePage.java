package com.nickpopyk.carina.demo.pages.android;

import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.nickpopyk.carina.demo.pages.common.HomePageBase;
import com.nickpopyk.carina.demo.pages.common.RegisterPageBase;
import com.nickpopyk.carina.demo.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

    @FindBy(id = "carina_logo")
    private ExtendedWebElement carinaLogo;

    @FindBy(id = "next_button")
    ExtendedWebElement nextBtn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isNextBtnPresent() {
        return nextBtn.isElementPresent(Constants.TIMEOUT);
    }

    @Override
    public boolean isCarinaLogoPresent() {
        return carinaLogo.isElementPresent(Constants.TIMEOUT);
    }

    @Override
    public boolean isPageOpened(){
        return carinaLogo.isElementPresent(Constants.TIMEOUT);
    }

    @Override
    public RegisterPageBase clickNextBtn() {
        nextBtn.click();
        return initPage(getDriver(), RegisterPageBase.class);
    }
}
