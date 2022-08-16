package com.nickpopyk.wish.demo.mobile.gui.pages.android;

import com.nickpopyk.wish.demo.mobile.gui.pages.common.WishlistPageBase;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = WishlistPageBase.class)
public class WishlistPage extends WishlistPageBase {

    @FindBy(xpath = "//*[contains(@resource-id, 'drawer_activity_toolbar_container')]//*[contains(@resource-id, 'drawer_activity_toolbar')]/androidx.appcompat.widget.LinearLayoutCompat")
    private ExtendedWebElement wishlistTopBarOptions;

    @FindBy(xpath = "(//*[contains(@resource-id, 'drawer_activity_toolbar_container')]//*[contains(@resource-id, 'drawer_activity_toolbar')]/androidx.appcompat.widget.LinearLayoutCompat/*)[2]")
    private ExtendedWebElement deleteButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'multi_button_dialog_fragment_title')]")
    private ExtendedWebElement modalTitle;

    @FindBy(xpath = "//*[contains(@resource-id, 'multi_button_dialog_fragment_choices_container')]/android.widget.Button")
    private ExtendedWebElement confirmButton;

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return wishlistTopBarOptions.isElementPresent(FIVE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isDeleteButtonPresent(){
        return deleteButton.isElementPresent(FIVE_SECONDS_TIMEOUT);
    }

    @Override
    public void deleteWishlist(){
        deleteButton.click();
        waitUntil(ExpectedConditions.visibilityOfElementLocated(modalTitle.getBy()), THREE_SECONDS_TIMEOUT);
        if (modalTitle.getText().equalsIgnoreCase("Oops!")){
            Assert.fail("[Wishlist Page] Cannot delete because wishlist is not empty");
        }
        confirmButton.click();
        pause(ONE_SECOND_TIMEOUT);
        confirmButton.click();
    }
}
