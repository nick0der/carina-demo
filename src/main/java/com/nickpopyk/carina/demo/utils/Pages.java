package com.nickpopyk.carina.demo.utils;

public enum Pages {
    WEB_VIEW("Web View"),
    CHARTS("Charts"),
    MAP("Map"),
    UI_ELEMENTS("UI elements");

    private final String value;

    Pages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}