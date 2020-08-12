package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyCurrentListHelper extends PageBase {
    @FindBy(id = "org.wikipedia:id/item_title")
    WebElement title;
    @FindBy(id = "org.wikipedia:id/page_list_item_title")
    List<WebElement> articleTitlesList;

    public MyCurrentListHelper(WebDriver driver) {
        super(driver);
    }

    public MyCurrentListHelper waitUntilPageIsLoaded(){
        waitUntilElementIsVisible(title,15);
        waitUntilAllElementsAreVisible(articleTitlesList,15);
        return this;
    }


    public boolean existsArticle(String article) {
        boolean flag = false;
        for(WebElement title: articleTitlesList){
            if(title.getText().equals(article)) flag = true;
        }
        return flag;
    }

    public void swipeRightToLeft() {
        String article = "Selenium (software)";
        AppiumDriver appDriver = (AppiumDriver) (driver);
        TouchAction action = new TouchAction(appDriver);


        WebElement articleName  =  driver.findElement(By.xpath(xPathArticleName(article)));

        //int x = (articleName.getLocation().x );
        int y = (articleName.getLocation().y) ;

        Dimension size = driver.manage().window().getSize();

        int screenWidth = driver.manage().window().getSize().width;
        int x1 = (int) (.9 * screenWidth);
        int x2 = (int) (.65 * screenWidth);
        int x3 = (int) (.2 * screenWidth);

        action.longPress(PointOption.point(x1, y))
                .waitAction()
                .moveTo(PointOption.point(x2, y))
                .moveTo(PointOption.point(x3, y))
                .release()
                .perform();

    }
    public String xPathArticleName(String article){
        return "//*[@text='" + article +"']";
    }

    public void openMyListReading() {
    }
}
