package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractPage {

    private final By elementsInCartLocator = By.xpath("");

    @FindBy(xpath = "//div[@class='empty-basket-icon']")
    private WebElement emptyTitle;

    public CartPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    protected CartPage openPage() {
        return this;
    }

    public int getCountElementsInCart(){
        waitUntilAjaxCompleted();
        waitForSeconds(3);
        List<WebElement> elements = driver.findElements(By.xpath("//div[@id='basket-products']/div[@class='row row-item']"));
        return elements.size();
    }

    public boolean cartIsEmpty(){
        waitUntilAjaxCompleted();
        waitForSeconds(10);
        return emptyTitle.isDisplayed();
    }
}
