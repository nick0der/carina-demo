package com.nickpopyk.carina.demo.mobile.gui.pages.android;

import com.nickpopyk.carina.demo.mobile.gui.pages.common.*;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import static com.nickpopyk.carina.demo.utils.Pages.*;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = MenuPageBase.class)
public class MenuPage extends MenuPageBase {

    @FindBy(xpath = "//*[@text = '%s']")
    private ExtendedWebElement menuItemByText;

    @FindBy(id = "nav_view")
    private ExtendedWebElement navView;

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return navView.isElementPresent();
    }

    @Override
    public boolean isMenuItemByTextPresent(String text){
        return menuItemByText.format(text).isElementPresent();
    }

    public WebViewPageBase openWebViewPage() {
        menuItemByText.format(WEB_VIEW.getValue()).click();
        return initPage(getDriver(), WebViewPageBase.class);
    }

    @Override
    public ChartsPageBase openChartsPage() {
        menuItemByText.format(CHARTS.getValue()).click();
        return initPage(getDriver(), ChartsPageBase.class);
    }

    @Override
    public MapPageBase openMapPage() {
        menuItemByText.format(MAP.getValue()).click();
        return initPage(getDriver(), MapPageBase.class);
    }

    @Override
    public UIElementsPageBase openUIElementsPage() {
        menuItemByText.format(UI_ELEMENTS.getValue()).click();
        return initPage(getDriver(), UIElementsPageBase.class);
    }
}
