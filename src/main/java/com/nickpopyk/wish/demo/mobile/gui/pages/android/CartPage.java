package com.nickpopyk.wish.demo.mobile.gui.pages.android;

import com.nickpopyk.wish.demo.mobile.gui.pages.common.CartPageBase;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;
import java.util.stream.Collectors;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CartPageBase.class)
public class CartPage extends CartPageBase {

    @FindBy (xpath = "//*[contains(@resource-id, 'drawer_activity_toolbar')]//*[contains(@class, 'android.widget.TextView')]")
    private ExtendedWebElement cartTitle;

    @FindBy(xpath = "//*[contains(@resource-id, 'list')]")
    private ExtendedWebElement cartListContainer;

    @FindBy(xpath = "//*[contains(@resource-id, 'list')]/child::*[contains(@class, 'android.view.ViewGroup')]")
    private List<ExtendedWebElement> cartList;

    @FindBy(id = "com.contextlogic.wish:id/cart_fragment_loading_view")
    private ExtendedWebElement cartLoadingView;

    @FindBy(xpath = "//*[contains(@resource-id, 'multi_button_dialog_fragment_choices_container')]/child::*[contains(@class, 'android.widget.Button')]")
    private ExtendedWebElement confirmRemoveButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'list')]//*[contains(@resource-id, 'cart_fragment_cart_items_item_row_title')]")
    private List<ExtendedWebElement> productTitles;

    @FindBy(xpath = "//*[contains(@resource-id, 'list')]//*[contains(@resource-id, 'cart_fragment_cart_items_item_row_size_color_text')]")
    private List<ExtendedWebElement> productCharacteristics;

    @FindBy(xpath = "//*[contains(@resource-id, 'list')]//*[contains(@resource-id, 'cart_fragment_cart_items_item_row_title') or contains(@resource-id, 'cart_fragment_cart_items_item_row_size_color_text')]")
    private List<ExtendedWebElement> productInfo;

    @FindBy(xpath = "//*[contains(@resource-id, 'cart_payment_structure_view')]")
    private ExtendedWebElement cartPaymentStructureView;

    @FindBy(xpath = "//*[contains(@resource-id, 'header_view_section_title') and @text='Items in cart']")
    private ExtendedWebElement itemsInCartTitle;

    @FindBy(xpath = "//*[contains(@resource-id, 'empty_message')]")
    private ExtendedWebElement emptyMessage;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return cartLoadingView.isElementPresent();
    }

    @Override
    public boolean isCartTitlePresent(){
        return cartTitle.isElementPresent(FIVE_SECONDS_TIMEOUT);
    }

    @Override
    public List<List<String>> getProductsInfo(){
        if (emptyMessage.isElementPresent()){
            return new ArrayList<>();
        }

        List<String> titles = productTitles.stream().map(ExtendedWebElement::getText).collect(Collectors.toList());
        List<String> characteristics = productCharacteristics.stream().map(ExtendedWebElement::getText).collect(Collectors.toList());
        List<List<String>> productInfo = new ArrayList<>(new ArrayList<>());

        // Make list sizes equal
        if (titles.size() > characteristics.size()){
            titles.remove(titles.size() - 1);
        } else if (titles.size() < characteristics.size()) {
            characteristics.remove(0);
        }

        //Merge lists into one
        for (int i = 0; i < titles.size(); i++) {
            productInfo.add(Arrays.asList(titles.get(i), characteristics.get(i)));
        }

        while (!cartPaymentStructureView.isElementPresent()){
            swipeInContainer(cartListContainer, Direction.UP, ONE_TIME_SWIPE, THOUSAND_DURATION_TIME);

            List<String> visibleTitles =  productTitles.stream().map(ExtendedWebElement::getText).collect(Collectors.toList());
            List<String> visibleCharacteristics = productCharacteristics.stream().map(ExtendedWebElement::getText).collect(Collectors.toList());
            List<List<String>> visibleProductInfo = new ArrayList<>(new ArrayList<>());

            //Make list sizes equal
            if (visibleTitles.size() > visibleCharacteristics.size()){
                visibleTitles.remove(visibleTitles.size() - 1);
            } else if (visibleTitles.size() < visibleCharacteristics.size()) {
                visibleCharacteristics.remove(0);
            }

            //Merge lists into one
            for (int i = 0; i < visibleTitles.size(); i++) {
                visibleProductInfo.add(Arrays.asList(visibleTitles.get(i), visibleCharacteristics.get(i)));
            }

            //Add product info if it is not present
            for (List<String> element: visibleProductInfo) {
                if (!productInfo.contains(element)) {
                    productInfo.add(element);
                }
            }
        }
        return productInfo;
    }

    @Override
    public String getTitleOfCartElement(int index){
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
        waitUntil(ExpectedConditions.visibilityOfElementLocated(confirmRemoveButton.getBy()), THREE_SECONDS_TIMEOUT);
        confirmRemoveButton.click();
        waitUntil(ExpectedConditions.visibilityOfElementLocated(cartTitle.getBy()), THREE_SECONDS_TIMEOUT);
        return true;
    }

    @Override
    public void waitForLoading(){
        waitUntil(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(itemsInCartTitle.getBy()),
                ExpectedConditions.visibilityOfElementLocated(emptyMessage.getBy())
        ), FIVE_SECONDS_TIMEOUT);
    }
}
