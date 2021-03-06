package tests;

import org.testng.Assert;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CurrentArticlePageHelper;
import pages.MyCurrentListHelper;
import pages.MyListsPageHelper;
import pages.SearchPageHelper;

public class CurrentArticleTests extends TestBase {
    SearchPageHelper searchPage;
    CurrentArticlePageHelper articleSearchSoftware;
    MyListsPageHelper myListsPage;
    MyCurrentListHelper myCurrentList;

    @BeforeMethod(alwaysRun = true)
    public void testsInit(){
        searchPage = PageFactory.initElements(driver, SearchPageHelper.class);
        articleSearchSoftware = new CurrentArticlePageHelper(driver, "Selenium (software)");
        myListsPage = PageFactory.initElements(driver, MyListsPageHelper.class);
        myCurrentList = PageFactory.initElements(driver, MyCurrentListHelper.class);
        searchPage.waitUntilPageIsLoaded();
    }

    @Test(groups= {"smoke","regression"})
    public void addToNewReadingList()  {
        String search = "Selenium ";
        String article = "Selenium (software)";
        searchPage.enterSearchText(search)
                .openArticle(article);
        articleSearchSoftware.waitUntilPageIsLoaded()
                .addToNewReadingList("My List")
                .closeArticle();
        searchPage.waitUntilPageIsLoaded()
                .openMyListsPage();
        myListsPage.waitUntilPageIsLoaded()
                .openList("My List");
        myCurrentList.waitUntilPageIsLoaded();
        Assert.assertTrue(myCurrentList.existsArticle(article));
    }
    @Test(groups= {"smoke"})
    public void addToNewReadingListAndDelete()  {
        String search = "Selenium ";
        String article = "Selenium (software)";
        searchPage.enterSearchText(search)
                .openArticle(article);
        articleSearchSoftware.waitUntilPageIsLoaded()
                .addToNewReadingList("My List")
                .closeArticle();
        searchPage.waitUntilPageIsLoaded()
                .openMyListsPage();
        myListsPage.waitUntilPageIsLoaded()
                .openList("My List");
        myCurrentList.waitUntilPageIsLoaded()
                .deleteArticle(article)
                .closeReadingList();
        myListsPage.waitUntilPageIsLoaded()
                .openList("My List");
        Assert.assertFalse(myCurrentList.existsArticle(article));
    }

    @Test
    public void addToNewReadingListRotateDeleteAbdBackground()  {
        String search = "Selenium ";
        String article = "Selenium (software)";
        searchPage.enterSearchText(search)
                .openArticle(article);
        articleSearchSoftware.waitUntilPageIsLoaded()
                .addToNewReadingList("My List")
                .closeArticle();
        searchPage.waitUntilPageIsLoaded()
                .openMyListsPage();
        myListsPage.waitUntilPageIsLoaded()
                .openList("My List");
        myCurrentList.rotateScreenLandscape();
        myCurrentList.waitUntilPageIsLoaded()
                .deleteArticle(article)
                .closeReadingList();
        myCurrentList.rotateScreenPORTRAIT();
        myListsPage.waitUntilPageIsLoaded()
                .openList("My List");
        myCurrentList.runBackGround(3);
        Assert.assertFalse(myCurrentList.existsArticle(article));
    }


}