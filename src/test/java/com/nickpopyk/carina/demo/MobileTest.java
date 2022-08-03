package com.nickpopyk.carina.demo;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.report.testrail.TestRailCases;
import com.nickpopyk.carina.demo.pages.common.HomePageBase;
import com.nickpopyk.carina.demo.pages.common.RegisterPageBase;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MobileTest implements IAbstractTest {

    private final static String TITLE = "CARINA";
    private final static String MALE_RADIO_BTN_TEXT = "Male";
    private final static String FEMALE_RADIO_BTN_TEXT = "Female";

    @TestRailCases(testCasesId = "0001")
    @Test(description = "Verification of home page and register page")
    @MethodOwner(owner = "nickpopyk")
    public void testHomePageAndRegisterPage(){

        SoftAssert softAssert = new SoftAssert();

        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        softAssert.assertTrue(homePage.isCarinaLogoPresent(), "Carina logo is not present");
        softAssert.assertTrue(homePage.isNextBtnPresent(), "Next button is not present");

        RegisterPageBase registerPage = homePage.clickNextBtn();
        Assert.assertTrue(registerPage.isPageOpened(), "Register page is not opened");

        //Verify elements are present
        softAssert.assertTrue(registerPage.isBackBtnPresent(), "Back button is not present");
        softAssert.assertTrue(registerPage.isNameInputPresent(), "Name input is not present");
        softAssert.assertTrue(registerPage.isPasswordInputPresent(), "Password input is not present");
        softAssert.assertTrue(registerPage.isItemByTextPresent(MALE_RADIO_BTN_TEXT), "Male radio is not present");
        softAssert.assertTrue(registerPage.isItemByTextPresent(FEMALE_RADIO_BTN_TEXT), "Female radio is not present");
        softAssert.assertTrue(registerPage.isCheckboxPresent(), "Checkbox is not present");
        softAssert.assertTrue(registerPage.isSignUpBtnPresent(), "Sign up button is not present");
        softAssert.assertTrue(registerPage.isItemByTextPresent(TITLE), "Carina title is not present");

        //Verify radio buttons and checkbox
        softAssert.assertTrue(registerPage.clickItemByText(MALE_RADIO_BTN_TEXT), "Male radio button should be turned on");
        softAssert.assertTrue(registerPage.clickItemByText(FEMALE_RADIO_BTN_TEXT), "Female radio button should be turned on");
        softAssert.assertTrue(registerPage.clickCheckbox(), "Checkbox is not checked");
        softAssert.assertFalse(registerPage.clickCheckbox(), "Checkbox should not be checked");

        softAssert.assertAll();
    }

}
