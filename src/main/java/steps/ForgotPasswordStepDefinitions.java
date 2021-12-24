package steps;

import cucumber.api.java.en.Then;
import framework.selenium.support.Print;

import org.openqa.selenium.WebElement;
import steps.generic.keywords.World;

public class ForgotPasswordStepDefinitions {

	World world;

	public ForgotPasswordStepDefinitions(World world) {
		this.world = world;
	}

	@Then("I verify \"([^\"]*)\" Error states text is displayed as \"([^\"]*)\" for \"([^\"]*)\"")
	public void i_verify_Error_states_text_is_displayed_as_for(String errorType, String expectedErrorText,
			String sObject) {

		WebElement Object = null;
		String actualErrorText;

		try {
			Object = this.world.getWebElementFactory().getElement(sObject);
			actualErrorText = Object.getText();
			World.softAssertMy.assertTrue(actualErrorText.equalsIgnoreCase(expectedErrorText),
					this.world.RED + errorType + " is not displayed as expected on the screen. Expected Text: "
							+ expectedErrorText + ". Actual Text: " + actualErrorText + World.RESET);
			if (actualErrorText.equalsIgnoreCase(expectedErrorText)){
				Print.printGreen(errorType + " is displayed as expected on the screen. Expected Text: "
							+ expectedErrorText + ". Actual Text: " + actualErrorText);
				System.out.println(errorType + " is displayed as expected on the screen. Expected Text: "
						+ expectedErrorText + ". Actual Text: " + actualErrorText);
			}else{
				Print.printRed(errorType + " is not displayed as expected on the screen. Expected Text: "
							+ expectedErrorText + ". Actual Text: " + actualErrorText);
				System.out.println(errorType + " is not displayed as expected on the screen. Expected Text: "
						+ expectedErrorText + ". Actual Text: " + actualErrorText);
			}
			
			/*
			 * if (actualErrorText.equalsIgnoreCase(expectedErrorText)) {
			 * World.softAssertMy.assertTrue(true, this.world.GREEN + errorType
			 * + " is displayed as expected on the screen. Expected Text: "
			 * +expectedErrorText+". Actual Text: "+actualErrorText +
			 * World.RESET); Print.printGreen(errorType +
			 * " is displayed as expected on the screen. Expected Text: "
			 * +expectedErrorText+". Actual Text: "+actualErrorText); } else {
			 * World.softAssertMy.assertTrue(false, new
			 * Throwable().getStackTrace()[0].getLineNumber() + this.world.RED +
			 * errorType +
			 * " is not displayed as expected on the screen. Expected Text: "
			 * +expectedErrorText+". Actual Text: "+actualErrorText +
			 * World.RESET); Print.printRed(errorType +
			 * " is not displayed as expected on the screen. Expected Text: "
			 * +expectedErrorText+". Actual Text: "+actualErrorText); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
