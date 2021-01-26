package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage
{
	private final String BASE_URL = "https://by.oriflame.com/";

	@FindBy(xpath = "//span[@class='n-header-item-icon v-icon-search n-header-item-icon-search']")
	private WebElement searchButton;

	@FindBy(id = "sbc-search__box")
	private WebElement searchField;

	@FindBy(xpath = "//span[@class='n-badge-counter-position v-icon-mini-bag ui-badge-counter js-badge-counter']")
	private WebElement toCartButton;

	public MainPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public MainPage openPage()
	{
		driver.navigate().to(BASE_URL);
		waitUntilAjaxCompleted();
		return this;
	}

	public SearchResultPage search(String request) {
		waitUntilElementIsClickableAndClickAvoidModalWindow(searchButton);
		waitUntilVisibilityOf(searchField).sendKeys(request);

		waitForSeconds(3);

		searchField.sendKeys(Keys.ENTER);

		waitUntilAjaxCompleted();

		return new SearchResultPage(driver);
	}

	public CartPage toCart() {
		waitUntilAjaxCompleted();
		waitUntilElementIsClickableAndClickAvoidModalWindow(toCartButton);
		return new CartPage(driver);
	}
}
