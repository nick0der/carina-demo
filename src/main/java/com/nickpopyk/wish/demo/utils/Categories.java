package com.nickpopyk.wish.demo.utils;

public enum Categories {
    FASHION("Fashion"),
    DRESSES("Dresses"),
    TOPS("Tops"),
    BOTTOMS("Bottoms"),
    ACCESSORIES("Accessories"),
    SHOES("Shoes"),
    WATCHES("Watches"),
    WALLETS_AND_BAGS("Wallets & Bags"),
    MAKEUP_AND_BEAUTY("Makeup & Beauty"),
    BABY_AND_KIDS("Baby & Kids"),
    HOBBIES("Hobbies"),
    PHONE_UPGRADES("Phone Upgrades"),
    GADGETS("Gadgets"),
    TOOLS("Tools"),
    AUTOMOTIVE("Automotive"),
    KITCHEN("Kitchen"),
    HOME_DECOR("Home Decor"),
    STATIONERY("Stationery"),
    PET_ACCESSORIES("Pet Accessories");

    private final String value;

    Categories(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
