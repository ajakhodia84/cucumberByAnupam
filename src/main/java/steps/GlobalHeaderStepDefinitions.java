package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.configuration.Configuration;
import framework.selenium.support.Print;
import steps.generic.keywords.World;
import steps.generic.keywords.ClickOnElementOrLink;
import steps.generic.keywords.DeviceSpecificSteps;
import steps.generic.keywords.VerificationHelper;
import steps.generic.keywords.VerifyCSSAttribute;
import steps.generic.keywords.Wait;

public class GlobalHeaderStepDefinitions {

	World world;
	List<WebElement> ProductCategorys = new ArrayList<WebElement>();
	List<WebElement> SubProductCategorys = new ArrayList<WebElement>();
	Wait wait = new Wait(this.world);
	VerifyCSSAttribute verifyCSSAttribute = new VerifyCSSAttribute(this.world);
	DeviceSpecificSteps deviceSpecificSteps = new DeviceSpecificSteps(this.world);
	ClickOnElementOrLink clickOnElementOrLink = new ClickOnElementOrLink(this.world);
	VerificationHelper verificationHelper = new VerificationHelper(this.world);

	public GlobalHeaderStepDefinitions(World world) {
		this.world = world;
	}

	@Then("I click on {string} product category in global header to navigate to respective PLP Page")
	public void i_click_on_product_category_in_global_header_to_navigate_to_PLP_Page(String sProductCategory) throws Throwable {

		clickProductCategory(sProductCategory);
	}

	@Then("I expect all product categories displayed in Global Header")
	public void i_expect_all_product_categories_displayed_in_Global_Header() {
		try {

			String sBrowser = Configuration.getConfig("browser");

			if (sBrowser.contains("device")) {
				ProductCategorys = this.world.getWebElementFactory()
						.getElements("homepage.header.productCategory.list.mobile");

			} else {

				ProductCategorys = this.world.getWebElementFactory()
						.getElements("homepage.header.productCategory.list");
			}

			List<String> expectedProductCategories = expectedProductCategories();

			List<String> actualProductCategories = getActualProductCategoriesdisplayed(ProductCategorys);

			World.softAssertMy.assertTrue(actualProductCategories.containsAll(expectedProductCategories),
					this.world.RED + " All expected product categories are not displayed " + this.world.RESET);

		} catch (Exception e) {
			e.printStackTrace();
			Print.printRed(this.world.RED + "Test not completed for verification of Product Categoreis Displayed"
					+ World.RESET);
		}

	}

	@Then("I verify clicking product category link navigates to respective PLP page")
	public void i_verify_clicking_product_category_link_navigates_to_respective_PLP_page() throws Throwable {

		List<String> expectedProductCategories = expectedProductCategories();

		for (String option : expectedProductCategories) {
			clickProductCategory(option);
			Print.printGreen(this.world.GREEN + "User is able to click the given option: " + option + World.RESET);
			System.out.println("User is able to click the given option: " + option);
			String sBrowser = Configuration.getConfig("browser");
			if (sBrowser.contains("device")) {
				WebElement hambergerMenu = this.world.getWebElementFactory().getElement("homepage.header.hamburgerMenu");
				wait.waitForElement(hambergerMenu, 50, this.world.getWebDriver());
				deviceSpecificSteps.clickHamburgermenu("homepage.header.hamburgerMenu");
			} else {
				System.out.println("User is not using mobile device");
				Print.printGreen("User is not using mobile device");
			}
			
			
		}

	}

	public List<String> expectedProductCategories() {
		List<String> expectedProductCategoryOption = new ArrayList<String>();
		expectedProductCategoryOption.add("Joint Sealants");
		expectedProductCategoryOption.add("Cut Gaskets");
		expectedProductCategoryOption.add("Sheet Gasketing");
		expectedProductCategoryOption.add("Gasket Tape");
		expectedProductCategoryOption.stream().forEach(s -> System.out.println(s));
		return expectedProductCategoryOption;
	}

	public List<String> getActualProductCategoriesdisplayed(List<WebElement> sProductCategoryWebElementList) {
		List<String> actualProductCategoryOptions = sProductCategoryWebElementList.stream().map(s -> s.getText())
				.collect(Collectors.toList());
		actualProductCategoryOptions.stream().forEach(s -> System.out.println(s));
		return actualProductCategoryOptions;
	}

