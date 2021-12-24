package steps.generic.keywords;

import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Then;

public class EnterTextInField {
	
	World world;

	public EnterTextInField(World world) {

		this.world = world;
	}
	
	@Then("I enter \"([^\"]*)\" in \"([^\"]*)\"")
	public void enterTextinField(String textToEnter, String sObject) {
		WebElement Object;
		Object = this.world.getWebElementFactory().getElement(sObject);
		
		try{
			Object.clear();
			Object.sendKeys(textToEnter);
			System.out.println("User is able to enter text "+textToEnter+" in given field "+sObject);
			
		}catch(Exception e){
			System.out.println("User is not able to enter text "+textToEnter+" in given field "+sObject+" Please check details error");
		}
	}
	
}
