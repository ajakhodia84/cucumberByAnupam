package steps.generic.keywords;

import org.openqa.selenium.WebElement;

import framework.selenium.support.Print;

public class VerificationHelper {
	
	World world;
	ClickOnElementOrLink clickOnElementOrLink = new ClickOnElementOrLink(this.world);

	public VerificationHelper(World world) {
		this.world = world;
	}

	public boolean isDisplayed(WebElement element){
		try{
			element.isDisplayed();
			Print.printGreen("Passed: element is Displayed.."+element.getText());
			return true;
		}
		catch(Exception e){
			Print.printRed("Failed: element is not Displayed.."+ e.getCause());
			return false;
		}
	}
	
	public boolean isDisplayed(String sObject){
		try{
			WebElement element=this.world.getWebElementFactory().getElement(sObject);
			element.isDisplayed();
			Print.printGreen("Passed: element is Displayed.."+sObject);
			return true;
		}
		catch(Exception e){
			Print.printRed(sObject+" Failed: element is not Displayed.."+ e.getCause());
			return false;
		}
	}
	
	public boolean isNotDisplayed(WebElement element){
		try{
			if(!element.isDisplayed()){
			Print.printGreen("Passed: element is not present.."+element.getText());
			System.out.println("Passed: element is not present.."+element.getText());
			return true;
			}else{
				Print.printGreen("Failed: element is present.."+element.getText());
				System.out.println("Failed: element is present.."+element.getText());
				return false;
			}
		}
		catch(Exception e){
			Print.printRed("Failed: element is present..");
			return true;
		}
	}
	
	public boolean isNotDisplayed(String sObject){
		try{
			WebElement element=this.world.getWebElementFactory().getElement(sObject);
			if(!element.isDisplayed()){
				Print.printGreen("Passed: element is not present.."+sObject);
				System.out.println("Passed: element is not present.."+sObject);
				return true;
				}else{
					Print.printGreen("Failed: element is present.."+sObject);
					System.out.println("Failed: element is present.."+sObject);
					return false;
				}
		}
		catch(Exception e){
			Print.printRed("Failed: element is present.."+sObject);
			return true;
		}
	}
	
	public String readValueFromElement(WebElement element){
		if(null == element){
			Print.printGreen("WebElement is null..");
			return null;
		}
		boolean status = isDisplayed(element);
		if(status){
			Print.printGreen("element text is .."+element.getText());
			return element.getText();
		}
		else{
			return null;
		}
	}
	public String getText(WebElement element){
		if(null == element){
			Print.printGreen("WebElement is null..");
			return null;
		}
		boolean status = isDisplayed(element);
		if(status){
			Print.printGreen("element text is .."+element.getText());
			return element.getText();
		}
		else{
			return null;
		}
	}
	
	public String getTitle(){
		Print.printGreen("page title is: "+this.world.getWebDriver().getTitle());
		return this.world.getWebDriver().getTitle();
	}
}
