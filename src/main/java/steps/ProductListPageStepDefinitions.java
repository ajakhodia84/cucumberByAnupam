package steps;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import framework.shared.SelectProduct;
import steps.generic.keywords.World;
import steps.generic.keywords.ClickOnElementOrLink;
import steps.generic.keywords.DeviceSpecificSteps;
import steps.generic.keywords.ElementSpecificFunctions;
import steps.generic.keywords.MoveFocusToElement;
import steps.generic.keywords.VerificationHelper;
import steps.generic.keywords.VerifyCSSAttribute;
import steps.generic.keywords.VerifyImage;
import steps.generic.keywords.Wait;

public class ProductListPageStepDefinitions {

	World world;
	Wait wait = new Wait(this.world);
	VerifyCSSAttribute verifyCSSAttribute = new VerifyCSSAttribute(this.world);
	DeviceSpecificSteps deviceSpecificSteps = new DeviceSpecificSteps(this.world);
	ClickOnElementOrLink clickOnElementOrLink = new ClickOnElementOrLink(this.world);
	VerificationHelper verificationHelper = new VerificationHelper(this.world);
	VerifyImage verifyImageDisplayed = new VerifyImage(this.world);
	MoveFocusToElement moveFocusToElement = new MoveFocusToElement(this.world);
	ElementSpecificFunctions elementSpecificFunctions = new ElementSpecificFunctions(this.world);

	public ProductListPageStepDefinitions(World world) {
		this.world = world;
	}

	@Then("^I expect on hover \"([^\"]*)\" changes \"([^\"]*)\" to \"([^\"]*)\"$")
	public void i_expect_on_hover_something_changes_something_to_something(String sObject, String hoverState,
			String color) throws Throwable {
		WebElement Object = this.world.getWebElementFactory().getElement(sObject);

		verifyCSSAttribute.verifyHoverState(Object, hoverState, color, this.world.getWebDriver());

	}

	@And("^I expect number of items mentioned in total items section is correct for \"([^\"]*)\"$")
	public void i_expect_number_of_items_mentioned_in_total_items_section_is_correct_for_something(
			String productCategory) throws Throwable {
		int totalProdInShowResults = getTotalProductinShowResults("plp.filter.totalItems.label");

		if (totalProdInShowResults == 0) {
			WebElement sortFilter = this.world.getWebElementFactory().getElement("plp.filter.sortedBy.pill=xpath");
			boolean ifFilterNotDisplayed = !sortFilter.isDisplayed();
			World.softAssertMy.assertTrue(ifFilterNotDisplayed, "Failed for Product Category-" + productCategory
					+ ": Filter sections is displayed even in case of 0 products mentioned in Show total Results section");
		} else {

			int totalProductInStandardResult = getListOfProductsInPLPStandardResults().size();

			World.softAssertMy.assertTrue(totalProdInShowResults == totalProductInStandardResult,
					"Failed for Product Category-" + productCategory
							+ ": Total products displayed in Show Results section is not equal to total products in Standard Results. Total Products in Show Results: "
							+ totalProdInShowResults + " Total products actually available: "
							+ totalProductInStandardResult);

		}

	}

	private int getTotalProductinShowResults(String sObject) throws Throwable {

		WebElement Object = this.world.getWebElementFactory().getElement(sObject);
		String label = Object.getText();

		label = label.replaceAll("[^\\d]", "").trim();
		int noOfProductExp = Integer.parseInt(label);
		return noOfProductExp;

	}

	public List<String> getListOfProductsInPLPStandardResults() throws Throwable {

		WebElement nextButton = this.world.getWebElementFactory().getElement("plp.pagination.nextButton");

		List<String> productsName = new ArrayList<>();

		if (nextButton.isDisplayed()) {

			do {
				List<WebElement> products = this.world.getWebElementFactory()
						.getElements("plp.standardResults.productname");
				products.stream().forEach(s -> productsName.add(s.getText()));

				if (nextButton.getAttribute("class").contains("disabled")) {
					break;
				}
				nextButton.click();
				Thread.sleep(5000);
			} while (true);

		} else if(!elementSpecificFunctions.elementPresent("plp.pagination.nextButton")){
			List<WebElement> products = this.world.getWebElementFactory()
					.getElements("plp.standardResults.productname");
			products.stream().forEach(s -> productsName.add(s.getText()));
		}else{
			System.out.println("Not able to get the correct number of products displayed in Standard Results");
		}

		productsName.stream().forEach(s -> System.out.println(s));

		return productsName;

	}


    @And("^(?:I|i) open product list page \"([^\"]*)\" from header$")
    public void openProductListPage(String sObject) throws Throwable {
        new ClickOnElementOrLink(this.world).clickElement(sObject);
        new Wait(this.world).waitForPageLoad();
        Thread.sleep(5000);
    }

