package com.nickpopyk.wish.demo;

import com.nickpopyk.wish.demo.mobile.gui.pages.common.*;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.report.testrail.TestRailCases;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.nickpopyk.wish.demo.utils.Categories.FASHION;
import static com.nickpopyk.wish.demo.utils.IConstants.*;
import static com.nickpopyk.wish.demo.utils.Pages.CATEGORIES;

public class WishMobileTest implements IAbstractTest {

    @TestRailCases(testCasesId = "0001")
    @Test(description = "Verify login, finding the product, adding to cart and removing")
    @MethodOwner(owner = "nick0der")
    public void testAddProductToCart() {
        String email = "thistestn123@gmail.com";
        String password = "thistest123";

        NotificationPageBase notificationPage = initPage(getDriver(), NotificationPageBase.class);
        Assert.assertTrue(notificationPage.isPageOpened(), "[Notification page] Notification page is not opened");
        Assert.assertTrue(notificationPage.isPermissionAllowButtonPresent(), "[Notification page] Permission allow button is not present");
        Assert.assertTrue(notificationPage.isPermissionDenyButtonPresent(), " [Notification page] Permission deny button is not present");
        LoginPageBase loginPage = notificationPage.clickPermissionAllowButton();

        // Login and open home page
        HomePageBase homePage = loginPage.login(email, password);
        pause(THIRTY_SECONDS_TIMEOUT);
        Assert.assertTrue(homePage.isPageOpened(), "[Home page] Home Page is not opened");

        // Open categories page
        NavigationPageBase navigationPage = initPage(getDriver(), NavigationPageBase.class);
        CategoriesPageBase categoriesPage = navigationPage.navigateToPage(CATEGORIES);
        Assert.assertTrue(categoriesPage.isPageOpened(), "[Home page] Categories page is not opened");

        // Select category, verify corresponding page is opened
        pause(THREE_SECONDS_TIMEOUT);
        CategoryPageBase categoryPage = categoriesPage.selectCategory(FASHION);
        Assert.assertTrue(categoryPage.isPageOpened(), "[Categories Page] Category page is not opened");
        Assert.assertEquals(categoryPage.readCategoryTitle(), FASHION.getValue(), "[Category Page] Wrong category opened. Should be '" + FASHION.getValue() + "'.");
        pause(FIVE_SECONDS_TIMEOUT);

        // Select product
        ProductPageBase productPage = categoryPage.chooseProduct(0);
        Assert.assertTrue(productPage.isPageOpened(), "[Category page] Product page is not opened");
        pause(FIVE_SECONDS_TIMEOUT);

        // Add product to cart, verify product title and chosen characteristics
        String productTitle = productPage.readProductTitle();
        String productCharacteristics = productPage.addToCart();
        CartPageBase cartPage = productPage.navigateToCart();
        pause(FIVE_SECONDS_TIMEOUT);
        Assert.assertTrue(cartPage.isPageOpened(), "[Product page] Cart page is not opened");
        Assert.assertEquals(productTitle, cartPage.readTitleOfCartElement(0), "[Cart page] Product title is wrong");
        Assert.assertEquals(productCharacteristics, cartPage.readCharacteristicsOfCartElement(0), "[Cart page] Product characteristics are wrong");

        // Remove product from cart
        Assert.assertTrue(cartPage.removeFromCart(0), "[Cart page] Product is not removed");
    }
}
