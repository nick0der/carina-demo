package com.nickpopyk.web.demo.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import static com.nickpopyk.web.demo.utils.IConstants.THREE_SEC_TIMEOUT;

public class PhonesPage extends AbstractPage {

    @FindBy(xpath = "//ul[@class='article-info-meta']/li[contains(@class, 'popularity')]/a")
    private ExtendedWebElement popularityButton;

    @FindBy(xpath = "(//div[@class='makers']/ul/li/a)[1]")
    private ExtendedWebElement firstPhone;

    public PhonesPage(WebDriver driver, String url) {
        super(driver);
        setPageAbsoluteURL(url);
    }

    public void selectPopularityTab(){
        popularityButton.click();
    }

    public PhonePage openFirstPhone(){
        String url = firstPhone.getAttribute("href");
        firstPhone.click();
        return new PhonePage(driver, url);
    }

    public String getFirstPhoneTitle(){
        return firstPhone.getText();
    }
}
