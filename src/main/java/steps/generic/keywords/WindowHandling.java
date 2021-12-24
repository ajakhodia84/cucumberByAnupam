package steps.generic.keywords;

import java.util.Set;

public class WindowHandling {
	World world;

	public WindowHandling(World world) {
		this.world = world;
	}

	public boolean checkWindowWithTitle(String title) {
		String currentWindow = world.getWebDriver().getWindowHandle();
		boolean result = false;
		Set<String> handles = world.getWebDriver().getWindowHandles();
		String pageTitle = title;
		System.out.println("Switching window..");
		for (String handle : handles) {
			world.getWebDriver().switchTo().window(handle);
			// Check Your Page Title
			if (world.getWebDriver().getTitle().equalsIgnoreCase(pageTitle))
				result = true;
			// world.getWebDriver().switchTo().window(handle);
			// System.out.println(World.GREEN + "Switched window successfully" +
			// World.RESET);

		}
		world.getWebDriver().switchTo().window(currentWindow);
		if (!result)
			World.softAssertMy.assertTrue(false,
					World.RED + "Page does not exists with title : " + title + World.RESET);
		return result;

	}

	public boolean switchToWindowWithTitle(String title) {
		String currentWindow = world.getWebDriver().getWindowHandle();
		Set<String> handles = world.getWebDriver().getWindowHandles();
		String pageTitle = title;
		boolean result = false;
		System.out.println("Switching window..");
		for (String handle : handles) {
			world.getWebDriver().switchTo().window(handle);
			// Check Your Page Title
			if (world.getWebDriver().getTitle().equalsIgnoreCase(pageTitle)) {

				result = true;
				System.out.println(World.GREEN + "Switched window successfully" + World.RESET);

			}

		}

		if (!world.getWebDriver().getTitle().equalsIgnoreCase(pageTitle))
			World.softAssertMy.assertTrue(false,
					World.RED + "Page does not exists with title : " + title + World.RESET);
		world.getWebDriver().switchTo().window(currentWindow);

		return result;
	}
}
