package com.nickpopyk.wish.demo.mobile.gui.pages.android;

import com.nickpopyk.wish.demo.mobile.gui.pages.common.CartPageBase;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.nickpopyk.wish.demo.utils.IConstants.THREE_SECONDS_TIMEOUT;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase {

    @FindBy(xpath = "//*[contains(@resource-id, 'list')]/child::*[contains(@class, 'android.view.ViewGroup')]")
    List<ExtendedWebElement> cartList;

    @FindBy(id = "com.contextlogic.wish:id/cart_fragment_loading_view")
    ExtendedWebElement cartLoadingView;

    @FindBy(xpath = "//*[contains(@resource-id, 'multi_button_dialog_fragment_choices_container')]/child::*[contains(@class, 'android.widget.Button')]")
    ExtendedWebElement confirmRemoveButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return cartLoadingView.isElementPresent();
    }

    @Override
    public String readTitleOfCartElement(int index){
        ExtendedWebElement itemTitle = cartList.get(index).findExtendedWebElement(By.xpath(".//*[contains(@resource-id, 'cart_fragment_cart_items_item_row_title')]"));
        return itemTitle.getText();
    }

    @Override
    public String readCharacteristicsOfCartElement(int index){
        ExtendedWebElement itemTitle = cartList.get(index).findExtendedWebElement(By.xpath(".//*[contains(@resource-id, 'cart_fragment_cart_items_item_row_size_color_text')]"));
        return itemTitle.getText();
    }

    @Override
    public boolean removeFromCart(int index){
        ExtendedWebElement itemToDelete = cartList.get(index);
        itemToDelete.getElement().isDisplayed();
        ExtendedWebElement deleteButton = itemToDelete.findExtendedWebElement(By.xpath(".//*[contains(@resource-id, 'cart_fragment_cart_items_remove_button')]"));
        deleteButton.click();
        pause(THREE_SECONDS_TIMEOUT);
        confirmRemoveButton.click();
        pause(THREE_SECONDS_TIMEOUT);
        return true;
    }
}
