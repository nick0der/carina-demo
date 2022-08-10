package com.nickpopyk.wish.demo.mobile.gui.pages.common;

import com.nickpopyk.wish.demo.utils.WishAbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class CartPageBase extends WishAbstractPage {
    public CartPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract List<List<String>> getProductsInfo();

    public abstract String readTitleOfCartElement(int index);

    public abstract String readCharacteristicsOfCartElement(int index);

    public abstract boolean removeFromCart(int index);

    public abstract void waitForLoading();
}
