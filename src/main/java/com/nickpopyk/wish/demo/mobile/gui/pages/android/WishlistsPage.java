package com.nickpopyk.wish.demo.mobile.gui.pages.android;

import com.nickpopyk.wish.demo.mobile.gui.pages.common.CreateWishlistModalBase;
import com.nickpopyk.wish.demo.mobile.gui.pages.common.WishlistPageBase;
import com.nickpopyk.wish.demo.mobile.gui.pages.common.WishlistsPageBase;
import com.qaprosoft.carina.core.foundation.utils.factory.DeviceType;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = WishlistsPageBase.class)
public class WishlistsPage extends WishlistsPageBase {

    @FindBy(xpath = "(//*[contains(@resource-id, 'loading_page_content_view')]/android.view.ViewGroup/*[contains(@resource-id, 'title')])[1]")
    private ExtendedWebElement noWishlistsTitle;

    @FindBy(xpath = "//*[contains(@resource-id, 'action_bar_item_icon')]")
    private ExtendedWebElement createNewWishlistButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'listview')]")
    private ExtendedWebElement listView;

    @FindBy(xpath = "//*[contains(@resource-id, 'fragment_wishlist_profile_item_title_container')]//*[contains(@resource-id, 'fragment_wishlist_profile_item_title')]")
    private List<ExtendedWebElement> wishlistTitlesList;

    @FindBy(xpath = "//*[contains(@resource-id, 'fragment_wishlist_profile_item_title_container')]//*[contains(@resource-id, 'fragment_wishlist_profile_item_title') and @text = '%s']")
    private ExtendedWebElement wishlistTitleByText;

    @FindBy(xpath = "(//*[contains(@resource-id, 'fragment_wishlist_profile_item_title_container')]//*[contains(@resource-id, 'fragment_wishlist_profile_item_title') and @text = '%s']//parent::*//parent::*//parent::*)[1]//*[contains(@resource-id, 'fragment_wishlist_menu_button')]")
    private ExtendedWebElement wishlistSettingsButtonByText;

    @FindBy(xpath = "(//*[contains(@resource-id, 'content')])[1]/android.widget.RelativeLayout/android.widget.TextView")
    private ExtendedWebElement renameButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'profile_fragment_footer_action_button')]")
    private ExtendedWebElement actionButton;

    @FindBy(xpath = "//*[contains(@resource-id, 'multi_button_dialog_fragment_title')]")
    private ExtendedWebElement modalTitle;

    public WishlistsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened(){
        return noWishlistsTitle.isElementPresent(THREE_SECONDS_TIMEOUT) || listView.isElementPresent(THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isNoWishlistsTitlePresent(){
        return noWishlistsTitle.isElementPresent(THREE_SECONDS_TIMEOUT);
    }

    @Override
    public boolean isListViewPresent(){
        return listView.isElementPresent(THREE_SECONDS_TIMEOUT);
    }

    @Override
    public void createNewWishlist(String wishlistName){
        createNewWishlistButton.click();
        CreateWishlistModalBase createWishlistModal = initPage(getDriver(), CreateWishlistModalBase.class);
        createWishlistModal.typeWishlistNameInput(wishlistName);
        createWishlistModal.clickConfirmCreateWishlistButton();
        if (modalTitle.isElementPresent(ONE_SECOND_TIMEOUT) && modalTitle.getText().equalsIgnoreCase("Oops!")) {
            Assert.fail("[Wishlists page] Cannot create wishlist '" + wishlistName + "' because name is already taken");
        }
    }

    @Override
    public void renameWishlist(String name, String rename){
        scrollToTheTop();
        if (!swipe(wishlistTitleByText.format(name), listView, Direction.UP, TEN_TIMES_SWIPE, TWO_HUNDRED_DURATION_TIME)) {
            Assert.fail("[Wishlists Page] There is no wishlist named '" + name + "'");
        }
        wishlistSettingsButtonByText.format(name).click();
        waitUntil(ExpectedConditions.visibilityOfElementLocated(renameButton.getBy()), THREE_SECONDS_TIMEOUT);
        renameButton.click();

        CreateWishlistModalBase renameWishlistModal = initPage(getDriver(), CreateWishlistModalBase.class);
        renameWishlistModal.typeWishlistNameInput(rename);
        renameWishlistModal.clickConfirmCreateWishlistButton();
    }

    @Override
    public void deleteWishlist(String name){
        scrollToTheTop();
        if (!swipe(wishlistTitleByText.format(name), listView, Direction.UP, TEN_TIMES_SWIPE, TWO_HUNDRED_DURATION_TIME)) {
            Assert.fail("[Wishlists Page] There is no wishlist named '" + name + "'");
        }
        wishlistTitleByText.format(name).click();
        WishlistPageBase wishlistPage = initPage(getDriver(), WishlistPageBase.class);
        wishlistPage.deleteWishlist();
    }

    @Override
    public List<String> getWishlistsTitles(){
        List<String> wishlistTitles = wishlistTitlesList.stream().map(ExtendedWebElement::getText).collect(Collectors.toList());
        List<String> visibleTitles;

        scrollToTheTop();

        //Scroll to the bottom and get all the titles
        while (actionButton.isElementNotPresent(THREE_SECONDS_TIMEOUT)) {
            swipeInContainer(listView, Direction.UP, ONE_TIME_SWIPE, TWO_HUNDRED_DURATION_TIME);
            visibleTitles =  wishlistTitlesList.stream().map(ExtendedWebElement::getText).collect(Collectors.toList());

            for (String title: visibleTitles) {
                if (!wishlistTitles.contains(title)){
                    wishlistTitles.add(title);
                }
            }
        }

        return wishlistTitles;
    }

    @Override
    public boolean isWishlistTitlePresentIncludingInvisible(String title){
        return getWishlistsTitles().contains(title);
    }

    @Override
    public boolean isWishlistTitlePresent(String title){
        return wishlistTitlesList.stream().map(ExtendedWebElement::getText).collect(Collectors.toList()).contains(title);
    }

    private void scrollToTheTop(){
        List<String> visibleTitles;
        List<String> previousTitles;

        //Scroll to the top
        while (true){
            previousTitles =  wishlistTitlesList.stream().map(ExtendedWebElement::getText).collect(Collectors.toList());
            swipeInContainer(listView, Direction.DOWN, ONE_TIME_SWIPE, HUNDRED_DURATION_TIME);
            visibleTitles =  wishlistTitlesList.stream().map(ExtendedWebElement::getText).collect(Collectors.toList());
            if (previousTitles.equals(visibleTitles)) {
                break;
            }
        }
    }
}
