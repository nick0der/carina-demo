package com.nickpopyk.wish.demo.mobile.gui.pages.android;

import com.nickpopyk.wish.demo.mobile.gui.pages.common.MenuPageBase;
import com.nickpopyk.wish.demo.mobile.gui.pages.common.WishlistsPageBase;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = MenuPageBase.class)
public class MenuPage extends MenuPageBase {

    @FindBy(xpath = "//*[contains(@resource-id, 'menu_profile_name')]")
    private ExtendedWebElement profileNameTitle;

    @FindBy(xpath = "(//*[contains(@resource-id, 'menu_fragment_row_container')])[3]")
    private ExtendedWebElement wishlistButton;

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return profileNameTitle.isElementPresent(FIVE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isWishlistButtonPresent(){
        return wishlistButton.isElementPresent(THREE_SECONDS_TIMEOUT);
    }

    @Override
    public WishlistsPageBase openWishlistsPage(){
        waitUntil(ExpectedConditions.visibilityOfElementLocated(wishlistButton.getBy()), THREE_SECONDS_TIMEOUT);
        wishlistButton.click();
        return initPage(getDriver(), WishlistsPageBase.class);
    }
}
