package steps.generic.keywords;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import framework.configuration.Configuration;

import org.apache.poi.util.SystemOutLogger;
import org.testng.Assert;


public class VerifyTitle {

    World world;
    public VerifyTitle(World world) {
        this.world = world;
    }

	@And("^(?:I|i) expect page title as \"([^\"]*)\"$")
	public boolean verifyPageTitle(String sData) {
		String sBrowser = Configuration.getConfig("browser");
		if(!sBrowser.contains("device")){
		try {
			String title = this.world.getWebDriver().getTitle().trim();
			if (sData.contains("|")) {
				String a = sData.substring(0, sData.indexOf("|")).trim();
				String b = sData.substring(sData.indexOf("|") + 1, sData.length()).trim();
				if (a.equals(title) || b.equals(title)) {
					return true;
				}
			} else if (title.contentEquals(sData.trim()))
				return true;
			else {
				Assert.assertTrue(false, "Actual title is : '" + title + "' but expected title is : '" + sData + "'");
			}
		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail(e.getMessage());
			return false;
		}
		}else{

			System.out.print("This is mobile. Title cannot be matched");
			Assert.assertTrue(true, "This is mobile. Title cannot be matched");

		}
		return true;

	}

	@And("^(?:I|i) expect page title contains \"([^\"]*)\"$")
	public void verifyTitleContains(String sData) {
		try {
			sData = sData.trim().replaceAll("^\"|\"$", "");
			String title = world.getWebDriver().getTitle().trim();
			Assert.assertTrue(title.toLowerCase().contains(sData.toLowerCase().toString()),
					"Actual title :: '" + title + "' doesn't contain Expected title :: '" + sData + "'");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@And("^(?:I|i) expect page title starts with \"([^\"]*)\"$")
	public void verifyTitleStartWith(String sData) {
		String sBrowser = Configuration.getConfig("browser");
		if(!sBrowser.contains("device")){
		try {
			String title = world.getWebDriver().getTitle().trim();
			Assert.assertTrue(title.toLowerCase().startsWith(sData.toLowerCase().toString()),
					"Actual title :: '" + title + "' doesn't starts with string :: '" + sData + "'");

			Assert.assertTrue(world.getWebDriver().getTitle().toLowerCase().startsWith(sData.toLowerCase().toString()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		}else{
			System.out.print("This is mobile. Title cannot be matched");
		}
	}


	@Then("I expect User navigated to {string} Page")
	public void i_expect_User_navigated_to_Page(String sPageTitle) {
		String sBrowser = Configuration.getConfig("browser");
		if(!sBrowser.contains("device")){
			String expectedTitle = sPageTitle;
			String actualTitle = this.world.getWebDriver().getTitle();
			World.softAssertMy.assertTrue(actualTitle.equalsIgnoreCase(expectedTitle),
					this.world.RED + "Expected title: " + sPageTitle
							+ " is displayed as expected on the page. Actual title displayed is: " + actualTitle
							+ World.RESET);
			System.out.println("Expected title: " + sPageTitle
					+ " is displayed as expected on the page. Actual title displayed is: " + actualTitle
					);
		}else{
			System.out.print("This is mobile. Title cannot be matched");
		}

	}
}
