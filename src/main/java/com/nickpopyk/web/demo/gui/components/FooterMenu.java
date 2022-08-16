package com.nickpopyk.web.demo.gui.components;

import com.nickpopyk.web.demo.gui.pages.NewsPage;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class FooterMenu extends AbstractUIObject {

    @FindBy(xpath = "//div[@class='footer-inner']//div[@id='footmenu']//a[@href='%s']")
    private ExtendedWebElement menuItemByLink;

    public FooterMenu(WebDriver driver) {
        super(driver);
    }

    public FooterMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public NewsPage openNewsPage(){
        menuItemByLink.format("news.php3").click();
        return new NewsPage(driver);
    }
}
