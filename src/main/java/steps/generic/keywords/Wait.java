package steps.generic.keywords;

import cucumber.api.java.en.And;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Wait {

	public World world;

	public Wait(World world) {
		this.world = world;
	}

	@And("^(?:I|i) wait for page load$")
	public void waitForPageLoad() {
		waitForPageReady(60);
	}

	@And("^(?:I|i) wait for (.*) seconds$")
	public void wait(String sData) throws NumberFormatException, InterruptedException {
		sData = sData + "000";
		Thread.sleep(Integer.parseInt(sData));
	}

	public void waitForPageReady(int seconds) {
		try {
			String sPageInteractiveStatus = "";
			for (int iPageStatusLoop = 0; iPageStatusLoop < seconds; iPageStatusLoop++) {
				if (sPageInteractiveStatus.equalsIgnoreCase("complete")
						|| sPageInteractiveStatus.equalsIgnoreCase("interactive")) {
					Thread.sleep(500);
					break;
				} else {
					Thread.sleep(1000);
					sPageInteractiveStatus = String.valueOf(((JavascriptExecutor) this.world.getWebDriver())
							.executeScript("return document.readyState"));
				}
			}
		} catch (Exception e) {
			System.out.println("error while getting page interactive status; " + e);
		}
	}

	/**
	 * This is ImplicitWait method
	 * 
	 * @param timeout
	 * @param unit
	 */
	public void setImplicitWait(long timeout, TimeUnit unit) {
		this.world.getWebDriver().manage().timeouts().implicitlyWait(timeout, unit);
	}

	/**
	 * This will help us to get WebDriverWait object
	 * 
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		WebDriverWait wait = new WebDriverWait(this.world.getWebDriver(), timeOutInSeconds);
		wait.pollingEvery(Duration.ofMillis(pollingEveryInMiliSec));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	/**
	 * This method will make sure element is visible
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public void WaitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds,
			int pollingEveryInMiliSec) {
		System.out.println("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(element));
		System.out.println("element is visible now");
	}

	/**
	 * This method will make sure elementToBeClickable
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void WaitForElementClickable(WebElement element, int timeOutInSeconds) {
		System.out.println("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(this.world.getWebDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		System.out.println("element is clickable now");
	}
	
	@And("^I wait for \"([^\"]*)\" to be clickable again$")
    public void i_wait_for_something_to_be_clickable_again(String sObject) throws Throwable {
        WebElement Object=this.world.getWebElementFactory().getElement(sObject);
        WaitForElementClickable(Object,20);
    }
	
	@And("^I wait for element's \"([^\"]*)\" \"([^\"]*)\" value to be \"([^\"]*)\"$")
	    public void waitForElementEnabledAgainAfterClick(String sObject, String sAttribute, String sValue) throws Throwable {
		 try{
		 WebElement Object=this.world.getWebElementFactory().getElement(sObject);
		 
		 System.out.println("waiting for :" + sObject + " for :" + " 20" + " seconds");		 	
			WebDriverWait wait = new WebDriverWait(this.world.getWebDriver(), 20);
			wait.until(ExpectedConditions.attributeToBe(Object, sAttribute, sValue));
					//attributeContains(Object, "class", "disabled"));
			System.out.println(Object+" attribute "+sAttribute+" Value is now: "+sValue);
		 
		 }catch(Exception e){
			 e.printStackTrace();
				World.softAssertMy.fail(
						"Failed: waitForElementEnabledAgainAfterClick");
		 }
	        
	    }

	/**
	 * This method will make sure invisibilityOf element
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean waitForElementNotPresent(WebElement element, long timeOutInSeconds) {
		System.out.println("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(this.world.getWebDriver(), timeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		System.out.println("element is invisibile now");
		return status;
	}

	/**
	 * This method will wait for frameToBeAvailableAndSwitchToIt
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForframeToBeAvailableAndSwitchToIt(WebElement element, long timeOutInSeconds) {
		System.out.println("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(this.world.getWebDriver(), timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		System.out.println("frame is available and switched");
	}


	public void pageLoadTime(long timeout, TimeUnit unit) {
		System.out.println("waiting for page to load for : " + unit + " seconds");
		this.world.getWebDriver().manage().timeouts().pageLoadTimeout(timeout, unit);
		System.out.println("page is loaded");
	}

	/**
	 * This method will make sure elementToBeClickable
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElement(WebElement element, int timeOutInSeconds, WebDriver driver) {
		try{
		System.out.println("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		System.out.println("element is visible now");
		}catch(Exception e){
			e.printStackTrace();
			World.softAssertMy.fail("waitForElement Failed: Given element is not displayed even after waiting"+ element.getAttribute("class"));
		}
	}

}
