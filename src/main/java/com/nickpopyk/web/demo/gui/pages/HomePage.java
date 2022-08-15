package com.nickpopyk.web.demo.gui.pages;

import com.nickpopyk.web.demo.gui.components.BrandsComponent;
import com.nickpopyk.web.demo.gui.components.FooterMenu;
import com.nickpopyk.web.demo.gui.components.TopNavbar;
import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(id = "social-connect")
    private TopNavbar topNavbar;
    @FindBy(xpath = "//div[@class='footer-inner']//div[@id='footmenu']")
    private FooterMenu footerMenu;

    @FindBy(xpath = "//div[contains(@class, 'brandmenu-v2')]")
    private BrandsComponent brandsComponent;

    public HomePage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(R.CONFIG.get(Configuration.Parameter.URL.getKey()));
    }

    public TopNavbar getTopNavbar(){
        return topNavbar;
    }

    public FooterMenu getFooterMenu() {
        return footerMenu;
    }

    public BrandsComponent getBrandsComponent() {
        return brandsComponent;
    }
}
