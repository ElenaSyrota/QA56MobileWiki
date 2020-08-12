package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CurrentArticlePageHelper extends PageBase {
    @FindBy(id = "org.wikipedia:id/view_page_title_text")
    WebElement articleTitle;
    @FindBy(className = "android.support.v7.app.ActionBar$Tab")
    WebElement addToReadingListButton;
    @FindBy(id = "org.wikipedia:id/onboarding_button")
    WebElement gotItButton;
    @FindBy(id = "org.wikipedia:id/text_input")
    WebElement inputReadingListNameField;
    @FindBy(id = "android:id/button1")
    WebElement okButtonAddReadingList;
    @FindBy(xpath = "//*[@content-desc ='Navigate up']")
    WebElement closeArticleButton;

    private String article;
    public CurrentArticlePageHelper(WebDriver driver, String article) {
        super(driver);
        this.article = article;
        PageFactory.initElements(driver,this);
    }

    public CurrentArticlePageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsVisible(articleTitle,40);
        return this;
    }

    public boolean isOpenedCorrectly() {
        return articleTitle.getText().equals(article);
    }
    public CurrentArticlePageHelper addToNewReadingList(String listName) {
        addToReadingListButton.click();
        this.waitUntilElementIsClickable(gotItButton,15);
        gotItButton.click();
        waitUntilElementIsClickable(inputReadingListNameField,15);
        inputReadingListNameField.clear();
        inputReadingListNameField.sendKeys(listName);
        okButtonAddReadingList.click();
        //waitUntilElementIsInvisible(okButtonAddReadingList,15);
        waitUntilElementIsClickable(closeArticleButton,15);
        return this;

    }

    public CurrentArticlePageHelper closeArticle(){
        closeArticleButton.click();
        return this;
    }

    public void swipeToFooter() throws InterruptedException {
        swipeUpToElement(By.xpath("//*[@text ='View page in browser']"), 20);
    }

    public boolean isEndOfArticle() {
        return driver.findElements(By.id("org.wikipedia:id/read_more_header")).size()>0;
    }
}