package com.nickpopyk.carina.demo.utils;

import com.nickpopyk.carina.demo.mobile.gui.pages.common.*;

public enum Pages {
    WEB_VIEW("Web View", WebViewPageBase.class),
    CHARTS("Charts", ChartsPageBase.class),
    MAP("Map", MapPageBase.class),
    UI_ELEMENTS("UI elements", UIElementsPageBase.class);
    private final String value;
    private final Class<? extends ContentPageBase> pageClass;

    Pages(String value, Class<? extends ContentPageBase> pageClass) {
        this.value = value;
        this.pageClass = pageClass;
    }

    public String getValue() {
        return value;
    }

    public Class<? extends ContentPageBase> getPageClass() {
        return pageClass;
    }
}
