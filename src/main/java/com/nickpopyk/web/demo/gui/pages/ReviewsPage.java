package com.nickpopyk.web.demo.gui.pages;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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

    @FindBy(xpath = "//li[contains(@class, 'upost')]/time")
    private List<ExtendedWebElement> datesList;

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

    public List<Date> getDatesList(){
        List<Date> resultList;
        DateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        resultList = datesList.stream().map(element -> {
            try {
                return format.parse(element.getText());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        return resultList;
    }

    public void voteComment(int index){
        if (reactionButtons.get(0).isElementNotPresent(THREE_SEC_TIMEOUT)) {
            Assert.fail("Reaction buttons are not present. Maybe you are not logged in.");
        }
        reactionButtons.get(index).click();
        upvoteButton.click();
    }

    public int getCommentRating(int index){
        if (votesList.get(index).getElement().findElements(By.xpath(".//*")).size() == 0){
            return 0;
        } else if (votesList.get(index).getElement().findElements(By.xpath("./li/span")).size() != 0) {
            return Integer.parseInt(votesList.get(index).getElement().findElement(By.xpath("./li/span")).getText());
        } else return 1;
    }
}
