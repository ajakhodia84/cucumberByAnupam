package steps.business.generic;

import cucumber.api.java.en.Given;
import framework.configuration.BaseUrl;
import framework.configuration.Configuration;
import steps.generic.keywords.World;
import framework.shared.GetUrl;
import framework.shared.UrlHandler;
import org.testng.Assert;

public class StepLaunch {

    private World capabilities;
    public static UserActions getBasicKeywords;
    GetUrl getConfig = new GetUrl();

    public StepLaunch(UserActions getBasicKeywords, World world) {
        this.capabilities = world;
        this.getBasicKeywords = getBasicKeywords;
    }

    @Given("^User is at \"([^\"]*)\" brand's \"([^\"]*)\" page of \"([^\"]*)\" locale$")
    public void openBrandUrl(String brand, String pageName, String locale) throws Throwable {
        this.capabilities.getScenarioContext().addScenarioData("brand", brand);
        this.capabilities.getScenarioContext().addScenarioData("locale", locale);
        try {
            String url = getConfig.getUrl(brand + "." + locale + "." + pageName);

            if (url == null)
                Assert.fail("no url is present in urls.properties for key :: " + brand + "." + locale + "." + pageName);
            UrlHandler urlHandler = new UrlHandler();
            if (!(url.trim().startsWith("http"))) {
                url = urlHandler.append(BaseUrl.getBaseUrl(brand + "." + locale + "." + Configuration.getEnvironment().getEnvironmentName().trim()))
                        .append(url).getUrl();
            }
            System.out.print("Url is : " + url + "\n");

            this.getBasicKeywords.Launch.launch(url, false);
            this.capabilities.setBrandName(brand, locale);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
