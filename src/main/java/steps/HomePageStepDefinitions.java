package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import framework.exceptions.ObjectNotFoundInORException;
import framework.selenium.support.Print;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import steps.generic.keywords.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import framework.configuration.Configuration;
import steps.generic.keywords.World;
import steps.generic.keywords.ClickOnElementOrLink;
import steps.generic.keywords.DeviceSpecificSteps;
import steps.generic.keywords.MoveFocusToElement;
import steps.generic.keywords.VerificationHelper;
import steps.generic.keywords.VerifyCSSAttribute;
import steps.generic.keywords.Wait;

public class HomePageStepDefinitions {

	World world;
	Wait wait = new Wait(this.world);
	VerifyCSSAttribute verifyCSSAttribute = new VerifyCSSAttribute(this.world);
	DeviceSpecificSteps deviceSpecificSteps = new DeviceSpecificSteps(this.world);
	ClickOnElementOrLink clickOnElementOrLink = new ClickOnElementOrLink(this.world);
	VerificationHelper verificationHelper = new VerificationHelper(this.world);
	VerifyImage verifyImageDisplayed = new VerifyImage(this.world);
	MoveFocusToElement moveFocusToElement = new MoveFocusToElement(this.world);

	public HomePageStepDefinitions(World world) {
		this.world = world;
	}

	@Then("^I expect total product displayed are equal to total product data provided below ie \"([^\"]*)\"$")
	public void i_expect_total_product_displayed_are_equal_to_total_product_data_provided_below_ie_something(
			String totalProductExpected) throws Throwable {

		int totalFeaturedProduct = getTotalFeaturedProduct("homepage.featuredProduct.productContainer");
		int productnumberInt = Integer.parseInt(totalProductExpected);

		World.softAssertMy.assertTrue(totalFeaturedProduct == productnumberInt,
				this.world.RED
						+ "Failed: Total product calculated is not equal to total product data provided. Expected: "
						+ totalFeaturedProduct + " Actual: " + productnumberInt + World.RESET);
	}

	@Then("^I expect image \"([^\"]*)\" displayed for \"([^\"]*)\"$")
	public void i_expect_image_something_displayed_for_something(String imagedisplayedornot, String productnumber)
			throws Throwable {
		List<WebElement> productImages = this.world.getWebElementFactory()
				.getElements("homepage.featuredProduct.Images");
		int totalFeaturedProduct = getTotalFeaturedProduct("homepage.featuredProduct.productContainer");
		int productnumberInt = Integer.parseInt(productnumber);

		if (imagedisplayedornot.equalsIgnoreCase("is")) {
			boolean imagedisplayed = verifyImageDisplayed.verifyImageDisplayedOrNot(
					"Product Image for Product " + productnumber, productImages.get(productnumberInt - 1));
			World.softAssertMy.assertTrue(imagedisplayed,
					new Throwable().getStackTrace()[0].getLineNumber() + this.world.RED
							+ "Failed: Product Image for Product " + productnumberInt
							+ " is not displayed on given screen which is not expected" + World.RESET);

		} else {
			boolean imagedisplayed = verifyImageDisplayed.verifyImageDisplayedOrNot(
					"Product Image for Product " + productnumberInt, productImages.get(productnumberInt - 1));
			World.softAssertMy.assertTrue(!imagedisplayed,
					new Throwable().getStackTrace()[0].getLineNumber() + this.world.RED
							+ "Failed: Product Image for Product " + productnumberInt
							+ " is displayed on given screen which is not expected" + World.RESET);

		}

	}

	@Then("^I expect Product name displayed for \"([^\"]*)\" is \"([^\"]*)\"$")
	public void i_expect_product_name_displayed_for_something_is_something(String productnumber, String productnameExp)
			throws Throwable {

		List<WebElement> productNameAct = this.world.getWebElementFactory()
				.getElements("homepage.featuredProduct.ProductName");
		int totalFeaturedProduct = getTotalFeaturedProduct("homepage.featuredProduct.productContainer");
		int productnumberInt = Integer.parseInt(productnumber);
		moveFocusToElement.focusOnElement(productNameAct.get(0), this.world.getWebDriver());
		boolean productNameMatches = (productNameAct.get(productnumberInt - 1)).getText()
				.equalsIgnoreCase(productnameExp);

		World.softAssertMy.assertTrue(productNameMatches, new Throwable().getStackTrace()[0].getLineNumber()
				+ this.world.RED + "Failed: Product name for Product " + productnumberInt
				+ " does not matches with expected. Expected: " + (productNameAct.get(productnumberInt - 1)).getText()
				+ " Actual is: " + productnameExp + World.RESET);

	}

	@Then("^I expect clicking featured product \"([^\"]*)\" navigates to Produc Details Page$")
	public void i_expect_clicking_featured_product_something_navigates_to_produc_details_page(String productnumber)
			throws Throwable {

		List<WebElement> featuredProduct = this.world.getWebElementFactory()
				.getElements("homepage.featuredProduct.productContainer");
		moveFocusToElement.focusOnElement(featuredProduct.get(0), this.world.getWebDriver());
		int productnumberInt = Integer.parseInt(productnumber);

		clickOnElementOrLink.clickElement(featuredProduct.get(productnumberInt - 1));
	}