	@Then("I expect text displayed for Account menu option is {string} and User's First name {string}")
	public void i_expect_text_displayed_for_Account_menu_option_is_and_User_s_First_name(String sHello,
			String sFirstName) {

		String sBrowser = Configuration.getConfig("browser");
		WebElement accountName;

		if (sBrowser.contains("device")) {

			accountName = this.world.getWebElementFactory().getElement("homepage.header.accountsMenu.mobile");

		} else {

			accountName = this.world.getWebElementFactory().getElement("homepage.header.accountsMenu");
		}

		try {
			boolean textDisplayed = accountName.getText().contains(sHello)
					&& accountName.getText().contains(sFirstName);
			World.softAssertMy.assertTrue(textDisplayed,
					this.world.RED + "Failed: Account label is not displayed as expected. Expected is: " + sHello + " "
							+ sFirstName + ". But Actual text displayed is: " + accountName.getText() + " ."
							+ this.world.RESET);

		} catch (Exception e) {
			e.printStackTrace();
			Print.printRed(this.world.RED
					+ "Failed: Test not completed for verification of Product Categoreis Displayed" + World.RESET);
		}

	}

	@Then("^I click on Account menu option$")
	public void i_click_on_account_menu_option() throws Throwable {

		String sBrowser = Configuration.getConfig("browser");
		WebElement accountName;

		if (sBrowser.contains("device")) {

			accountName = this.world.getWebElementFactory().getElement("homepage.header.accountsMenu.mobile");
			clickOnElementOrLink.clickElement(accountName);

		} else {

			accountName = this.world.getWebElementFactory().getElement("homepage.header.accountsMenu");
			clickOnElementOrLink.clickElement(accountName);
		}

	}

	@Then("I expect all Account menu options displayed in Accout Menu dropdown")
	public void i_expect_all_Account_menu_options_displayed_in_Accout_Menu_dropdown() {

		try {

			List<String> expectedAccountOption = expectedAccountOptions();

			List<String> actualAccountOptions = getActualAccountOptions();

			World.softAssertMy.assertTrue(expectedAccountOption.equals(actualAccountOptions),
					this.world.RED + "Failed: All expected Account options are not displayed " + this.world.RESET);

		} catch (Exception e) {
			e.printStackTrace();
			Print.printRed(
					this.world.RED + "Test not completed for verification of Account Options Displayed" + World.RESET);
		}
	}

	public List<String> expectedAccountOptions() {
		List<String> expectedAccountOptions = null;
		expectedAccountOptions.add("Order History");
		expectedAccountOptions.add("Change Password");
		expectedAccountOptions.add("View Profile");
		expectedAccountOptions.add("View Addresses");
		expectedAccountOptions.add("Sign Out");
		return expectedAccountOptions;
	}

	public List<String> getActualAccountOptions() {
		List<WebElement> Object = this.world.getWebElementFactory().getElements("homepage.header.accountsMenu.options");

		List<String> actualAccountOptions = Object.stream().map(s -> s.getText()).collect(Collectors.toList());
		return actualAccountOptions;
	}

	@Then("I click on {string} from Account Options list")
	public void i_click_on_from_Account_Options_list(String sAccountOption) throws Throwable {
		clickAccountOption(sAccountOption);
	}

