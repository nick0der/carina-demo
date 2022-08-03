package com.nickpopyk.carina.demo;

import com.nickpopyk.carina.demo.mobile.gui.pages.common.RegisterPageBase;
import com.nickpopyk.carina.demo.mobile.gui.pages.common.WebViewPageBase;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.report.testrail.TestRailCases;
import com.nickpopyk.carina.demo.mobile.gui.pages.common.HomePageBase;
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
        Assert.assertTrue(homePage.isPageOpened(), "[Home page] Home page is not opened");
        softAssert.assertTrue(homePage.isCarinaLogoPresent(), "[Home page] Carina logo is not present");
        softAssert.assertTrue(homePage.isNextButtonPresent(), "[Home page] Next button is not present");

        RegisterPageBase registerPage = homePage.clickNextButton();
        Assert.assertTrue(registerPage.isPageOpened(), "[Register page] Register page is not opened");

        //Verify elements are present
        softAssert.assertTrue(registerPage.isBackButtonPresent(), "[Register page] Back button is not present");
        softAssert.assertTrue(registerPage.isNameInputPresent(), "[Register page] Name input is not present");
        softAssert.assertTrue(registerPage.isPasswordInputPresent(), "[Register page] Password input is not present");
        softAssert.assertTrue(registerPage.isItemByTextPresent(MALE_RADIO_BTN_TEXT), "[Register page] Male radio is not present");
        softAssert.assertTrue(registerPage.isItemByTextPresent(FEMALE_RADIO_BTN_TEXT), "[Register page] Female radio is not present");
        softAssert.assertTrue(registerPage.isAgreeCheckboxPresent(), "[Register page] Agree checkbox is not present");
        softAssert.assertTrue(registerPage.isSignUpButtonPresent(), "[Register page] Sign up button is not present");
        softAssert.assertTrue(registerPage.isItemByTextPresent(TITLE), "[Register page] Carina title is not present");

        //Verify radio buttons and checkbox
        softAssert.assertFalse(registerPage.isRadioButtonByTextActive("Male"), "[Register page] Male radio button should be turned off");
        softAssert.assertFalse(registerPage.isRadioButtonByTextActive("Female"), "[Register page] Female radio button should be turned off");
        registerPage.clickRadioButtonByText(MALE_RADIO_BTN_TEXT);
        softAssert.assertTrue(registerPage.isRadioButtonByTextActive("Male"), "[Register page] Male radio button should be turned on");
        softAssert.assertFalse(registerPage.isRadioButtonByTextActive("Female"), "[Register page] Female radio button should be turned off");
        registerPage.clickRadioButtonByText(FEMALE_RADIO_BTN_TEXT);
        softAssert.assertFalse(registerPage.isRadioButtonByTextActive("Male"), "[Register page] Male radio button should be turned off");
        softAssert.assertTrue(registerPage.isRadioButtonByTextActive("Female"), "[Register page] Female radio button should be turned on");
        softAssert.assertTrue(registerPage.checkAgreeCheckbox(), "[Register page] Agree checkbox is not checked");
        softAssert.assertFalse(registerPage.uncheckAgreeCheckbox(), "[Register page] Agree checkbox should not be checked");

        softAssert.assertAll();
    }

    @TestRailCases(testCasesId = "0002")
    @Test(description = "Verify if sign up button is clickable without filling name field")
    @MethodOwner(owner = "nickpopyk")
    public void testSignUpFormWithoutNameField(){
        RegisterPageBase registerPage = openRegisterPage();
        registerPage.fillFormExceptNameField();
        Assert.assertFalse(registerPage.isSignUpButtonClickable(), "[Register page] Sign up button should not be clickable");
    }

    @TestRailCases(testCasesId = "0003")
    @Test(description = "Verify if sign up button is clickable without filling password field")
    @MethodOwner(owner = "nickpopyk")
    public void testSignUpFormWithoutPasswordField(){
        RegisterPageBase registerPage = openRegisterPage();
        registerPage.fillFormExceptPasswordField();
        Assert.assertFalse(registerPage.isSignUpButtonClickable(), "[Register page] Sign up button should not be clickable");
    }

    @TestRailCases(testCasesId = "0004")
    @Test(description = "Verify if sign up button is clickable without selecting gender radio button")
    @MethodOwner(owner = "nickpopyk")
    public void testSignUpFormWithoutRadioButtons(){
        RegisterPageBase registerPage = openRegisterPage();
        registerPage.fillFormExceptRadioButtons();
        Assert.assertFalse(registerPage.isSignUpButtonClickable(), "[Register page] Sign up button should not be clickable");
    }

    @TestRailCases(testCasesId = "0005")
    @Test(description = "Verify if sign up button is clickable without checking checkbox")
    @MethodOwner(owner = "nickpopyk")
    public void testSignUpFormWithoutCheckbox(){
        RegisterPageBase registerPage = openRegisterPage();
        registerPage.fillFormExceptCheckbox();
        Assert.assertFalse(registerPage.isSignUpButtonClickable(), "[Register page] Sign up button should not be clickable");
    }

    @TestRailCases(testCasesId = "0006")
    @Test(description = "Verify sign up form with valid data")
    @MethodOwner(owner = "nickpopyk")
    public void testSignUpFormValid(){
        RegisterPageBase registerPage = openRegisterPage();
        registerPage.fillForm();
        Assert.assertTrue(registerPage.isSignUpButtonClickable(), "[Register page] Sign up button should be clickable");
        WebViewPageBase webViewPage = registerPage.clickSignUpButton();
        Assert.assertTrue(webViewPage.isPageOpened());
    }

    private RegisterPageBase openRegisterPage(){
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        Assert.assertTrue(homePage.isPageOpened(), "[Home page] Home page is not opened");
        RegisterPageBase registerPage = homePage.clickNextButton();
        Assert.assertTrue(registerPage.isPageOpened(), "[Register page] Register page is not opened");
        return registerPage;
    }

}
