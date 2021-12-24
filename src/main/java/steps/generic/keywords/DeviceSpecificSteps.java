package steps.generic.keywords;

import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Then;
import framework.configuration.Configuration;
import framework.selenium.support.Print;

public class DeviceSpecificSteps {

	World world;
	ClickOnElementOrLink clickOnElementOrLink = new ClickOnElementOrLink(this.world);

	public DeviceSpecificSteps(World world) {
		this.world = world;
	}

	@Then("I click on {string} if I am using mobile")
	public void clickHamburgermenu(String sObject) throws Throwable {

		String sBrowser = Configuration.getConfig("browser");
		
		if (sBrowser.contains("device")) {
			try {
				WebElement Object;
				Object = this.world.getWebElementFactory().getElement(sObject);
				clickOnElementOrLink.clickElement(Object);
				System.out.println(
						"User is able to click the given element: " + sObject + " since mobile device is used");
				Print.printGreen(
						"User is able to click the given element: " + sObject + " since mobile device is used");

			} catch (Exception e) {
				System.out.println("User is not able to click or find the given element: " + sObject
						+ " even mobile device is used");
				Print.printRed("User is not able to click or find the given element: " + sObject
						+ " even mobile device is used");
			}
		} else {
			System.out.println("User is not using mobile device");
			Print.printGreen("User is not using mobile device");
		}

	}
	
	@Then("^I close the hamburger menu if its open$")
    public void closeHamburgerMenu() throws Throwable {
		String sBrowser = Configuration.getConfig("browser");
		if (sBrowser.contains("device")) {
			try {
       
		clickOnElementOrLink.clickElement("homepage.hamburgerMenu.closeIcon");
		System.out.println(
				"User is able to close the hamburger menu since mobile device is used");
		Print.printGreen(
				"User is able to close the hamburger menu since mobile device is used");

	} catch (Exception e) {
		System.out.println("User is not able to close the hamburger menu since mobile device is used");
		Print.printRed("User is not able to close the hamburger menu since mobile device is used");
	}
} else {
	System.out.println("User is not using mobile device");
	Print.printGreen("User is not using mobile device");
}
    }
}
