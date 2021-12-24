package steps.generic.keywords;

import cucumber.api.java.en.When;

public class PageSprecificActions {
	
	World world;

	public PageSprecificActions(World world) {

		this.world = world;
	}
	
	
	@When("^I refresh the page$")
    public void i_refresh_the_page() throws Throwable {
		this.world.getWebDriver().navigate().refresh();
    }

}
