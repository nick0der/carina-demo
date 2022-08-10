package com.nickpopyk.wish.demo.mobile.gui.pages.common;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class CartPageBase extends AbstractPage {
    public CartPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract String readTitleOfCartElement(int index);

    public abstract String readCharacteristicsOfCartElement(int index);

    public abstract boolean removeFromCart(int index);
}
