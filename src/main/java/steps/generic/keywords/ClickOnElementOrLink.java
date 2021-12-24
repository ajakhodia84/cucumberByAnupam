package steps.generic.keywords;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import framework.configuration.Configuration;
import framework.selenium.support.Print;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ClickOnElementOrLink {

	World world;
	MoveFocusToElement moveFocusToElement = new MoveFocusToElement(this.world);

	public ClickOnElementOrLink(World world) {

		this.world = world;
	}

	@Given("I click on \"([^\"]*)\"$")
	public void i_click_on(String sObject) throws Throwable {
		clickElement(sObject);
	}

	public void clickElement(String sObject) {

		WebElement Object;
		Object = this.world.getWebElementFactory().getElement(sObject);
		try {
			new Actions(this.world.getWebDriver()).moveToElement(Object).perform();
			Object.click();
			System.out.println("User is able to click the given element using Click Method: " + sObject);
			Print.printGreen("User is able to click the given element using Click Method: " + sObject);

		} catch (Exception e) {
			try {
				new Actions(this.world.getWebDriver()).moveToElement(Object).perform();
				JavascriptExecutor executor = (JavascriptExecutor) this.world.getWebDriver();
				executor.executeScript("arguments[0].click();", Object);
				System.out.println("User is able to click the given element using JavaScript Executor: " + sObject);
				Print.printGreen("User is able to click the given element using JavaScript Executor: " + sObject);

			} catch (Exception e1) {
				System.out.println("User is not able to click or find the given element: " + sObject);
				Print.printRed("User is not able to click or find the given element: " + sObject);

			}

		}
	}

	public void clickElement(WebElement sObject) throws Throwable {
		try {

			sObject.click();
			System.out.println("User is able to click the given element using Click Method: " + sObject);
			Print.printGreen("User is able to click the given element using Click Method: " + sObject);

		} catch (Exception e) {
			try {

				JavascriptExecutor executor = (JavascriptExecutor) this.world.getWebDriver();
				executor.executeScript("arguments[0].click();", sObject);
				System.out.println("User is able to click the given element using JavaScript Executor: " + sObject);
				Print.printGreen("User is able to click the given element using JavaScript Executor: " + sObject);

			} catch (Exception e1) {
				System.out.println("User is not able to click or find the given element: " + sObject);
				Print.printRed("User is not able to click or find the given element: " + sObject);

			}

		}

	}

	@And("^I press Enter Key for \"([^\"]*)\"$")
	public void i_press_enter_key_for_something(String sObject) throws Throwable {

		WebElement InputField;

		String sBrowser = Configuration.getConfig("browser");

		if (sBrowser.contains("device") && sBrowser.contains("phone")) {
			InputField = this.world.getWebElementFactory().getElement(sObject + ".Mobile");

		} else if (sBrowser.contains("device") && sBrowser.contains("pad")) {
			InputField = this.world.getWebElementFactory().getElement(sObject + ".Desktop");
		} else {
			InputField = this.world.getWebElementFactory().getElement(sObject + ".Desktop");
		}

		try {
			InputField.sendKeys(Keys.ENTER);
			System.out.println("User is able to press enter for the given element using Keys.ENTER: " + sObject);
			Print.printGreen("User is able to press enter for the given element using Keys.ENTER: " + sObject);

		} catch (Exception e) {
			try {

				InputField.sendKeys(Keys.RETURN);
				System.out.println("User is able to press enter for the given element using Keys.RETURN: " + sObject);
				Print.printGreen("User is able to press enter for the given element using Keys.RETURN: " + sObject);

			} catch (Exception e1) {
				System.out.println("User is not able to press enter for the given element: " + sObject);
				Print.printRed("User is not able to press enter for the given element: " + sObject);

			}

		}

	}
}
