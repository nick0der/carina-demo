package com.nickpopyk.web.demo;

import com.nickpopyk.web.demo.gui.components.LoginPopUp;
import com.nickpopyk.web.demo.gui.pages.NewsPage;
import com.nickpopyk.web.demo.services.impls.LoginService;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.report.testrail.TestRailCases;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.nickpopyk.web.demo.gui.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.apache.commons.lang3.StringUtils;

public class WebTest implements IAbstractTest {

    @DataProvider(name = "search-data")
    public Object[][] dataProviderForSearch(){
        return new Object[][] {{"iPhone"}, {"Xiaomi"}, {"Samsung"}, {"Nokia"}, {"Asus"}};
    }

    @TestRailCases(testCasesId = "0001")
    @Test(description = "Verify login and searching process", dataProvider = "search-data")
    @MethodOwner(owner = "nick0der")
    public void testVerifySearchingProcess(String searchText) {
        SoftAssert softAssert = new SoftAssert();

        // Open GSM Arena home page and verify page is opened
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        Assert.assertTrue(homePage.getTopNavbar().isLoginButtonPresent(), "Login button is not present");

        // Open login popup and verify elements are present
        LoginPopUp loginPopUp = homePage.getTopNavbar().openLoginPopUp();
        softAssert.assertTrue(homePage.getTopNavbar().isLoginPopUpPresent(), "Login popup is not present");
        softAssert.assertTrue(loginPopUp.isLoginTitlePresent(), "Login title is not present");
        softAssert.assertTrue(loginPopUp.isEmailTextFieldPresent(), "Email text field is not present");
        softAssert.assertTrue(loginPopUp.isPasswordTestFieldPresent(), "Password text field is not present");
        softAssert.assertTrue(loginPopUp.isSubmitButtonPresent(), "Submit button is not present");
        softAssert.assertTrue(loginPopUp.isForgotPasswordLinkPresent(), "'Forgot password' link  is not present");
        softAssert.assertAll();

        // Login and verify login successful
        LoginService loginService = new LoginService(loginPopUp, getDriver());
        loginService.login();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        Assert.assertTrue(homePage.getTopNavbar().isAccountButtonPresent(), "Login is not successful, account button is not present");

        // Open news page and verify elements are present
        NewsPage newsPage = homePage.getFooterMenu().openNewsPage();
        Assert.assertTrue(newsPage.isPageOpened(), "News page is not opened");
        softAssert.assertTrue(newsPage.isArticleInfoNamePresent(), "Article info name is not present");
        softAssert.assertTrue(newsPage.isSearchInputPresent(), "Search input is not present");
        softAssert.assertTrue(newsPage.isSearchButtonPresent(), "Search button is not present");
        softAssert.assertAll();

        // Search and verify results
        newsPage.typeSearchText(searchText);
        newsPage.clickSearchButton();
        String expectedTitle = String.format("Results for \"%s\"", searchText);
        Assert.assertTrue(newsPage.getArticleInfoName().equalsIgnoreCase(expectedTitle), "News info name has wrong text");
        for (String title: newsPage.getArticleTitles()) {
            Assert.assertTrue(StringUtils.containsIgnoreCase(title, searchText), "Article does not contain searched result");
        }
    }
}