    /*@And("^(?:I|i) I verify current selected PLP should not be present in More categories$")
    public void verifyCurrentPLPNotPresentInMoreCategories() throws Throwable {
        String currentPlp;

        for (int i=0;i<4 ;i++) {
            List<String> remainCategories = new ArrayList<>();
            this.world.getWebElementFactory().getLocatorValue("homepage.header.productCategory.commonLocator")
            new Click(this.world).click("homepage.header.productCategory.one");
            new Wait(this.world).waitForPageLoad();
            currentPlp = remainCategories.add(this.world.getWebElementFactory().getElement("plp.more.category.title.one").getText());
            remainCategories.add(this.world.getWebElementFactory().getElement("plp.more.category.title.two").getText());
            remainCategories.add(this.world.getWebElementFactory().getElement("plp.more.category.title.three").getText());
            for (String str : remainCategories) {
               // if ()
                    }
                    }
            }
        }*/

    @And("^(?:I|i) verify category title and description for \"([^\"]*)\" product list page$")
    public void verifyCategoryDescriptions(String sheetName) throws IOException {
        String actualProductDescription = null;
        String productName = null;
        productName = this.world.getWebElementFactory().getElement("plp.category.title").getText();
        actualProductDescription = this.world.getWebElementFactory().getElement("plp.category.description").getText();
        System.out.println("Checking description for '" + productName + "' in product list of '" + sheetName + "'");
        String expectedProductDescription = new SelectProduct().getCellData(productName, "Description", sheetName);
        //System.out.println(expectedProductDescription.replace("\n",""));
        //world.softAssertMy.assertTrue(actualProductDescription.replace("\n", "").equals(expectedProductDescription.replace("\n", "")), "Product - '" + productName + "', Expected description '" + expectedProductDescription + "'\n but found '" + actualProductDescription);
        world.softAssertMy.assertTrue((actualProductDescription.replace("\n", "")).equals(expectedProductDescription.replace("\n", "")), "Product - '" + productName + "'\nExpected description - \n'" + expectedProductDescription + "'" + "\n" + "but found - \n'" + actualProductDescription + "'\n\n");
    }

    @And("^(?:I|i) verify products' title and descriptions in \"([^\"]*)\" product list page$")
    public void verifyProductDescription(String sheetName) throws IOException {
        System.out.println();
        List<WebElement> productList = this.world.getWebElementFactory().getElements("plp.productList.item");
        int productCount = productList.size();
        System.out.println("product count is :" + productCount);
        for (int i = 1; i <= productCount; i++) {
            String locator = this.world.getWebElementFactory().getLocatorValue("plp.productList.item") + "[" + i + "]";
            String productName = this.world.getWebDriver().findElement(By.xpath(locator + "//h6/lightning-formatted-rich-text")).getText();
            System.out.println("Checking description for '" + productName + "' in product list of '" + sheetName + "'");
            String actualProductDescription = this.world.getWebDriver().findElement(By.xpath(locator + "//p")).getText().toLowerCase().replace("\n", "").trim();
            String expectedProductDescription = new SelectProduct().getCellData(productName, "Description", sheetName).toLowerCase().replace("\n", "").trim();
            // String str1 = "gore? universal pipe gasket rg. 100% eptfe ring gasket for a range of flange materials. printing on each gasket indicates size and class. class 150; 1/4 in (6.0 mm) thickness. gls i\n" +
            //    "d available from 1/2 through 24 in. nps id (\"old standard\") available from 1/2 through 12 in. sizes 1/2 through 12 in sold in box quantities only.";
            //String str2 = "gore? universal pipe gasket rg. 100% eptfe ring gasket for a range of flange materials. printing on each gasket indicates size and class. class 150; 1/4 in (6.0 mm) thickness. gls i\n" +
            //      "d available from 1/2 through 24 in. nps id (\"old standard\") available from 1/2 through 12 in. sizes 1/2 through 12 in sold in box quantities only.";
            // System.out.println(expectedProductDescription.replace("\n",""));
            // System.out.println("Description for '" + productName + "' is - '" + productDescription + "'");
            // String actualProductDescription2  = Normalizer.normalize(actualProductDescription, Normalizer.Form.NFKC);
            // String expectedProductDescription2 = Normalizer.normalize(expectedProductDescription, Normalizer.Form.NFKC);
            // world.softAssertMy.assertTrue((actualProductDescription.replace("\n", "")).equals(expectedProductDescription.replace("\n", "")), "Product - '" + productName + "'\nExpected description - \n'" + expectedProductDescription + "'but found - \n'" + actualProductDescription + "'\n\n");
            world.softAssertMy.assertTrue(actualProductDescription.equals(expectedProductDescription), "Product - '" + productName + "'\nExpected description - \n'" + expectedProductDescription + "'\nbut found - \n'" + actualProductDescription + "'\n\n");
            System.out.println(actualProductDescription.compareTo(expectedProductDescription));
        }
    }
}
