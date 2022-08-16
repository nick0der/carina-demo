package com.nickpopyk.carina.demo.service;

import com.nickpopyk.carina.demo.mobile.gui.pages.common.RegisterPageBase;
import com.nickpopyk.carina.demo.mobile.gui.pages.common.WebViewPageBase;

public class RegisterPageService {

    private final RegisterPageBase registerPage;

    public RegisterPageService(RegisterPageBase registerPage) {
        this.registerPage = registerPage;
    }

    public WebViewPageBase register(String name, String password, String gender) {
        registerPage.typeName(name);
        registerPage.typePassword(password);
        registerPage.clickRadioButtonByText(gender);
        registerPage.checkAgreeCheckbox(true);
        return registerPage.clickSignUpButton();
    }
}