package com.nickpopyk.wish.demo.utils;

import com.qaprosoft.carina.core.foundation.utils.mobile.IMobileUtils;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class WishAbstractPage extends AbstractPage implements IMobileUtils, IConstants {

    public WishAbstractPage(WebDriver driver) {
        super(driver);
    }
}