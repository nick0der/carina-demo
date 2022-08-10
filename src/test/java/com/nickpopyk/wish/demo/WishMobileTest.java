package com.nickpopyk.wish.demo;

import com.nickpopyk.wish.demo.utils.Pages;
import com.nickpopyk.wish.demo.mobile.gui.pages.common.*;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.report.testrail.TestRailCases;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.nickpopyk.wish.demo.utils.Categories.FASHION;
import static com.nickpopyk.wish.demo.utils.IConstants.*;
import static com.nickpopyk.wish.demo.utils.Pages.*;

public class WishMobileTest implements IAbstractTest {

    @TestRailCases(testCasesId = "0001")
    @Test(description = "Verify login, finding the product, adding to cart and removing")
    @MethodOwner(owner = "nick0der")
    public void testAddProductToCart() {
        HomePageBase homePage = openHomePage();

        // Init and verify navigation menu
        NavigationPageBase navigationPage = initPage(getDriver(), NavigationPageBase.class);
        for (Pages page : Pages.values()) {
            Assert.assertTrue(navigationPage.isNavigationItemPresent(page), String.format("[Home Page] Item '%s' is not present in navigation",  page.getValue()));
        }

        // Open categories page
        CategoriesPageBase categoriesPage = navigationPage.navigateToPage(CATEGORIES);
        Assert.assertTrue(categoriesPage.isPageOpened(), "[Home Page] Categories page is not opened");

        // Select category, verify corresponding page is opened
        CategoryPageBase categoryPage = categoriesPage.selectCategory(FASHION);
        Assert.assertTrue(categoryPage.isPageOpened(), "[Categories Page] Category page is not opened");
        Assert.assertTrue(categoryPage.isProductListPresent(), "[Category Page] Product list is not present");
        Assert.assertEquals(categoryPage.getCategoryTitle(), FASHION.getValue(), "[Category Page] Wrong category opened. Should be '" + FASHION.getValue() + "'.");

        // Select product and verify product page
        ProductPageBase productPage = categoryPage.chooseProduct(0);
        Assert.assertTrue(productPage.isPageOpened(), "[Category Page] Product page is not opened");
        Assert.assertTrue(productPage.isCartIconPresent(), "[Product Page] Cart icon is not present");
        Assert.assertTrue(productPage.isProductTitlePresent(), "[Product Page] Product title is not present");
        Assert.assertTrue(productPage.isAddToCartButtonPresent(), "[Product Page] 'Add to cart' button is not present");

        // Add product to cart, verify product title and chosen characteristics
        String productTitle = productPage.getProductTitle();
        String productCharacteristics = productPage.addToCart();
        CartPageBase cartPage = productPage.navigateToCart();
        Assert.assertTrue(cartPage.isPageOpened(), "[Product Page] Cart page is not opened");
        Assert.assertTrue(cartPage.isCartTitlePresent(), "[Cart Page] Cart title is not present");
        Assert.assertEquals(productTitle, cartPage.getTitleOfCartElement(0), "[Cart page] Product title is wrong");
        Assert.assertEquals(productCharacteristics, cartPage.readCharacteristicsOfCartElement(0), "[Cart page] Product characteristics are wrong");

        // Remove product from cart
        Assert.assertTrue(cartPage.removeFromCart(0), "[Cart Page] Product is not removed");
    }

    @TestRailCases(testCasesId = "0002")
    @Test(description = "Test cart scrolling")
    @MethodOwner(owner = "nick0der")
    public void testCartScroll() {
        HomePageBase homePage = openHomePage();
        String titleToVerify = "Young Men's Business Tosp Men's Personality and Comfortable T Shirt";
        String characteristicsToVerify = "S, Orange";

        // Init and verify navigation menu
        NavigationPageBase navigationPage = initPage(getDriver(), NavigationPageBase.class);
        for (Pages page : Pages.values()) {
            Assert.assertTrue(navigationPage.isNavigationItemPresent(page), String.format("[Home Page] Item '%s' is not present in navigation",  page.getValue()));
        }

        // Open cart page
        CartPageBase cartPage = navigationPage.navigateToPage(CART);
        cartPage.waitForLoading();
        Assert.assertTrue(cartPage.isPageOpened(), "[Home Page] Cart page is not opened");
        Assert.assertTrue(cartPage.isCartTitlePresent(), "[Cart Page] Cart title is not present");

        // Check if specified product is in the cart
        List<List<String>> productsInfo = cartPage.getProductsInfo();
        Assert.assertTrue(
                productsInfo.contains(Arrays.asList(titleToVerify, characteristicsToVerify)),
                "[Cart] Element '" + titleToVerify + " " + " : " + characteristicsToVerify + "' is not present"
        );
    }

    public HomePageBase openHomePage() {
        String email = "thistestn123@gmail.com";
        String password = "thistest123";

        NotificationPageBase notificationPage = initPage(getDriver(), NotificationPageBase.class);
        Assert.assertTrue(notificationPage.isPageOpened(), "[Notification Page] Notification page is not opened");
        Assert.assertTrue(notificationPage.isPermissionAllowButtonPresent(), "[Notification Page] Permission allow button is not present");
        Assert.assertTrue(notificationPage.isPermissionDenyButtonPresent(), " [Notification Page] Permission deny button is not present");
        LoginPageBase loginPage = notificationPage.clickPermissionAllowButton();

        // Login and open home page
        Assert.assertTrue(loginPage.isPageOpened(), "[Notification Page] Login page is not opened");
        Assert.assertTrue(loginPage.isEmailInputPresent(), "[Login Page] Email input is not present");
        Assert.assertTrue(loginPage.isPasswordInputPresent(), "[Login Page] Password input is not present");
        Assert.assertTrue(loginPage.isSignInButtonPresent(), "[Login Page] Sign in button is not present");
        HomePageBase homePage = loginPage.login(email, password);

        pause(THIRTY_SECONDS_TIMEOUT); // Pause for two-factor authentication
        Assert.assertTrue(homePage.isPageOpened(), "[Home Page] Home Page is not opened");
        return homePage;
    }
}
