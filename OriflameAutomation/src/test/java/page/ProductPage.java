package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends AbstractPage {

    @FindBy(id = "addToBasketButton")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[@class='n-badge-counter-position v-icon-mini-bag ui-badge-counter js-badge-counter']")
    private WebElement toCartButton;

    public ProductPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    protected ProductPage openPage(){
        return this;
    }

    public ProductPage addToCart() {
        waitUntilAjaxCompleted();
        waitUntilElementIsClickableAndClickAvoidModalWindow(addToCartButton);
        waitUntilVisibilityOf(toCartButton).click();

        return this;
    }

    public CartPage toCart() {
        waitUntilAjaxCompleted();
        waitUntilElementIsClickableAndClickAvoidModalWindow(toCartButton);
        return new CartPage(driver);
    }
}
