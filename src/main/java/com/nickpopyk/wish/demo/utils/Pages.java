package com.nickpopyk.wish.demo.utils;

import com.nickpopyk.wish.demo.mobile.gui.pages.common.*;
import com.qaprosoft.carina.core.gui.AbstractPage;

public enum Pages {
    HOME("Home", HomePageBase.class),
    CATEGORIES("Categories", CategoriesPageBase.class),
    CLIPS("Clips", ClipsPageBase.class),
    CART("Cart", CartPageBase.class),
    MENU("Menu", MenuPageBase.class);
    private final String value;
    private final Class<? extends AbstractPage> pageClass;

    Pages(String value, Class<? extends AbstractPage> pageClass) {
        this.value = value;
        this.pageClass = pageClass;
    }

    public String getValue() {
        return value;
    }

    public Class<? extends AbstractPage> getPageClass() {
        return pageClass;
    }
}
