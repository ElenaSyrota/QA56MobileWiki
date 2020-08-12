package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CurrentArticlePageHelper;
import pages.SearchPageHelper;

import java.net.MalformedURLException;
import java.net.URL;

public class SearchTests extends TestBase {

    SearchPageHelper searchPage;
    CurrentArticlePageHelper articleSeleniumSoftware;

    @BeforeMethod
    public void initTests() {
        searchPage = PageFactory.initElements(driver, SearchPageHelper.class);
        articleSeleniumSoftware = new CurrentArticlePageHelper(driver, "Selenium (software)");
        searchPage.waitUntilPageIsLoaded();
    }

    @Test
    public void wikiTest() {

        Assert.assertEquals("Search Wikipedia", searchPage.getSearchFieldText());

    }

    @Test
    public void searchArticle() {

        searchPage.enterSearchText("Selenium");
        Assert.assertTrue("Error!", searchPage.existArticleInSearchResult("Selenium (software)"));

    }

    @Test
    public void searchArticleAndOpen() {
        searchPage.enterSearchText("Selenium");
        searchPage.openArticle("Selenium (software)");
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        //Assert.assertTrue(articleSeleniumSoftware.isOpenedCorrect());
    }

    @Test
    public void openReadList() {
        //searchPage.openReadList();
        Assert.assertTrue("Error!", searchPage.existArticleInSearchResult("Selenium (software)"));

        //android.widget.FrameLayout[@content-desc="My lists"]/android.widget.ImageView
    }
    @Test
    public  void searchArticleOpenAndSwipeUp() throws InterruptedException {
        String search = "Selenium ";
        String article = "Selenium (software)";
        searchPage.enterSearchText(search);
        searchPage.openArticle(article);
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        articleSeleniumSoftware.swipeUp();
    }

    @Test
    public  void searchArticleOpenAndSwipe() throws InterruptedException {
        String search = "Selenium ";
        String article = "Selenium (software)";
        searchPage.enterSearchText(search);
        searchPage.openArticle(article);
        articleSeleniumSoftware.waitUntilPageIsLoaded();
        articleSeleniumSoftware.swipeToFooter();
        Assert.assertTrue(articleSeleniumSoftware.isEndOfArticle());
    }

    @Test
    public void searchArticleAndOpenMenuArticle()  {
        String search = "Selenium ";
        String article = "Selenium (software)";
        searchPage.enterSearchText(search);
        searchPage.openArticleMenu(article);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        searchPage.closeArticleMenu();
    }


}