	public int getTotalFeaturedProduct(String sObject) throws Throwable {
		List<WebElement> featuredProduct = this.world.getWebElementFactory().getElements(sObject);
		moveFocusToElement.focusOnElement(featuredProduct.get(0), this.world.getWebDriver());
		int totalFeaProds = featuredProduct.size();
		System.out.print("Total featured product displayed is: " + featuredProduct);
		return totalFeaProds;
	}

	@Then("^I expect \"([^\"]*)\" rows are displayed to add products$")
	public void i_expect_something_rows_are_displayed_to_add_products(String noOfRows) throws Throwable {

		List<WebElement> quantityInputRows = this.world.getWebElementFactory()
				.getElements("homepage.quickOrder.quantity.input");

		List<WebElement> partNumberInputRows = this.world.getWebElementFactory()
				.getElements("homepage.quickOrder.partNumber.input");
		moveFocusToElement.focusOnElement(partNumberInputRows.get(0), this.world.getWebDriver());

		int noOfRowsExpected = Integer.parseInt(noOfRows);

		boolean ifNoOfRowsAreexpected = Integer.compare(noOfRowsExpected, quantityInputRows.size()) == 0
				&& Integer.compare(noOfRowsExpected, partNumberInputRows.size()) == 0;

		// (noOfRowsExpected == quantityInputRows.size()
		// && noOfRowsExpected == partNumberInputRows.size());

		World.softAssertMy.assertTrue(ifNoOfRowsAreexpected,
				"Failed: Expected number of rows are not equat to actual rows. Expected: " + noOfRowsExpected
						+ " .Actual Part number rows: " + partNumberInputRows.size() + " . Actual quantity rows: "
						+ quantityInputRows.size());

	}

