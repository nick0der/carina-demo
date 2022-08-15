package com.nickpopyk.web.demo.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.nickpopyk.web.demo.utils.IConstants.THREE_SEC_TIMEOUT;

public class ReviewsPage extends AbstractPage {

    @FindBy(xpath = "(//select[@name='nSortNew'])[1]")
    private ExtendedWebElement sortBySelect;

    @FindBy(xpath = "//ul[contains(@class, 'votes')]")
    private List<ExtendedWebElement> votesList;

    @FindBy(xpath = "//li[contains(@class, 'uvote')]")
    private List<ExtendedWebElement> reactionButtons;

    @FindBy(xpath = "//span[contains(@class, 'upvote')]")
    private ExtendedWebElement upvoteButton;

    public ReviewsPage(WebDriver driver, String url) {
        super(driver);
        setPageAbsoluteURL(url);
    }

    public void setSortBySelect(String by){
        sortBySelect.select(by);
    }
    public List<Integer> getVotesList(){
        return votesList.stream().map(element -> Integer.parseInt(element.getAttribute("data-votes"))).collect(Collectors.toList());
    }

    public int voteComment(int index){
        if (reactionButtons.get(0).isElementNotPresent(THREE_SEC_TIMEOUT)) {
            Assert.fail("Reaction buttons are not present. Maybe you are not logged in.");
        }
        reactionButtons.get(index).click();
        upvoteButton.click();


        //TODO modify this method
//        if (votesList.get(index).getElement().findElements(By.xpath(".//*")).size() == 0){
//            return 0;
//        } else if (votesList.get(index).getElement().findElement(By.xpath(".//li//span")).isDisplayed() &&
//                !Objects.equals(votesList.get(index).getElement().findElement(By.xpath(".//li//span")).getText(), "")) {
//            return Integer.parseInt(votesList.get(index).getElement().findElement(By.xpath(".//li//span")).getText());
//        } else if (votesList.get(index).getElement().findElement(By.xpath(".//li//span")).isDisplayed() &&
//                Objects.equals(votesList.get(index).getElement().findElement(By.xpath(".//li//span")).getText(), "")) {
//            return 1;
//        }
        return 0;
    }
}
