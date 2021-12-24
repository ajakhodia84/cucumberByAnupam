package steps.generic.keywords;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.And;

public class MoveFocusToElement {

	World world;

	public MoveFocusToElement(World world) {
		this.world = world;
	}

	@And("^I focus on element \"([^\"]*)\"$")
	public void focusOnElement(String sObject, WebDriver driver) throws Throwable {

		WebElement object = this.world.getWebElementFactory().getElement(sObject);

		try {
			((JavascriptExecutor) driver)
					.executeScript("arguments[0].scrollIntoView({block: 'center'});", object);
			System.out.println("Focus Changed using JavaScript Executor to element: " + sObject);

		} catch (Exception e) {
			try {
				new Actions(this.world.getWebDriver()).moveToElement(object).click().perform();
				System.out.println("Focus Changed using Action class to element: " + sObject);

			} catch (Exception e1) {
				e.printStackTrace();
				World.softAssertMy.fail("Failed: Not able to focus on given element: " + sObject);
			}
		}

	}

	public void focusOnElement(WebElement object, WebDriver driver) throws Throwable {

		try {
			((JavascriptExecutor) driver)
					.executeScript("arguments[0].scrollIntoView({block: 'center'});", object);
			System.out.println("Focus Changed using JavaScript Executor to element: " + object.getAttribute("class"));

		} catch (Exception e) {
			try {
				new Actions(this.world.getWebDriver()).moveToElement(object).click().perform();
				System.out.println("Focus Changed using Action class to element: " + object.getAttribute("class"));

			} catch (Exception e1) {
				e.printStackTrace();
				World.softAssertMy.fail("Failed: Not able to focus on given element: " + object.getAttribute("class"));
			}
		}

	}

}
