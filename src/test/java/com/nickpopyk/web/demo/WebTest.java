package com.nickpopyk.web.demo;

import com.nickpopyk.web.demo.gui.components.LoginPopUp;
import com.nickpopyk.web.demo.gui.pages.*;
import com.nickpopyk.web.demo.services.impls.LoginService;
import com.nickpopyk.web.demo.utils.Dropdowns;
import com.nickpopyk.web.demo.utils.SortReviewsBy;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.report.testrail.TestRailCases;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class WebTest implements IAbstractTest {

    @DataProvider(name = "search-data")
    public Object[][] dataProviderForSearch(){
        return new Object[][] {{"iPhone"}, {"Xiaomi"}, {"Samsung"}, {"Nokia"}, {"Asus"}};
    }

    @TestRailCases(testCasesId = "0001")
    @Test(description = "Verify login")
    @MethodOwner(owner = "nick0der")
    public void testLogin() {
        // Open GSM Arena home page and verify page is opened
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        Assert.assertTrue(homePage.getTopNavbar().isLoginButtonPresent(), "Login button is not present");

        // Open login popup and verify elements are present
        LoginPopUp loginPopUp = homePage.getTopNavbar().openLoginPopUp();
        Assert.assertTrue(homePage.getTopNavbar().isLoginPopUpPresent(), "Login popup is not present");
        Assert.assertTrue(loginPopUp.isLoginTitlePresent(), "Login title is not present");
        Assert.assertTrue(loginPopUp.isEmailTextFieldPresent(), "Email text field is not present");
        Assert.assertTrue(loginPopUp.isPasswordTestFieldPresent(), "Password text field is not present");
        Assert.assertTrue(loginPopUp.isSubmitButtonPresent(), "Submit button is not present");
        Assert.assertTrue(loginPopUp.isForgotPasswordLinkPresent(), "'Forgot password' link  is not present");

        // Login and verify login successful
        LoginService loginService = new LoginService(loginPopUp, getDriver());
        loginService.login();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        Assert.assertTrue(homePage.getTopNavbar().isAccountButtonPresent(), "Login is not successful, account button is not present");
    }


    @TestRailCases(testCasesId = "0002")
    @Test(description = "Verify searching process", dataProvider = "search-data")
    @MethodOwner(owner = "nick0der")
    public void testVerifySearchingProcess(String searchText) {
        SoftAssert softAssert = new SoftAssert();

        // Open GSM Arena home page and verify page is opened
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        // Open news page and verify elements are present
        NewsPage newsPage = homePage.getFooterMenu().openNewsPage();
        Assert.assertTrue(newsPage.isPageOpened(), "News page is not opened");
        softAssert.assertTrue(newsPage.isArticleInfoNamePresent(), "Article info name is not present");
        softAssert.assertTrue(newsPage.isSearchInputPresent(), "Search input is not present");
        softAssert.assertTrue(newsPage.isSearchButtonPresent(), "Search button is not present");

        // Search and verify results
        newsPage.typeSearchText(searchText);
        newsPage.clickSearchButton();
        String expectedTitle = String.format("Results for \"%s\"", searchText);
        Assert.assertTrue(newsPage.getArticleInfoName().equalsIgnoreCase(expectedTitle), "News info name has wrong text");
        for (String title: newsPage.getArticleTitles()) {
            Assert.assertTrue(StringUtils.containsIgnoreCase(title, searchText), "Article does not contain searched result");
        }
        softAssert.assertAll();
    }

    @TestRailCases(testCasesId = "0003")
    @Test(description = "Verify phone finder")
    @MethodOwner(owner = "nick0der")
    public void testVerifyPhoneFinder() {
        String brand = "Xiaomi";
        String yourSearchText = "Your search returned [0-9]+ results\\.(?s:.*)";
        String refineSearchText = "(?s:.*)To refine your search click here\\.";
        String noteText = "Note: Please report wrong Phone Finder results here.";

        // Open GSM Arena home page and verify page is opened
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");

        // Go to phone finder page
        Assert.assertTrue(homePage.getBrandsComponent().isPhoneFinderButtonPresent(), "Phone finder button is not present");
        PhoneFinderPage phoneFinderPage = homePage.getBrandsComponent().openPhoneFinder();
        Assert.assertTrue(phoneFinderPage.isPageOpened(), "Phone finder page is not opened");

        // Select brand and verify show button
        phoneFinderPage.chooseDropdownOption(Dropdowns.BRAND, brand);
        Assert.assertTrue(phoneFinderPage.isShowButtonPresent(), "Show button is not present");
        Assert.assertTrue(phoneFinderPage.doesShowButtonHaveResultsText(), "Result text is not present");
        int resultsNumber = phoneFinderPage.getResultsNumber();

        //Verify resultsPage
        ResultsPage resultsPage = phoneFinderPage.clickShowButton();
        Assert.assertTrue(resultsPage.isPageOpened(), "Results page is not opened");
        Assert.assertTrue(resultsPage.isResultTextPresent(), "Result text is not present");
        Assert.assertTrue(resultsPage.getResultText().matches(yourSearchText), "Returned results text is not present");
        Assert.assertTrue(resultsPage.getResultText().matches(refineSearchText), "Refine search text is not present");
        Assert.assertTrue(resultsPage.isClickHereButtonPresent(), "'click here' button is not present");
        Assert.assertEquals(resultsPage.getResultsNumber(), resultsNumber, "Result numbers are not the same");

        //Check if all results are correct
        for (String title: resultsPage.getPhonesTitles()) {
            Assert.assertTrue(title.toLowerCase().contains(brand.toLowerCase()), "Title does not contain '" + brand + "' word");
        }

        //Verify note text
        Assert.assertTrue(resultsPage.isNoteTextPresent(), "Note text is not present");
        Assert.assertEquals(resultsPage.getNoteText(), noteText, "Note text is wrong");

        //Click 'click here' button and verify corresponding page is opened
        resultsPage.clickClickHereButton();
        Assert.assertTrue(phoneFinderPage.isPageOpened(), "Phone finder page is not opened");
    }

    @TestRailCases(testCasesId = "0004")
    @Test(description = "Verify opinions on phone page")
    @MethodOwner(owner = "nick0der")
    public void testVerifyOpinionsOnPhonePage() {
        String brand = "Apple";

        // Open GSM Arena home page and login
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        LoginPopUp loginPopUp = homePage.getTopNavbar().openLoginPopUp();
        LoginService loginService = new LoginService(loginPopUp, getDriver());
        loginService.login();

        // Open brand phones page, then open first popular phone from this brand
        PhonesPage phonesPage = homePage.getBrandsComponent().openBrandLink(brand);
        Assert.assertTrue(phonesPage.isPageOpened(), "Page with " + brand + " phones is not opened");
        phonesPage.selectPopularityTab();
        String phoneTitle =  phonesPage.getFirstPhoneTitle();
        PhonePage phonePage = phonesPage.openFirstPhone();
        Assert.assertTrue(phonePage.isPageOpened(), "Phone page is not opened");
        Assert.assertTrue(phonePage.getPhoneTitle().toLowerCase().contains(phoneTitle.toLowerCase()), "Phone title is wrong");

        // Open reviews and verify sorting by 'Best rating'
        ReviewsPage reviewsPage = phonePage.openOpinionsTab();
        Assert.assertTrue(reviewsPage.isPageOpened(), "Reviews page is not opened");
        reviewsPage.setSortBySelect(SortReviewsBy.RATING);
        List<Integer> sortedVotes = reviewsPage.getVotesList().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        Assert.assertEquals(reviewsPage.getVotesList(), sortedVotes, "Reviews are not sorted correctly by rating");

        // Check random comment uprating and unrating
        int randomComment = new Random().nextInt(reviewsPage.getVotesList().size());
        int previousRating = reviewsPage.getVotesList().get(randomComment);
        reviewsPage.voteComment(randomComment, true); //vote
        Assert.assertEquals(reviewsPage.getCommentRating(randomComment), previousRating + 1, "Comment is not rated");
        reviewsPage.voteComment(randomComment, false); //unvote
        Assert.assertEquals(reviewsPage.getCommentRating(randomComment), previousRating, "Comment is not unrated");

        // Check sorting by 'Newest first'
        reviewsPage.setSortBySelect(SortReviewsBy.NEWEST);
        List<Date> reviewDates = reviewsPage.getDatesList();
        List<Date> sortedDates = reviewDates.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        Assert.assertEquals(reviewDates, sortedDates, "Reviews are not sorted correctly by date");
    }
}
