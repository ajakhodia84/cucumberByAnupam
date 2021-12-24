package steps.generic.keywords;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.IOException;

public class ElementSpecificFunctions {
	World world;

	public ElementSpecificFunctions(World world) {
		this.world = world;
	}

	@And("^(?:I|i) (?:expect to see|expect) \"([^\"]*)\" as (?:enabled|present)$")
	public boolean verifyElementEnabled(String sObject) throws IOException {
		WebElement webElement = null;
		webElement = this.world.getWebElementFactory().getElement(sObject, false);
		if (webElement == null) {
			World.softAssertMy.assertTrue(false, new Throwable().getStackTrace()[0].getLineNumber() + this.world.RED
					+ sObject + " - Element not present" + world.RESET);
			return false;
		}
		return true;
	}

	@And("^(?:I|i) (?:expect not to see|donot expect) \"([^\"]*)\" as (?:enabled|present)$")
	public void verifyElementNotEnabled(String sObject) throws IOException {
		try {
			WebElement webElement = null;
			webElement = this.world.getWebElementFactory().getElement(sObject, false);
			if (webElement != null) {
				Assert.fail(sObject + " is present in webpage");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception occurred in verifying object's presence on webpage");
		}
	}

	@And("^(?:I|i) expect to see \"([^\"]*)\" as enabled without highlighting$")
	public void verifyElementEnabledWithoutHighlighting(String sObject) throws IOException {
		try {
			WebElement webElement = null;
			webElement = this.world.getWebElementFactory().getElement(sObject, false);
			if (webElement == null) {
				Assert.fail(sObject + " is not present in webpage");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception occurred in verifying object's presence on webpage");
		}
	}

	@And("^(?:I|i) expect to see \"([^\"]*)\" as enabled with highlighting$")
	public void verifyElementEnabledWithHighlighting(String sObject) throws IOException {
		try {
			WebElement webElement = null;
			webElement = this.world.getWebElementFactory().getElement(sObject, true);
			new Actions(this.world.getWebDriver()).moveToElement(webElement).perform();
			if (webElement == null) {
				Assert.fail(sObject + " is not present in webpage");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception occurred in verifying object's presence on webpage");
		}
	}

	@And("^(?:I|i) expect to see \"([^\"]*)\" as disabled$")
	public void verifyElementDisabled(String sObject) throws IOException {
		try {
			WebElement Object = null;
			Object = this.world.getWebElementFactory().getElement(sObject);
			new Actions(this.world.getWebDriver()).moveToElement(Object).perform();
			Assert.assertNotNull(Object.getAttribute("disabled"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception occurred in verifyElementDisabled method for :: " + sObject);
		}
	}

	@And("^i expect to see \"([^\"]*)\" checkbox as enabled$")
	public void iExpectToSeeCheckboxAsEnabled(String sObject) throws Throwable {
		try {
			WebElement Object = null;
			Object = this.world.getWebElementFactory().getElement(sObject);
			new Actions(this.world.getWebDriver()).moveToElement(Object).perform();
			boolean selected = Object.isSelected();
			System.out.println(selected);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception occurred in verifyElementDisabled method for :: " + sObject);
		}
	}

	@Then("^I expect \"([^\"]*)\" is \"([^\"]*)\"$")
	public void verifyIfElementIsEnabledOrDisabled(String sObject, String isEnabledOrDisabled) throws Throwable {

		try {
			WebElement Object = null;
			Object = this.world.getWebElementFactory().getElement(sObject);
			new Actions(this.world.getWebDriver()).moveToElement(Object).perform();
			boolean isDisabled = Object.getAttribute("class").contains("disabled");
			if (isEnabledOrDisabled.equalsIgnoreCase("disabled")) {
				World.softAssertMy.assertTrue(isDisabled,
						"Given element: " + sObject + " is not displayed as disabled as expected");
			} else if (isEnabledOrDisabled.equalsIgnoreCase("enabled")) {
				World.softAssertMy.assertTrue(!isDisabled,
						"Given element: " + sObject + " is not displayed as enabled as expected");
			}
			System.out.println("The class for object " + sObject + " is displayed as " + Object.getAttribute("class"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception occurred in verifyIfElementIsEnabledOrDisabled method for :: " + sObject);
		}

	}

	@And("^I expect element \"([^\"]*)\" is displayed on page$")
	public boolean elementPresent(String sObject) throws Throwable {

		boolean present;
		try {
			WebElement Object = this.world.getWebElementFactory().getElement(sObject);
			if (Object.isDisplayed()) {
				present = true;
				System.out.println("Step 1");
			} else {
				present = false;
				System.out.println("Step 2");
			}

		} catch (Exception e) {
			System.out.println(e);
			present = false;
			System.out.println("Step 3");
		}

		return present;

	}
}
