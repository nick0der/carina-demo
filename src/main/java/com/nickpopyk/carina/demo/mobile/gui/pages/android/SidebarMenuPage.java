package com.nickpopyk.carina.demo.mobile.gui.pages.android;

import com.nickpopyk.carina.demo.mobile.gui.pages.common.*;
import com.nickpopyk.carina.demo.utils.Pages;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = SidebarMenuPageBase.class)
public class SidebarMenuPage extends SidebarMenuPageBase {

    @FindBy(xpath = "//*[@text = '%s']")
    private ExtendedWebElement menuItemByText;

    @FindBy(id = "nav_view")
    private ExtendedWebElement navView;

    public SidebarMenuPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return navView.isElementPresent();
    }

    @Override
    public boolean isMenuItemPresent(Pages pages){
        return menuItemByText.format(pages.getValue()).isElementPresent();
    }

    public ContentPageBase openContentPage(Pages page) {
        menuItemByText.format(page.getValue()).click();
        return initPage(getDriver(), page.getPageClass());
    }
}
