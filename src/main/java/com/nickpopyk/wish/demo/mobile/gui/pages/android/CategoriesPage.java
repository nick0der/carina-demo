package com.nickpopyk.wish.demo.mobile.gui.pages.android;

import com.nickpopyk.wish.demo.mobile.gui.pages.common.CategoriesPageBase;
import com.nickpopyk.wish.demo.mobile.gui.pages.common.CategoryPageBase;
import com.nickpopyk.wish.demo.utils.Categories;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = CategoriesPageBase.class)
public class CategoriesPage extends CategoriesPageBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(xpath = "//*[contains(@resource-id, 'drawer_activity_toolbar_container')]/child::*/child::" +
            "*[contains(@class, 'android.widget.LinearLayout')]/child::*[contains(@text, 'Categories')]")
    ExtendedWebElement categoriesLogo;

    @FindBy(xpath = "//*[contains(@resource-id, 'categories_list')]/child::*/child::*[contains(@resource-id, 'title')]")
    List<ExtendedWebElement> categoriesList;


    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return categoriesLogo.isElementPresent();
    }

    @Override
    public CategoryPageBase selectCategory(Categories category) {
        LOGGER.info("selecting '" + category.getValue() + "' category...");

        for (ExtendedWebElement categoryItem : categoriesList) {
            if (categoryItem.getText().equalsIgnoreCase(category.getValue())) {
                categoryItem.click();
                return initPage(getDriver(), CategoryPageBase.class);
            }
        }
        throw new RuntimeException("Unable to open category: " + category.getValue());
    }
}
