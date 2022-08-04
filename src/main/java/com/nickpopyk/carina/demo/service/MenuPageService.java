package com.nickpopyk.carina.demo.service;

import com.nickpopyk.carina.demo.mobile.gui.pages.common.ContentPageBase;
import com.nickpopyk.carina.demo.mobile.gui.pages.common.MenuPageBase;
import com.nickpopyk.carina.demo.utils.Pages;

public class MenuPageService {
    private final MenuPageBase menuPage;

    public MenuPageService(MenuPageBase menuPage) {
        this.menuPage = menuPage;
    }

    public ContentPageBase openPage(Pages page) {
        switch (page) {
            case  WEB_VIEW:
                return menuPage.openWebViewPage();
            case CHARTS:
                return menuPage.openChartsPage();
            case MAP:
                return menuPage.openMapPage();
            case UI_ELEMENTS:
                return menuPage.openUIElementsPage();
            default:
                break;
        }
        return null;
    }
}