	@And("^I enter part number as \"([^\"]*)\" in row \"([^\"]*)\"$")
	public void i_enter_part_number_as_something_in_row_something(String partNumber, String rowNumber)
			throws Throwable {

		try {
			List<WebElement> partNoInputRows = this.world.getWebElementFactory()
					.getElements("homepage.quickOrder.partNumber.input");
			int rowNo = Integer.parseInt(rowNumber);
			moveFocusToElement.focusOnElement(partNoInputRows.get(rowNo - 1), this.world.getWebDriver());
			partNoInputRows.get(rowNo - 1).clear();
			partNoInputRows.get(rowNo - 1).sendKeys(partNumber, Keys.TAB);

			System.out.println("User is able to enter text " + partNumber + " in given row " + rowNumber);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed: Exception occurred in while entering part number " + partNumber + " in row number: "
					+ rowNumber);
		}
	}

	@And("^I enter quantity as \"([^\"]*)\" in row \"([^\"]*)\"$")
	public void i_enter_quantity_as_something_in_row_something(String quantity, String rowNumber) throws Throwable {

		try {
			List<WebElement> quantityInputRows = this.world.getWebElementFactory()
					.getElements("homepage.quickOrder.quantity.input");
			int rowNo = Integer.parseInt(rowNumber);
			moveFocusToElement.focusOnElement(quantityInputRows.get(rowNo - 1), this.world.getWebDriver());
			quantityInputRows.get(rowNo - 1).clear();
			quantityInputRows.get(rowNo - 1).sendKeys(quantity, Keys.TAB);
			System.out.println("User is able to enter text " + quantity + " in given row " + rowNumber);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Failed: Exception occurred in while entering part number " + quantity + " in row number: "
					+ rowNumber);
		}
	}

	@Then("^I expect error text \"([^\"]*)\" is displayed for \"([^\"]*)\" field in row \"([^\"]*)\"$")
	public void i_expect_error_text_something_is_displayed_for_something_field_in_row_something(
			String errorTextExpected, String errorField, String rowNumber) throws Throwable {
		String sBrowser = Configuration.getConfig("browser");

		try {

			List<WebElement> quantityInputRows = this.world.getWebElementFactory()
					.getElements("homepage.quickOrder.errorText");

			moveFocusToElement.focusOnElement(quantityInputRows.get(0), this.world.getWebDriver());
			int rowNo = Integer.parseInt(rowNumber);
			String errorTextActual;
			if (errorField.equalsIgnoreCase("quantity")) {

				errorTextActual = verificationHelper.getText(quantityInputRows.get(rowNo * 2 - 1));
				World.softAssertMy.assertTrue(errorTextActual.equalsIgnoreCase(errorTextExpected),
						"Failed: Mismatch between Expected error text and actual Error text. Expected: "
								+ errorTextExpected + " Actual: " + errorTextActual);

			} else {

				errorTextActual = verificationHelper.getText(quantityInputRows.get(rowNo * 2 - 2));
				World.softAssertMy.assertTrue(errorTextActual.equalsIgnoreCase(errorTextExpected),
						"Failed: Mismatch between Expected error text and actual Error text. Expected: "
								+ errorTextExpected + " .Actual: " + errorTextActual);
			}

		} catch (Exception e) {
			e.printStackTrace();
			World.softAssertMy.fail("Failed: Exception occurred in while verifying error messages for " + errorField
					+ " for row number: " + rowNumber);
		}
	}

	@And("^I expect error icon is displayed for both fields in row \"([^\"]*)\"$")
	public void i_expect_error_icon_is_displayed_for_both_fields_in_row_something(String rowNumber) throws Throwable {

		try {
			List<WebElement> quantityInputRows = this.world.getWebElementFactory()
					.getElements("homepage.quickOrder.errorIcon");
			int rowNo = Integer.parseInt(rowNumber);
			moveFocusToElement.focusOnElement(quantityInputRows.get(rowNo * 2 - 2), this.world.getWebDriver());

			boolean errorIconDisplayed = verificationHelper.isDisplayed(quantityInputRows.get(rowNo * 2 - 2))
					&& verificationHelper.isDisplayed(quantityInputRows.get(rowNo * 2 - 1));
			World.softAssertMy.assertTrue(errorIconDisplayed,
					"Failed: Error icons are not displayed for row number: " + rowNumber);

		} catch (Exception e) {
			e.printStackTrace();
			World.softAssertMy.fail(
					"Failed: Exception occurred in while verifying error icon displayed for row number: " + rowNumber);
		}

	}

	@Then("^I expect round of message is displayed for \"([^\"]*)\"$")
	public void i_expect_round_of_message_is_displayed_for_something(String sListOfSKU) throws Throwable {

		String[] SKUs = sListOfSKU.split("&");
		// WebElement
		// roundOffError=this.world.getWebElementFactory().getElement("");
		int totalSKUsExp = SKUs.length;
		for (int i = 0; i < totalSKUsExp; i++) {
			System.out.println(SKUs[i]);
		}

	}

	@Then("^I expect no error text displayed in quick order$")
	public void i_expect_no_error_text_displayed_in_quick_order() throws Throwable {

		try {
			List<WebElement> errorIcons = this.world.getWebElementFactory()
					.getElements("homepage.quickOrder.errorIcon");
			List<WebElement> errorMsg = this.world.getWebElementFactory().getElements("homepage.quickOrder.errorText");
			boolean errorNotdisplayed = errorIcons.size() == 0 && errorMsg.size() == 0;

			World.softAssertMy.assertTrue(errorNotdisplayed,
					"Failed: Products are not successfully added to cart. Error messages are displayed");
		} catch (Exception e) {
			e.printStackTrace();
			World.softAssertMy.fail(
					"Failed: Not able to verify if products are successfully added or error message are displayed");
		}
	}

	@And("^(?:I|i) expect \"([^\"]*)\" is a mailto link \"([^\"]*)\"$")
	public void checkLinkIsMailToLink(String sObject, String sValue) throws IOException {
		if (new VerifyAttribute(this.world).verifyAttributeContains("href", sObject, sValue))
			Print.printGreen(sObject + " is a mailto link");
	}

	@And("^(?:I|i) expect \"([^\"]*)\" is a Tel link \"([^\"]*)\"$")
	public void checkLinkIsTelLink(String sObject, String sValue) throws IOException {
		if (new VerifyAttribute(this.world).verifyAttributeContains("href", sObject, sValue))
			Print.printGreen(sObject + " is a Tel link");
	}

	@And("^(?:I|i) proceed to test \"([^\"]*)\"$")
	public void Message(String TestName) {
		Print.printPurple("Verifying " + TestName);
	}

	@And("^(?:I|i) expect order history table is sorted by order date \"([^\"]*)\"$")
	public void checkOrdersSortedByDateDescending(String sObject) throws ParseException {
		Print.printPurple("Testing sorting of order history table");
		if (!new SortingAndComparing(this.world).sortBydateDescending(sObject))
			Print.printGreen("Order history table is sorted by order date properly");
	}

	@And("^(?:I|i) verify for blank values in order history table$")
	public void checkBlankValueInOrderHistoryTable() throws ObjectNotFoundInORException {
		Print.printPurple("Checking for blank values in order history table");
		WebElement row;
		int i = 0;
		boolean fail = false;
		int count;
		count = this.world.getWebElementFactory()
				.getElementCount(this.world.getWebElementFactory().getByLocator("homepage.orderHistory.row"));
		for (i = 0; i < count; i++) {
			for (int j = 1; j < 6; j++) {
				String value;
				value = this.world.getWebDriver()
						.findElement(By.xpath("//div[@class='order-history-data-wrapper']/div/span[" + j + "]"))
						.getText();
				if (value == null) {

					World.softAssertMy.assertTrue(false, "Found blank value in row number : " + i);
					fail = true;
					break;
				}
			}
			if (fail == true) {
				Print.printRed("found blank value in row number : " + i);
				break;
			}
		}
		if (fail == false)
			Print.printGreen("Not found any blank value in order history table");
	}

}
