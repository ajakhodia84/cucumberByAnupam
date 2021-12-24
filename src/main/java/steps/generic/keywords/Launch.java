package steps.generic.keywords;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import framework.shared.GetUrl;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Random;

public class Launch {
    World world;

    public Launch(World world) {

        this.world = world;
    }

    GetUrl getConfig = new GetUrl();

    public void launch(String sUrl, Boolean proxyEnabled) {
        try {

            world.setCapabilities(proxyEnabled);
            if (proxyEnabled)
                System.out.println("Launching proxy enabled browser");
            else
                System.out.println("Launching proxy disabled browser");
            System.out.println(sUrl);
            world.getWebDriver().get(sUrl);
            new Wait(this.world).waitForPageLoad();
            String screenSize = System.getProperty("screensize", null);
            if (screenSize != null) {
                try {
                    screenSize = screenSize.toLowerCase();
                    int height, width;
                    height = Integer.valueOf(screenSize.split("x")[0]);
                    width = Integer.valueOf(screenSize.split("x")[1]);
                    Dimension dimension = new Dimension(width, height);
                    world.getWebDriver().manage().window().setSize(dimension);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    System.out.println("Error in setting screensize");
                    world.getWebDriver().manage().window().maximize();
                }
            } else {
                maximizeBrowser(world.getWebDriver());
            }

        } catch (Exception e) {
            System.out.println("Exception while launch :: " + e);
            Assert.fail(e.getMessage());
        }
    }

    @Given("^(?:I|i) navigate to \"([^\"]*)\" accessed by url \"([^\"]*)\"$")
    public void navigateTo(String sPage, String sUrl) {
        String Url = this.getConfig.getUrl(sUrl);
        System.out.println("Navigating to : " + sPage + " Using url :" + Url);
        this.world.getWebDriver().get(Url);
        maximizeBrowser(this.world.getWebDriver());
    }

    public void maximizeBrowser(WebDriver driver) {
        try {
            driver.manage().window().maximize();
        } catch (Exception ex) {
            System.out.println("Exception in maximizing browser window");
        }
    }

    @Given("^(?:i|I) navigate to wrong url$")
    public void navigateToWrongUrl() throws Throwable {
        String url = world.getWebDriver().getCurrentUrl();
        url = url.toLowerCase().replace(".html", "");
        Random rand = new Random();
        int n = rand.nextInt(50) + 1;
        url = url + n + ".html";
        world.getWebDriver().navigate().to(url);
    }

}
