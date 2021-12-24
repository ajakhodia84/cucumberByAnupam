package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import steps.generic.keywords.*;

import java.io.IOException;

public class LoginStepDefinitions {

	World world;

	public LoginStepDefinitions(World world) {
		this.world = world;
	}

	@And("^(?:I|i) check \"([^\"]*)\" of \"([^\"]*)\" is \"([^\"]*)\"$")
	public void checkCssAttribute(String sAttribute, String sObject, String sData) throws Throwable {
		System.out.println("Verifying " + sAttribute + " of : " + sObject);
		new VerifyCSSAttribute(this.world).verifyCSSAttributeContains(sAttribute, sObject, sData);
	}

	@And("^(?:I|i) check the size of \"([^\"]*)\" is \"([^\"]*)\" \"([^\"]*)\" expected$")
	public void verifyComponentDimentions(String sObject, String sWidth, String sHeight) {
		new VerifyCSSAttribute(this.world).verifyComponentDimension(sObject, sWidth, sHeight);
	}

	@Then("^I verify blank input fields Error states$")
	public void verifyErrorStates() throws IOException, InterruptedException {
		System.out.println(
				World.PURPLE + "Verifying blank input fields error states and error icon on Login page" + World.RESET);
		clearUsernameAndPasswordFields();
		//world.getWebElementFactory().getElement("loginpage.username.input").sendKeys("a");
		world.getWebElementFactory().getElement("loginpage.login.button").click();
		Thread.sleep(2000);
		new ElementSpecificFunctions(this.world).verifyElementEnabled("loginpage.error.icon");
		new ElementSpecificFunctions(this.world).verifyElementEnabled("loginpage.blank.password.error.message");

		clearUsernameAndPasswordFields();
		//world.getWebElementFactory().getElement("loginpage.password.input").sendKeys("a");
		world.getWebElementFactory().getElement("loginpage.login.button").click();
		Thread.sleep(2000);
		new ElementSpecificFunctions(this.world).verifyElementEnabled("loginpage.error.icon");
		new ElementSpecificFunctions(this.world).verifyElementEnabled("loginpage.blank.user.error.message");
	}

	@Then("^I verify invalid username or password error states for user \"([^\"]*)\"$")
	public void verifyInvalidUserNameOrPasswordErrorState(String userName) throws IOException, InterruptedException {
		System.out.println(World.PURPLE + "Verifying invalid password error state" + World.RESET);
		clearUsernameAndPasswordFields();
		world.getWebElementFactory().getElement("loginpage.username.input").sendKeys(userName);
		world.getWebElementFactory().getElement("loginpage.password.input").sendKeys("jfloam@wlgore.com.slvtest1.val");
		world.getWebElementFactory().getElement("loginpage.password.input").sendKeys("1253");
		world.getWebElementFactory().getElement("loginpage.login.button").click();
		Thread.sleep(3000);
		if (new ElementSpecificFunctions(this.world).verifyElementEnabled("loginpage.invalid.password.error.message"))
			System.out.println(World.GREEN + "invalid password error state working fine");

		System.out.println(World.PURPLE + "Verifying invalid username error state" + World.RESET);
		clearUsernameAndPasswordFields();
		world.getWebElementFactory().getElement("loginpage.username.input").sendKeys("jfloam@wlgsdsdsore.com.np-1.val");
		world.getWebElementFactory().getElement("loginpage.password.input").sendKeys("1253");
		world.getWebElementFactory().getElement("loginpage.login.button").click();
		Thread.sleep(3000);
		if (new ElementSpecificFunctions(this.world).verifyElementEnabled("loginpage.invalid.username.error.message"))
			System.out.println(World.GREEN + "invaid username error state working fine");
	}

	@Then("^I verify account locked error state for user \"([^\"]*)\"$")
	public void verifyAccountLockedErrorState(String userName) throws IOException, InterruptedException {
		System.out.println(World.PURPLE + "Verifying account locked error state" + World.RESET);
		for (int i = 0; i <= 2; i++) {
			clearUsernameAndPasswordFields();
			world.getWebElementFactory().getElement("loginpage.username.input").sendKeys(userName);
			world.getWebElementFactory().getElement("loginpage.password.input").sendKeys("jfloam@wlgore.com.gldprd2.val");
			world.getWebElementFactory().getElement("loginpage.login.button").click();
			Thread.sleep(2000);
		}

		if (new ElementSpecificFunctions(this.world).verifyElementEnabled("loginpage.account.locked.error.message"))
			System.out.println(World.GREEN + "Account locked error states working fine" + World.RESET);
	}

	@Then("^I verify link \"([^\"]*)\" opens in new tab and has title \"([^\"]*)\"$")
	public void verifyLinksWhichOpensInNewTab(String Plink, String title) {
		System.out.println(World.PURPLE + "Verifying links redirection" + World.RESET);
		world.getWebElementFactory().getElement(Plink).click();
		new Wait(this.world).waitForPageLoad();
		new WindowHandling(this.world).switchToWindowWithTitle(title);
		new Wait(this.world).waitForPageLoad();
	}

	@Then("^I verify user \"([^\"]*)\" with password \"([^\"]*)\" is able to login$")
	public void checkLogin(String userName, String password) throws InterruptedException, IOException {
		System.out.println(World.PURPLE + "Checking user is able to login successfully" + World.RESET);
		clearUsernameAndPasswordFields();
		world.getWebElementFactory().getElement("loginpage.username.input").sendKeys(userName);
		world.getWebElementFactory().getElement("loginpage.password.input").sendKeys(password);
		world.getWebElementFactory().getElement("loginpage.login.button").click();
		new Wait(this.world).waitForPageLoad();
		if (new VerifyTitle(this.world).verifyPageTitle("Home")) {
			System.out.println(World.GREEN + "User loggedIn successfully" + World.RESET);
		} else if (new ElementSpecificFunctions(this.world)
				.verifyElementEnabled("loginpage.invalid.username.error.message")) {
			System.out.println(World.GREEN + "Invalid Username Password combination");
		} else if (new ElementSpecificFunctions(this.world)
				.verifyElementEnabled("loginpage.account.locked.error.message")) {
			System.out.println(World.GREEN + "Account has been locked" + World.RESET);
		}
	}

	@Then("^(?:I|i) expect \"([^\"]*)\" redirects to \"([^\"]*)\"$")
	public void verifyLinksWhichOpensInSameTab(String sObject, String sURL) throws Throwable {
		System.out.println(World.PURPLE + "Verifying links redirection" + World.RESET);
		new VerifyUrl(this.world).verifyUrlAfterClickingElement(sObject, sURL);
	}

	public void clearUsernameAndPasswordFields() {
		System.out.println("Clearing input field...");
		world.getWebElementFactory().getElement("loginpage.username.input").clear();
		world.getWebElementFactory().getElement("loginpage.password.input").clear();
	}

}
