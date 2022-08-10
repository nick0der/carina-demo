package com.nickpopyk.wish.demo.mobile.gui.pages.android;

import com.nickpopyk.wish.demo.mobile.gui.pages.common.CategoryPageBase;
import com.nickpopyk.wish.demo.mobile.gui.pages.common.ProductPageBase;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CategoryPageBase.class)
public class CategoryPage extends CategoryPageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(xpath = "//*[contains(@resource-id, 'drawer_activity_toolbar')]/*[contains(@class, 'android.widget.LinearLayout')]/*[contains(@class, 'android.widget.TextView')]")
    ExtendedWebElement categoryTitle;

    @FindBy(xpath = "//*[contains(@resource-id, 'top_text_view')]")
    ExtendedWebElement topTextView;

    @FindBy(xpath = "//*[contains(@resource-id, 'recycler')]/*")
    List<ExtendedWebElement> productsList;

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    //TODO Add more checks
    @Override
    public boolean isPageOpened(){
        return categoryTitle.isElementPresent() && topTextView.isElementPresent();
    }

    @Override
    public String readCategoryTitle(){
        return categoryTitle.getText();
    }

    @Override
    public ProductPageBase chooseProduct(int index){
        LOGGER.info("selecting product №" + index);
        ExtendedWebElement product =  productsList.get(index);
        product.click();
        return initPage(getDriver(), ProductPageBase.class);
    }
}
