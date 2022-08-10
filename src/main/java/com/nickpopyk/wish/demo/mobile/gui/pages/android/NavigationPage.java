package com.nickpopyk.wish.demo.mobile.gui.pages.android;

import com.nickpopyk.wish.demo.mobile.gui.pages.common.NavigationPageBase;
import com.nickpopyk.wish.demo.utils.Pages;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = NavigationPageBase.class)
public class NavigationPage extends NavigationPageBase {

    @FindBy(xpath = "//*[contains(@resource-id, 'bottom_nav_view')]//*[contains(@text, '%s')]")
    private ExtendedWebElement menuItemByText;

    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isNavigationItemPresent(Pages page){
        return menuItemByText.format(page.getValue()).isElementPresent(THREE_SECONDS_TIMEOUT);
    }

    @Override
    public <T extends AbstractPage> T navigateToPage(Pages pageTo){
        menuItemByText.format(pageTo.getValue()).click();
        return (T) initPage(getDriver(), pageTo.getPageClass());
    }
}
