package com.nickpopyk.carina.demo.mobile.gui.pages.android;

import com.nickpopyk.carina.demo.mobile.gui.pages.common.ChartsPageBase;
import com.nickpopyk.carina.demo.mobile.gui.pages.common.MenuPageBase;
import com.nickpopyk.carina.demo.utils.IConstants;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ChartsPageBase.class)
public class ChartsPage extends ChartsPageBase {

    @FindBy(xpath = "//*[@content-desc='Navigate up']")
    private ExtendedWebElement menuButton;

    @FindBy(xpath = "//*[@text='Charts']")
    private ExtendedWebElement chartsTitle;

    @FindBy(id = "chartView")
    private ExtendedWebElement chartView;

    public ChartsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return chartsTitle.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT) &&
                chartView.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT);
    }

    @Override
    public MenuPageBase openMenuPage() {
        menuButton.click();
        return initPage(getDriver(), MenuPageBase.class);
    }
}
