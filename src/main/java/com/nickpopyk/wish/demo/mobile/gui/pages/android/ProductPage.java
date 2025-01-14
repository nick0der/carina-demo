package com.nickpopyk.wish.demo.mobile.gui.pages.android;

import com.nickpopyk.wish.demo.mobile.gui.pages.common.CartPageBase;
import com.nickpopyk.wish.demo.mobile.gui.pages.common.ProductPageBase;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ProductPageBase.class)
public class ProductPage extends ProductPageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(xpath = "//*[contains(@resource-id, 'drawer_activity_toolbar')]/*[contains(@class, 'android.widget.LinearLayout')]/*[contains(@class, 'android.widget.TextView')]")
    private ExtendedWebElement detailsTitle;

    @FindBy(id = "com.contextlogic.wish:id/bottom_nav_cart_icon")
    private ExtendedWebElement cartIcon;

    @FindBy(id = "com.contextlogic.wish:id/product_title_text_view")
    private ExtendedWebElement productTitle;

    @FindBy(id = "com.contextlogic.wish:id/add_to_cart_button")
    private ExtendedWebElement addToCartButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'recycler_view')]")
    private ExtendedWebElement selectMenuWindow;

    @FindBy(id = "com.contextlogic.wish:id/add_to_cart_offer_dialog_button")
    private ExtendedWebElement offerDialogButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'end_button')]")
    private ExtendedWebElement closeButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return detailsTitle.isElementPresent() && Objects.equals(detailsTitle.getText(), "Details");
    }

    @Override
    public boolean isCartIconPresent(){
        return cartIcon.isElementPresent(THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isProductTitlePresent(){
        return productTitle.isElementPresent(THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isAddToCartButtonPresent(){
        return addToCartButton.isElementPresent(THREE_SECONDS_TIMEOUT);
    }

    @Override
    public String addToCart(){
        List<String> productCharacteristics = new ArrayList<>();
        addToCartButton.click();
        boolean foundAvailableOption;

        while (selectMenuWindow.isElementPresent(THREE_SECONDS_TIMEOUT)) {

            List<ExtendedWebElement> optionList = selectMenuWindow.findExtendedWebElements(By.xpath(".//*"));
            foundAvailableOption = false; // To check if we found available option

            // Loop through options
            for (int i = 0; i < optionList.size(); i++){
                ExtendedWebElement sideInfo = optionList.get(i).findExtendedWebElements(By.xpath(".//*[contains(@resource-id, 'add_to_cart_dialog_fragment_row_right_side_info_section')]")).get(i);

                if (sideInfo.isElementPresent()) { //Find first available option
                    ExtendedWebElement optionToChoose = optionList.get(i).findExtendedWebElements(By.xpath(".//*[contains(@resource-id, 'add_to_cart_dialog_fragment_row_option')]")).get(i);
                    productCharacteristics.add(optionToChoose.getText());
                    LOGGER.info("selecting '" + optionToChoose.getText() + "' option");
                    optionToChoose.click();
                    pause(ONE_SECOND_TIMEOUT);
                    foundAvailableOption = true;
                    break;
                }
            }
            if (!foundAvailableOption) {
                throw new RuntimeException("No available options found");
            }

        }
        offerDialogButton.clickIfPresent(THREE_SECONDS_TIMEOUT);
        closeButton.click();

        return String.join(", ", productCharacteristics);
    }

    @Override
    public CartPageBase navigateToCart(){
        cartIcon.click();
        return initPage(getDriver(), CartPageBase.class);
    }

    @Override
    public String getProductTitle(){
        waitUntil(ExpectedConditions.visibilityOfElementLocated(productTitle.getBy()), THREE_SECONDS_TIMEOUT);
        return productTitle.getText();
    }
}
