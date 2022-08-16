package com.nickpopyk.wish.demo.mobile.gui.pages.android;

import com.nickpopyk.wish.demo.mobile.gui.pages.common.CreateWishlistModalBase;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CreateWishlistModalBase.class)
public class CreateWishlistModal extends CreateWishlistModalBase {

    @FindBy(xpath = "//*[contains(@resource-id, 'create_wishlist_title')]")
    private ExtendedWebElement createWishlistTitle;

    @FindBy(xpath = "//*[contains(@resource-id, 'create_wishlist_name_text')]")
    private ExtendedWebElement wishlistNameInput;

    @FindBy(xpath = "//*[contains(@resource-id, 'create_wishlist_modal_done_button')]")
    private ExtendedWebElement confirmCreateWishlistButton;

    public CreateWishlistModal(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return createWishlistTitle.isElementPresent();
    }

    @Override
    public boolean isWishlistNameInputPresent(){
        return wishlistNameInput.isElementPresent(THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isConfirmCreateWishlistButtonPresent(){
        return confirmCreateWishlistButton.isElementPresent(THREE_SECONDS_TIMEOUT);
    }

    @Override
    public void typeWishlistNameInput(String wishlistName){
        wishlistNameInput.type(wishlistName);
    }

    @Override
    public void clickConfirmCreateWishlistButton(){
        confirmCreateWishlistButton.click();
    }
}
