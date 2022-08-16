package com.nickpopyk.wish.demo.mobile.gui.pages.android;

import com.nickpopyk.wish.demo.mobile.gui.pages.common.CategoryPageBase;
import com.nickpopyk.wish.demo.mobile.gui.pages.common.ProductPageBase;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CategoryPageBase.class)
public class CategoryPage extends CategoryPageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(xpath = "//*[contains(@resource-id, 'drawer_activity_toolbar')]/*[contains(@class, 'android.widget.LinearLayout')]/*[contains(@class, 'android.widget.TextView')]")
    private ExtendedWebElement categoryTitle;

    @FindBy(xpath = "//*[contains(@resource-id, 'top_text_view')]")
    private ExtendedWebElement topTextView;

    @FindBy(xpath = "//*[contains(@resource-id, 'recycler')]/*")
    private List<ExtendedWebElement> productsList;

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return categoryTitle.isElementPresent(THREE_SECONDS_TIMEOUT) && topTextView.isElementPresent(THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isProductListPresent(){
        return productsList.get(0).isElementPresent(FIVE_SECONDS_TIMEOUT);
    }

    @Override
    public String getCategoryTitle(){
        return categoryTitle.getText();
    }

    @Override
    public ProductPageBase chooseProduct(int index){
        LOGGER.info("selecting product №" + index + " ...");
        waitUntil(ExpectedConditions.visibilityOfElementLocated(productsList.get(0).getBy()), FIVE_SECONDS_TIMEOUT);
        ExtendedWebElement product =  productsList.get(index);
        product.click();
        return initPage(getDriver(), ProductPageBase.class);
    }
}
