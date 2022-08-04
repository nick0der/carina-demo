package com.nickpopyk.carina.demo.mobile.gui.pages.android;

import com.nickpopyk.carina.demo.mobile.gui.pages.common.MapPageBase;
import com.nickpopyk.carina.demo.mobile.gui.pages.common.SidebarMenuPageBase;
import com.nickpopyk.carina.demo.utils.IConstants;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = MapPageBase.class)
public class MapPage extends MapPageBase {

    @FindBy(xpath = "//*[@content-desc='Navigate up']")
    private ExtendedWebElement menuButton;

    @FindBy(xpath = "//*[@text='Map']")
    private ExtendedWebElement mapTitle;

    @FindBy(xpath = "//*[@content-desc='Google Map']")
    private ExtendedWebElement googleMap;

    public MapPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return mapTitle.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT) &&
                googleMap.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT);
    }

    @Override
    public SidebarMenuPageBase openSidebarMenuPage() {
        menuButton.click();
        return initPage(getDriver(), SidebarMenuPageBase.class);
    }
}