	public void clickAccountOption(String sAccountOption) throws Throwable {

		try {

			List<WebElement> AccountOptionList = this.world.getWebElementFactory()
					.getElements("homepage.header.accountsMenu.options");

			for (WebElement option : AccountOptionList) {

				if (option.getText().equalsIgnoreCase(sAccountOption)) {
					clickOnElementOrLink.clickElement(option);
					Print.printRed(this.world.GREEN + "Given account option has been clicked: " + sAccountOption
							+ World.RESET);
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Print.printRed(this.world.RED + "Not able to Click on expected Account Option" + World.RESET);
		}
	}

	public void clickProductCategory(String sProductCategory) throws Throwable {

		try {
			String sBrowser = Configuration.getConfig("browser");

			if (sBrowser.contains("device")) {
				ProductCategorys = this.world.getWebElementFactory()
						.getElements("homepage.header.productCategory.list.mobile");

			} else {

				ProductCategorys = this.world.getWebElementFactory()
						.getElements("homepage.header.productCategory.list");
			}
			for (WebElement obj : ProductCategorys) {

				if (obj.getText().equalsIgnoreCase(sProductCategory)) {
					verifyCSSAttribute.verifyHoverState(obj, "underline", "1.5px solid #da291c", this.world.getWebDriver());
					clickOnElementOrLink.clickElement(obj);
					wait.waitForPageLoad();
					//verifyCSSAttribute.verifyTextColor(obj, "#da291c");
					break;
				} else if (obj.getText().equalsIgnoreCase("More")) {
					clickOnElementOrLink.clickElement(obj);
					SubProductCategorys = this.world.getWebElementFactory()
							.getElements("homepage.header.productCategory.list.moreoptions");
					for (WebElement obj2 : SubProductCategorys) {
						if (obj2.getText().equalsIgnoreCase(sProductCategory)) {
							clickOnElementOrLink.clickElement(obj2);
							break;
						}
					}
				}
			}

/*			WebElement pageHeader = this.world.getWebElementFactory().getElement("plp.pageHeader");
			wait.waitForElement(pageHeader, 50, this.world.getWebDriver());
			String actualCategoryDisplayed = pageHeader.getText();
			boolean pageNavigated = actualCategoryDisplayed.equalsIgnoreCase(sProductCategory);

			World.softAssertMy.assertTrue(pageNavigated,
					this.world.RED + "Failed: Click is not working properly for Product Categories. Expected: "
							+ sProductCategory + " But Actual is: " + actualCategoryDisplayed + this.world.RESET);*/

		} catch (Exception e) {
			e.printStackTrace();
			Print.printRed(this.world.RED + "Failed: Not able to Click on expected product category" + World.RESET);
		}
	}

	@Then("^I expect Cart icon is displayed$")
	public void i_expect_cart_icon_is_displayed() throws Throwable {

		try {
			WebElement cartIcon = this.world.getWebElementFactory().getElement("homepage.header.cartIcon");
			World.softAssertMy.assertTrue(verificationHelper.isDisplayed(cartIcon),
					this.world.RED + "Failed: Cart Icon is not displayed as expected" + this.world.RESET);

		} catch (Exception e) {
			Print.printRed("Exception Displayed is: " + e);
		}

	}

	@Then("^I expect Cart que icon is not displayed when cart is empty$")
	public void i_expect_cart_que_icon_is_not_displayed_when_cart_is_empty() throws Throwable {

		try {
			WebElement cartQueIcon = this.world.getWebElementFactory().getElement("homepage.header.cartQueIcon");
			World.softAssertMy.assertTrue(verificationHelper.isNotDisplayed(cartQueIcon),
					this.world.RED + "Failed: Cart Que Icon is displayed even for empty cart which is not expected"
							+ this.world.RESET);

		} catch (Exception e) {
			Print.printRed("Exception Displayed is: " + e);
		}

	}

	@Then("^I expect Cart que icon is displayed when cart is not empty$")
	public void i_expect_cart_que_icon_is_displayed_when_cart_is_not_empty() throws Throwable {

		try {
			WebElement cartQueIcon = this.world.getWebElementFactory().getElement("homepage.header.cartQueIcon");
			World.softAssertMy.assertTrue(verificationHelper.isDisplayed(cartQueIcon),
					this.world.RED
							+ "Failed: Cart Que Icon is not displayed even for non-empty cart which is not expected"
							+ this.world.RESET);

		} catch (Exception e) {
			Print.printRed("Exception Displayed is: " + e);
		}

	}

	@Then("^I expect Cart label is displayed only for desktop$")
	public void i_expect_cart_label_is_displayed_only_for_desktop() throws Throwable {

		String sBrowser = Configuration.getConfig("browser");

		if (sBrowser.contains("device") && sBrowser.contains("phone")) {
			Print.printGreen("This is mobile or tablet so Cart Label will not be displayed");

		} else {
			WebElement cartLabel = this.world.getWebElementFactory().getElement("homepage.header.cartLabel");
			boolean condition = verificationHelper.isDisplayed(cartLabel)
					&& cartLabel.getText().equalsIgnoreCase("Cart");
			World.softAssertMy.assertTrue(condition, this.world.RED
					+ "Failed: Cart Label is not displayed even for Desktop which is not expected" + this.world.RESET);

		}

	}

	@Then("^I expect Cart value text is not displayed when cart is empty but only applicable for desktop$")
	public void i_expect_cart_value_text_is_not_displayed_when_cart_is_empty_but_only_applicable_for_desktop()
			throws Throwable {

		String sBrowser = Configuration.getConfig("browser");

		if (sBrowser.contains("device") && sBrowser.contains("phone")) {
			Print.printGreen("This is mobile or tablet so Cart Value text will not be displayed");

		} else {

			WebElement cartText = this.world.getWebElementFactory().getElement("homepage.header.cartValue");
			World.softAssertMy.assertTrue(verificationHelper.isNotDisplayed(cartText),
					this.world.RED + "Failed: Cart Value is displayed even when cart is empty which is not expected"
							+ this.world.RESET);

		}
	}

	@Then("^I expect Cart value text displayed should contain \"([^\"]*)\" as product types but only applicable for desktop$")
	public void i_expect_cart_value_text_displayed_should_contain_something_as_product_types_but_only_applicable_for_desktop(
			String sNoOfProductType) throws Throwable {
		String sBrowser = Configuration.getConfig("browser");

		if (sBrowser.contains("device") && sBrowser.contains("phone")) {
			Print.printGreen("This is mobile so Cart Label will not be displayed");

		} else {
			WebElement cartValue = this.world.getWebElementFactory().getElement("homepage.header.cartValue");
			String cartValueText = cartValue.getText();

			System.out.println(cartValueText);
			// Verify if Cart Value text is displayed or not
			World.softAssertMy.assertTrue(verificationHelper.isDisplayed(cartValue),
					this.world.RED
							+ "Failed: Cart Value is not displayed even when cart is not empty which is not expected"
							+ this.world.RESET);

			// Verify if number of product type is correctly displayed in Cart
			// Value text
			boolean value = cartValueText.equalsIgnoreCase(": " + sNoOfProductType + " Lines");
			World.softAssertMy.assertTrue(value,
					this.world.RED
							+ "Failed: Cart value does not display the number of product types properly. Expected: "
							+ cartValueText + " Actual: : " + sNoOfProductType + " Lines" + this.world.RESET);

		}

	}

	@When("^I enter text \"([^\"]*)\" in search bar, See Results gets enabled$")
	public void i_enter_text_something_in_search_bar_see_results_gets_enabled(String text) throws Throwable {
		try {
			WebElement searchInput, seeResultsCTA, seeResultsLabel;
			String sBrowser = Configuration.getConfig("browser");

			if (sBrowser.contains("device") && sBrowser.contains("phone")) {
				searchInput = this.world.getWebElementFactory().getElement("homepage.header.searchInput.Mobile");
				seeResultsCTA = this.world.getWebElementFactory().getElement("homepage.header.seeResultsArrow.mobile");

				// Verify if See Results is enabled for entering three character
				searchInput.clear();
				searchInput.sendKeys(text);
				Thread.sleep(1500);
				Print.printGreen("User has entered text in search input in mobile. Text: " + text);

				seeResultsCTA = this.world.getWebElementFactory().getElement("homepage.header.seeResultsArrow.mobile");
				World.softAssertMy.assertTrue(verificationHelper.isDisplayed(seeResultsCTA), this.world.RED
						+ "Failed: See Results Arrow icon not displayed in mobile and tablet" + this.world.RESET);

			} else {
				searchInput = this.world.getWebElementFactory().getElement("homepage.header.searchInput.Desktop");

				searchInput.clear();
				searchInput.sendKeys(text);
				Print.printGreen("User has entered text in search input in desktop. Text: " + text);

				seeResultsCTA = this.world.getWebElementFactory().getElement("homepage.header.seeResultsArrow.desktop");
				seeResultsLabel = this.world.getWebElementFactory()
						.getElement("homepage.header.seeResultsLabel.desktop");
				boolean seeResultsDisplayed = verificationHelper.isDisplayed(seeResultsCTA)
						&& verificationHelper.isDisplayed(seeResultsLabel);
				boolean isSeeResultsLabelCorret = seeResultsLabel.getText().equalsIgnoreCase("See Results");

				World.softAssertMy.assertTrue(isSeeResultsLabelCorret,
						this.world.RED + "Failed: See Results Arrow icon not displayed for Desktop" + this.world.RESET);

				World.softAssertMy.assertTrue(seeResultsDisplayed, this.world.RED
						+ "Failed: See Results Label not displayed properly for Desktop" + this.world.RESET);

			}
		} catch (Exception e) {
			Print.printRed("User is facing issue in Search Bar " + e);
			World.softAssertMy.assertTrue(false,
					this.world.RED + "Failed: To verify if See Results CTA gets enabled or not" + this.world.RESET);
		}

	}

	@When("^I click on See Results CTA$")
	public void i_click_on_see_results_cta() throws Throwable {

		try {
			String sBrowser = Configuration.getConfig("browser");
			WebElement seeResultsCTA;
			if (sBrowser.contains("device") && sBrowser.contains("phone")) {
				seeResultsCTA = this.world.getWebElementFactory().getElement("homepage.header.seeResultsArrow.mobile");
				clickOnElementOrLink.clickElement(seeResultsCTA);
				Print.printGreen("User has clicked on See Results CTA in mobile.");
			} else {
				seeResultsCTA = this.world.getWebElementFactory().getElement("homepage.header.seeResultsArrow.desktop");
				clickOnElementOrLink.clickElement(seeResultsCTA);
				Print.printGreen("User has clicked on See Results CTA in desktop.");
			}
		} catch (Exception e) {
			Print.printRed("User is facing issue in clicking See Results CTA" + e);
		}

	}

	@Then("^I expect Search icon is displayed$")
	public void i_expect_search_icon_is_displayed() throws Throwable {

		WebElement searchicon = this.world.getWebElementFactory().getElement("homepage.header.searchicon");

		World.softAssertMy.assertTrue(verificationHelper.isDisplayed(searchicon),
				this.world.RED + " Failed: Search Icon is not displayed as expected" + this.world.RESET);

	}

	@Then("^I expect Search label is displayed only for desktop$")
	public void i_expect_search_label_is_displayed_only_for_desktop() throws Throwable {

		try {
			String sBrowser = Configuration.getConfig("browser");

			WebElement searchLabel = this.world.getWebElementFactory().getElement("homepage.header.searchLabel");

			if (sBrowser.contains("device") && sBrowser.contains("phone")) {
				Print.printRed("Search label should not get displayed in mobiles");
			} else {
				World.softAssertMy.assertTrue(verificationHelper.isDisplayed(searchLabel), this.world.RED
						+ " Failed: Search Label is not displayed as expected even for desktop" + this.world.RESET);

			}
		} catch (Exception e) {
			Print.printRed("Not able to test the display of search label" + e);
		}
	}

	@Then("^I expect Search Bar is maximized$")
	public void i_expect_search_bar_is_maximized() throws Throwable {

		try {
			Thread.sleep(1000);
			WebElement searchWrapper = this.world.getWebElementFactory().getElement("homepage.header.searchWrapper");
			System.out.println("Actual class of Search Wrapper is: " + searchWrapper.getAttribute("class"));
			boolean value = searchWrapper.getAttribute("class").contains("slide-left");
			World.softAssertMy.assertTrue(value,
					this.world.RED
							+ " failed: Search bar is not maximized on clicking search Icon. Please raise an issue. Actual Class displayed is: "
							+ searchWrapper.getAttribute("class") + this.world.RESET);

		} catch (Exception e) {
			Print.printRed("Not able to test if search bar is maxmimized or not " + e);
			World.softAssertMy.assertTrue(false,
					this.world.RED + " Not able to test if search bar is maxmimized or not " + this.world.RESET);
		}
	}

	@Then("^I expect autosuggestions is displayed in 300ms$")
	public void i_expect_autosuggestions_is_displayed_in_300ms() throws Throwable {

		Thread.sleep(3000);
		try {
			String sBrowser = Configuration.getConfig("browser");

			if ((sBrowser.contains("device") && sBrowser.contains("phone"))) {
				List<WebElement> autoSuggestions = this.world.getWebElementFactory()
						.getElements("homepage.header.searchAutoSuggests.Mobile");
				Print.printGreen("Total count of autosuggestions displayed are: " + autoSuggestions.size());
				World.softAssertMy.assertTrue(autoSuggestions.size() > 0, this.world.RED
						+ "Failed: Autosuggests are not displayed in expected time for Desktop" + this.world.RESET);

			} else {
				List<WebElement> autoSuggestions = this.world.getWebElementFactory()
						.getElements("homepage.header.searchAutoSuggests.Desktop");
				Print.printGreen("Total count of autosuggestions displayed are: " + autoSuggestions.size());
				World.softAssertMy.assertTrue(autoSuggestions.size() > 0, this.world.RED
						+ "Failed: Autosuggests are not displayed in expected time for mobile" + this.world.RESET);
			}
		} catch (Exception e) {
			Print.printRed("Not able to test the display of Autosuggest for search" + e);
		}

	}

	@And("^I select \"([^\"]*)\" from Auto-suggestion list$")
	public void i_select_something_from_autosuggestion_list(String sProductName) throws Throwable {

		List<WebElement> autoSuggests;

		try {
			String sBrowser = Configuration.getConfig("browser");
			int value = 0;

			if (sBrowser.contains("device") && sBrowser.contains("phone")) {
				autoSuggests = this.world.getWebElementFactory().getElements("homepage.header.searchAutoSuggests.Mobile");

			} else {

				autoSuggests = this.world.getWebElementFactory()
						.getElements("homepage.header.searchAutoSuggests.Desktop");
			}
			for (WebElement obj : autoSuggests) {

				if (obj.getText().contains(sProductName)) {
					clickOnElementOrLink.clickElement(obj);
					value = 1;
					Print.printRed(this.world.RED + sProductName
							+ " : Options is found in autosuggested list and clicked" + World.RESET);
					wait.waitForPageLoad();
					break;
				}
			}
			if (value == 0) {
				Print.printRed(
						this.world.RED + sProductName + " : Options is not found in autosuggested list" + World.RESET);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Print.printRed(this.world.RED + "Failed: Not able to Click on expected AutoSuggestion" + World.RESET);
		}

	}

	@Then("^I expect autosuggestions are not displayed in 300ms$")
	public void i_expect_autosuggestions_are_not_displayed_in_300ms() throws Throwable {

		Thread.sleep(3000);
		try {
			String sBrowser = Configuration.getConfig("browser");

			if ((sBrowser.contains("device") && sBrowser.contains("phone"))) {
				List<WebElement> autoSuggestions = this.world.getWebElementFactory()
						.getElements("homepage.header.searchAutoSuggests.Mobile");
				Print.printGreen("Total count of autosuggestions displayed are: " + autoSuggestions.size());
				World.softAssertMy.assertTrue(autoSuggestions.size() == 0,
						this.world.RED
								+ "Failed: Autosuggests are displayed in expected time for Desktop even if not expected"
								+ this.world.RESET);

			} else {
				List<WebElement> autoSuggestions = this.world.getWebElementFactory()
						.getElements("homepage.header.searchAutoSuggests.Desktop");
				Print.printGreen("Total count of autosuggestions displayed are: " + autoSuggestions.size());
				World.softAssertMy.assertTrue(autoSuggestions.size() == 0,
						this.world.RED
								+ "Failed: Autosuggests are displayed in expected time for phone even if not expected"
								+ this.world.RESET);
			}
		} catch (Exception e) {
			Print.printRed("Not able to test the display of Autosuggest for search" + e);
		}
	}

	@Then("^I expect Search bar is minimized and closed$")
	public void i_expect_search_bar_is_minimized_and_closed() throws Throwable {

		Thread.sleep(1000);
		try {
			WebElement searchWrapper = this.world.getWebElementFactory().getElement("homepage.header.searchWrapper");
			boolean value = !searchWrapper.getAttribute("class").contains("slide-left");
			System.out.println("Actual class of Search Wrapper is: " + searchWrapper.getAttribute("class"));
			World.softAssertMy.assertTrue(value,
					this.world.RED + "Search Bar is not minimized and closed after user click close icon for search"
							+ this.world.RESET);

		} catch (Exception e) {
			Print.printRed("Not able to test if search bar is minimized or not " + e);
			World.softAssertMy.assertTrue(false,
					this.world.RED + "Not able to test if search bar is minimized or not " + this.world.RESET);
		}

	}

}
