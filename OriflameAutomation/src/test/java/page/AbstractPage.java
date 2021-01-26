package page;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class AbstractPage
{
	protected WebDriver driver;

	protected abstract AbstractPage openPage();
	protected final int WAIT_TIMEOUT_SECONDS = 60;

	protected AbstractPage(WebDriver driver)
	{
		this.driver = driver;
	}

	public void waitUntilAjaxCompleted(){
		new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(jQueryAJAXCompleted());
	}

	protected static ExpectedCondition<Boolean> jQueryAJAXCompleted(){
		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver){
				return (Boolean) ((JavascriptExecutor)
						driver).executeScript("return (window.jQuery != null) && (jQuery.active == 0);");
			}
		};
	}

	public void waitUntilElementIsClickableAndClickAvoidModalWindow(WebElement element){
		try {
			new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
					.until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (ElementClickInterceptedException exception){
			driver.navigate().refresh();
			new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
					.until(ExpectedConditions.elementToBeClickable(element)).click();
		}
	}

	public WebElement waitUntilVisibilityOf(WebElement element){
		return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception exception) { }
	}

	public void waitUntilFieldIsChanged(WebElement element, String startValue){
		new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, startValue)));
	}
}
