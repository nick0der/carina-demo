package com.nickpopyk.carina.demo.mobile.gui.pages.android;

import com.nickpopyk.carina.demo.mobile.gui.pages.common.WebViewPageBase;
import com.nickpopyk.carina.demo.utils.IConstants;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = WebViewPageBase.class)
public class WebViewPage extends WebViewPageBase {

    @FindBy(xpath = "//*[@text = 'Web View']")
    private ExtendedWebElement webView;

    @FindBy(id = "lin")
    private ExtendedWebElement linearLayout;

    public WebViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return webView.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT) &&
                linearLayout.isElementPresent(IConstants.THREE_SECONDS_TIMEOUT);
    }
}
