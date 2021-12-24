package steps.generic.keywords;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import framework.exceptions.ObjectNotFoundInORException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class VerifyAttribute {

    World world;

    public VerifyAttribute(World world) {
        this.world = world;
    }

    public boolean verifyAttribute(String sData, String sAttribute, String sObject) {

        WebElement Object;
        Object = this.world.getWebElementFactory().getElement(sObject);
        String actualValue = Object.getAttribute(sAttribute);
        if (actualValue.equals(sData)) {
            World.softAssertMy.assertTrue(true, "Match : Expected - " + sData + " and Actual - " + actualValue);
            return true;
        } else
            World.softAssertMy.assertTrue(true, "MisMatch : Expected - " + sData + " and Actual - " + actualValue);
        return false;
    }

    public boolean verifyAttributeContains(String sAttribute, String sObject, String sData) throws IOException {
        WebElement Object = null;
        Object = this.world.getWebElementFactory().getElement(sObject);
        String actualValue = Object.getAttribute(sAttribute);
        if (actualValue.contains(sData)) {
            world.softAssertMy.assertTrue(true, "Match : Expected - " + sData + " and Actual - " + actualValue);
            return true;
        } else {
            world.softAssertMy.assertTrue(false, "Mistmatch : Expected - " + sData + " and Actual - " + actualValue);
            return false;
        }
        //Assert.assertTrue(actualValue.contains(sData),"Mistmatch : Expected - " + sData + " and Actual - " + actualValue);

    }


    @And("^(?:I|i) expect \"([^\"]*)\" attribute of \"([^\"]*)\" object is not having string \"([^\"]*)\"$")
    public void verifyAttributeNotContains(String sAttribute, String sObject, String sData) throws IOException {
        WebElement Object = null;
        try {
            Object = this.world.getWebElementFactory().getElement(sObject);
            String actualValue = Object.getAttribute(sAttribute);
            Assert.assertFalse(actualValue.contains(sData),
                    "Mistmatch : Expected - " + sData + " and Actual - " + actualValue);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Then("^(?:I|i) expect text displayed for \"([^\"]*)\" is \"([^\"]*)\"$")
    public void i_expect_text_displayed_for_is(String sObject, String sData) throws ObjectNotFoundInORException {
        WebElement Object = null;

        try {
            Object = this.world.getWebElementFactory().getElement(sObject);
            String actualValue = Object.getText();
			/*Assert.assertTrue(actualValue.equalsIgnoreCase(sData),
					"Mistmatch : Expected - " + sData + " and Actual - " + actualValue);*/
            if (actualValue == null) {
                String script = "return document.getElementByXPath('" + sObject + "').getText();";
                actualValue = ((JavascriptExecutor) this.world.getWebDriver()).executeScript(script).toString();
            }

            if (!actualValue.equalsIgnoreCase(sData))
                World.softAssertMy.assertTrue(false, new Throwable().getStackTrace()[0].getLineNumber()+"Expected: "+sData+" Actual: "+actualValue + this.world.RED);

            //Replace above code for reading data from DataRepository
            /*String expectedValue = DataRepository.getValue(sData);
            try {
                Object = this.world.getWebElementFactory().getElement(sObject);
                String actualValue = Object.getText();
			*//*Assert.assertTrue(actualValue.equalsIgnoreCase(sData),
					"Mistmatch : Expected - " + sData + " and Actual - " + actualValue);*//*
                if (actualValue == null) {
                    String script = "return document.getElementByXPath('" + sObject + "').getText();";
                    actualValue = ((JavascriptExecutor) this.world.getWebDriver()).executeScript(script).toString();
                }

                if (actualValue.equalsIgnoreCase(expectedValue)) {
                    World.softAssertMy.assertTrue(true,
                            this.world.GREEN + "Displayed as Expected. Expected - " + expectedValue + " and Actual - " + actualValue + World.RESET);
                    Print.printGreen("Displayed as Expected. Expected - " + expectedValue + " and Actual - " + actualValue);
                } else {
                    World.softAssertMy.assertTrue(false, new Throwable().getStackTrace()[0].getLineNumber() + this.world.RED
                            + "Mistmatch : Expected - " + expectedValue + " and Actual - " + actualValue + World.RESET);
                    Print.printRed("Mistmatch : Expected - " + expectedValue + " and Actual - " + actualValue);
                }*/

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Then("^I expect \"([^\"]*)\" attribute for \"([^\"]*)\" is \"([^\"]*)\"$")
    public void i_expect_something_attribute_for_something_is_something(String sAttribute, String sObject, String sValue) throws Throwable {

        WebElement Object = null;
        try {
            Object = this.world.getWebElementFactory().getElement(sObject);
            String actualValue = Object.getAttribute(sAttribute);
            World.softAssertMy.assertTrue(actualValue.equalsIgnoreCase(sValue),
                    "Mistmatch : Expected - " + sValue + " and Actual - " + actualValue);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}