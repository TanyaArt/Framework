package page;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultPage extends AbstractPage {

    @FindBy(xpath = "//h1[@class='title']")
    private WebElement title;

    private final By foundElements = new By.ByXPath("//a[@class='ui-product-box js-quick-shop']");

    @Override
    protected SearchResultPage openPage() {
        return this;
    }

    public SearchResultPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getSearchTitle() {
        waitUntilAjaxCompleted();
        return waitUntilVisibilityOf(title).getText();
    }

    public String getBadSearchTitle() {
        waitUntilAjaxCompleted();
        waitUntilFieldIsChanged(title, "ПОКАЗАТЬ РЕЗУЛЬТАТЫ ДЛЯ:");
        return waitUntilVisibilityOf(title).getText();
    }

    public ProductPage clickOnTheResult(int number){
        waitUntilAjaxCompleted();
        waitForSeconds(5);
        List<WebElement> found = driver.findElements(foundElements);
        waitUntilVisibilityOf(found.get(number-1));
        waitUntilElementIsClickableAndClickAvoidModalWindow(found.get(number-1));

        return new ProductPage(driver);
    }
}
