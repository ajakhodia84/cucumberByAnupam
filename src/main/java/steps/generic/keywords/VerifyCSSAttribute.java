package steps.generic.keywords;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import framework.configuration.Configuration;
import framework.selenium.support.Print;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;

import java.io.IOException;

public class VerifyCSSAttribute {
	World world;
	MoveFocusToElement moveFocusToElement = new MoveFocusToElement(this.world);

	public VerifyCSSAttribute(World world) {
		this.world = world;
	}

	@And("^(?:I|i) expect to see text as (.*) for (.*) attribute of \"([^\"]*)\"$")
	public void verifyCSSAttribute(String sData, String sAttribute, String sObject) throws IOException {
		try {
			WebElement Object;
			Object = this.world.getWebElementFactory().getElement(sObject);
			// Assert.assertTrue(Object.getAttribute(sAttribute).contentEquals(sData);
			String actualValue = Object.getAttribute(sAttribute);
			Assert.assertTrue(actualValue.equals(sData),
					"Mistmatch : Expected - " + sData + " and Actual - " + actualValue);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@And("^(?:I|i) expect \"([^\"]*)\" css attribute of \"([^\"]*)\" object is not having string \"([^\"]*)\"$")
	public void verifyCSSAttributeNotContains(String sAttribute, String sObject, String sData) throws IOException {
		WebElement Object = null;
		try {
			Object = this.world.getWebElementFactory().getElement(sObject);
			String actualValue = Object.getCssValue(sAttribute);
			Assert.assertFalse(actualValue.contains(sData),
					"Mistmatch : Expected - " + sData + " and Actual - " + actualValue);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	public boolean verifyCSSAttributeContains(String sAttribute, String sObject, String sData) throws IOException {
		WebElement Object = null;
		try {
			Object = this.world.getWebElementFactory().getElement(sObject);
			String actualValue = Object.getCssValue(sAttribute);
			if (actualValue.contains(sData))
				return true;
			else
				World.softAssertMy.assertTrue(false, World.RED + sObject + " - Mistmatch : Expected - " + sData
						+ " and Actual is - " + actualValue + World.RESET);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		return false;
	}

	public boolean verifyComponentDimension(String sObject, String sWidth, String sHeight) {
		try {
			WebElement Object;
			Object = this.world.getWebElementFactory().getElement(sObject);
			String actualWidth = Object.getCssValue("width");
			String actualHeight = Object.getCssValue("height");
			if (sHeight == null) {
				if (actualWidth.contains(sWidth))
					return true;
			} else if (actualWidth.contains(sWidth) && actualHeight.contains(sHeight))
				return true;
			else
				World.softAssertMy.assertTrue(false,
						World.RED + sObject + " - Mistmatch : Expected width and Height - " + sWidth + "x" + sHeight
								+ ", While Actual is - " + actualWidth + "x" + actualHeight + World.RESET);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		return false;
	}

	public void verifyTextColor(WebElement Object, String sData) {
		try {
			String color = Object.getCssValue("color");
			System.out.println(color);
			String hex = Color.fromString(color).asHex();
			System.out.println("xpected - " + sData + " and Actual - " + hex);
			World.softAssertMy.assertTrue(hex.equals(sData),
					"Mistmatch : Expected - " + sData + " and Actual - " + hex);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^I expect \"([^\"]*)\" is displayed in color \"([^\"]*)\"$")
	public void i_expect_something_is_displayed_in_color_something(String sObject, String color) throws Throwable {

		WebElement Object = this.world.getWebElementFactory().getElement(sObject);
		verifyTextColor(Object, "#da291c");
	}

	// Incomplete function...need property details from dev which are changing
	// on hover state
	public boolean textChangedOnHover(WebElement element, String sColor) {
		try {
			element.isDisplayed();
			Print.printGreen("Passed: element is Displayed.." + element.getText());
			return true;
		} catch (Exception e) {
			Print.printRed("Failed: element is not Displayed.." + e.getCause());
			return false;
		}
	}

	public void verifyHoverState(WebElement Object, String hoverState, String color, WebDriver driver)
			throws Throwable {
		String sBrowser = Configuration.getConfig("browser");
		Actions action = new Actions(driver);

		if (!sBrowser.contains("device")) {

			try {
				if (hoverState.equalsIgnoreCase("color")) {
					moveFocusToElement.focusOnElement(Object, driver);
					action.moveToElement(Object).build().perform();
					verifyTextColor(Object, color);
				} else if ((hoverState.equalsIgnoreCase("underline"))) {
					moveFocusToElement.focusOnElement(Object, driver);
					action.moveToElement(Object).build().perform();
					String underlinecolor = Object.getCssValue("border-bottom");
					System.out.println(underlinecolor);
					String[] str = underlinecolor.split("rgb");

					String hex = Color.fromString("rgb" + str[1]).asHex();
					System.out.println("Expected - " + color + " and Actual - " + str[0] + hex);
					World.softAssertMy.assertTrue((str[0] + hex).equals(color),
							"Mistmatch : Expected - " + color + " and Actual - " + (str[0] + hex));

				}

			} catch (Exception e) {
				Print.printRed("Failed: User is facing issue in verifying hover state " + e);
			}

		} else {
			Print.printGreen("Passed: Hover state cannot be matched for devices");
		}
	}
}
