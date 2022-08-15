package com.nickpopyk.web.demo.gui.components;

import com.nickpopyk.web.demo.gui.pages.PhoneFinderPage;
import com.nickpopyk.web.demo.gui.pages.PhonesPage;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;

import static com.nickpopyk.web.demo.utils.IConstants.THREE_SEC_TIMEOUT;

public class BrandsComponent extends AbstractUIObject {

    @FindBy(xpath = "//p[@class='pad']//a[@class='pad-single pad-finder']")
    private ExtendedWebElement phoneFinderButton;

    @FindBy(xpath = "//div[contains(@class, 'brandmenu-v2')]/ul/li/a")
    private List<ExtendedWebElement> brandLinks;

    public BrandsComponent(WebDriver driver) {
        super(driver);
    }

    public BrandsComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isPhoneFinderButtonPresent(){
        return phoneFinderButton.isElementPresent(THREE_SEC_TIMEOUT);
    }

    public PhonesPage openBrandLink(String brand){
        ExtendedWebElement brandButton = Objects.requireNonNull(brandLinks.stream().filter(link -> {
            return brand.equalsIgnoreCase(link.getText());
        }).findAny().orElse(null));
        String brandUrl = brandButton.getAttribute("href");
        brandButton.click();
        return new PhonesPage(driver, brandUrl);
    }

    public PhoneFinderPage openPhoneFinder(){
        phoneFinderButton.click();
        return new PhoneFinderPage(driver);
    }
}
