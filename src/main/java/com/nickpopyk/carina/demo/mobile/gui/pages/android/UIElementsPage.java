package com.nickpopyk.carina.demo.mobile.gui.pages.android;

import com.nickpopyk.carina.demo.mobile.gui.pages.common.MenuPageBase;
import com.nickpopyk.carina.demo.mobile.gui.pages.common.UIElementsPageBase;
import com.nickpopyk.carina.demo.utils.IConstants;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = UIElementsPageBase.class)
public class UIElementsPage extends UIElementsPageBase {

    @FindBy(xpath = "//*[@content-desc='Navigate up']")
    private ExtendedWebElement menuButton;

    @FindBy(xpath = "//*[@text='UI elements']")
    private ExtendedWebElement uiElementsTitle;

    @FindBy(id = "editText")
    private ExtendedWebElement textInput;

    public UIElementsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return uiElementsTitle.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT) &&
                textInput.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT);
    }

    @Override
    public MenuPageBase openMenuPage() {
        menuButton.click();
        return initPage(getDriver(), MenuPageBase.class);
    }
}
