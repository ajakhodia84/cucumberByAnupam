package steps.generic.keywords;

import cucumber.api.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import cucumber.api.java.en.Then;
import framework.selenium.support.Print;

import java.io.IOException;

public class VerifyImage {

    World world;

    public VerifyImage(World world) {
        this.world = world;
    }

    @Then("^(?:I|i) expect \"([^\"]*)\" is displayed for \"([^\"]*)\" element locator")
    public boolean verifyImageDisplayedOrNot(String sImageType, String sObject) {

        WebElement Object = null;
        try {
            Object = this.world.getWebElementFactory().getElement(sObject);
            if (Object.isDisplayed()) {
                World.softAssertMy.assertTrue(true,
                        this.world.GREEN + sImageType + " is displayed on the screen" + World.RESET);
                System.out.println(sImageType + " is displayed on the screen for the given object: " + sObject);
                Print.printGreen(sImageType + " is displayed on the screen for the given object: " + sObject);
                return true;
            } else {
                World.softAssertMy.assertTrue(false, new Throwable().getStackTrace()[0].getLineNumber() + this.world.RED
                        + sImageType + " is not displayed on given screen" + World.RESET);
                System.out.println(sImageType + " is not displayed on the screen for the given object: " + sObject);
                Print.printGreen(sImageType + " is not displayed on the screen for the given object: " + sObject);
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
            System.out.println(sImageType + " is not successful on the screen for the given object: " + sObject);
            Print.printGreen(sImageType + " iis not successful on the screen for the given object: " + sObject);
            return false;
        }

    }

    @And("^(?:I|i) verify image \"([^\"]*)\" is displayed on the page")
    public void verifyImageLoaded(String sObject) throws IOException {
        WebElement image = this.world.getWebElementFactory().getElement(sObject, true);
        verifyImageLoaded(image);
    }

    public void verifyImageLoaded(By byObject) throws IOException {
        WebElement image = this.world.getWebElementFactory().getElement(byObject, true);
        verifyImageLoaded(image);
    }

    public void verifyImageLoaded(WebElement imageObject) throws IOException {
        String script = "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0";
        Boolean imageLoaded = false;
        for (int i = 1; i <= 15; i++) {
            imageLoaded = (Boolean) ((JavascriptExecutor) this.world.getWebDriver()).executeScript(script, imageObject);
            if (imageLoaded) {
                System.out.println("image is loaded correctly on the page");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Assert.assertTrue(imageLoaded, "image is not loaded on the page.");
    }

    public boolean verifyImageDisplayedOrNot(String sImageType, WebElement object) {


        try {

            if (object.isDisplayed()) {
                World.softAssertMy.assertTrue(true,
                        this.world.GREEN + sImageType + " is displayed on the screen" + World.RESET);
                System.out.println(sImageType + " is displayed on the screen for the given object: " + object.getText());
                Print.printGreen(sImageType + " is displayed on the screen for the given object: " + object.getText());
                return true;
            } else {
                World.softAssertMy.assertTrue(false, new Throwable().getStackTrace()[0].getLineNumber() + this.world.RED
                        + sImageType + " is not displayed on given screen" + World.RESET);
                System.out.println(sImageType + " is not displayed on the screen for the given object: " + object.getText());
                Print.printGreen(sImageType + " is not displayed on the screen for the given object: " + object.getText());
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
            System.out.println(sImageType + " is not successful on the screen for the given object: " + object.getText());
            Print.printGreen(sImageType + " iis not successful on the screen for the given object: " + object.getText());
            return false;
        }

    }
}
