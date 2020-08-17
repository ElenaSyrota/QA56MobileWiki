package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyCurrentListHelper extends PageBase {
    public Object closeReadingList;
    @FindBy(id = "org.wikipedia:id/item_title")
    WebElement title;
    @FindBy(id = "org.wikipedia:id/page_list_item_title")
    List<WebElement> articleTitlesList;
    @FindBy(xpath = "//*[@content-desc = 'Navigate up']")
    WebElement closeOption;

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

    public MyCurrentListHelper deleteArticle(String article) {
        WebElement articleTitle = driver.findElement(By.xpath("//*[@text = '"+article+"']"));
        int y = articleTitle.getLocation().y + (int)(articleTitle.getSize().height*0.5);
        swipeLeft(y);
        waitUntilAllElementsAreVisible(articleTitlesList,15);
        return this;
    }

    public MyCurrentListHelper closeReadingList() {
        closeOption.click();
        return this;
    }
}