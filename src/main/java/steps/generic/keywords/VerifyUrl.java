package steps.generic.keywords;

import cucumber.api.java.en.Then;
import framework.selenium.support.Print;

public class VerifyUrl {

	World world;
	String attribute = "href";

	public VerifyUrl(World world) {
		this.world = world;
	}

	/*
	 * @Then("^(?:I|i) expect \"([^\"]*)\" redirects to \"([^\"]*)\"$") public
	 * void verifyUrlByHrefAttribute(String sObject, String sUrl) { if (new
	 * VerifyAttribute(this.world).verifyAttribute(sUrl, attribute, sObject))
	 * System.out.println("URL is correct for :" + sObject); }
	 */

	@Then("^(?:I|i) click on \"([^\"]*)\" and verify url is \"([^\"]*)\"$")
	public boolean verifyUrlAfterClickingElement(String sObject, String sUrl) throws Throwable {
		String CurrentUrl;
		System.out.println("clicking object :" + sObject);
		new ClickOnElementOrLink(this.world).clickElement(sObject);
		new Thread().sleep(3000);
		// new Wait(this.world).waitForPageLoad();
		// new Wait(this.world).waitForPageReady(15);
		CurrentUrl = getCurrentPageUrl();
		this.world.getWebDriver().navigate().back();
		new Thread().sleep(3000);
		// new Wait(this.world).waitForPageReady(15);
		if (CurrentUrl.equalsIgnoreCase(sUrl)) {
			return true;
		} else {
			world.softAssertMy.assertTrue(false, "Mis-Matched : Expected - " + sUrl + " and Actual - " + CurrentUrl);
			Print.printRed("URL is incorrect for :" + sObject);
			return false;
		}
	}

	public String getCurrentPageUrl() {
		return this.world.getWebDriver().getCurrentUrl();

	}

}
