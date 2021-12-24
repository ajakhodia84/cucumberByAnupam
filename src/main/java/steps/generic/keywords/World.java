package steps.generic.keywords;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import framework.configuration.Configuration;
import framework.selenium.support.DataRepository;
import framework.selenium.support.WebDriverFactory;
import framework.selenium.support.WebElementFactory;
import framework.shared.FrameworkConstants;
import framework.shared.ScenarioContext;
import joptsimple.internal.Strings;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class World {
    private DesiredCapabilities capabilities;
    private WebDriver driver = null;
    Scenario scenario;
    private String sOrName;
    private String sFeatureName;
    private static volatile int nextID = 1;
    private String brandNameForScenarioContext = "";
    private ScenarioContext scenarioContext = new ScenarioContext();
    private String brandName = "";
    private String Localname = "";
    private String scenarioId = "";
    private WebElementFactory webElementFactory = null;
    public static SoftAssert softAssertMy;
    public static final String RED = "\u001B[31m";
    public static final String BLACK = "\u001B[30m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String RESET = "\u001B[0m";

    @Before
    public void before(Scenario scenario) {
        softAssertMy = new SoftAssert();
        this.scenario = scenario;
        System.out.println(scenario.getId());
        int iPos = scenario.getId().lastIndexOf(":");
        sFeatureName = scenario.getId().substring(0, iPos);
        sFeatureName = sFeatureName.trim();
        String[] paths = sFeatureName.split("/");
        int sizeOfPaths = paths.length;
        sFeatureName = paths[sizeOfPaths - 1];
        sFeatureName = sFeatureName.replaceAll("(?i).feature", "");
        System.out.println("*******************************************************************************");
        System.out.println("Feature Name:: " + sFeatureName);
        System.out.println("Scenario Name:: " + scenario.getName());
        System.out.println("*******************************************************************************");
        //setOrName();
        this.scenarioContext.resetScenarioContext();
        this.scenarioId = scenario.getName();
        if (this.scenarioId.contains("sid=")) {
            this.scenarioId = this.scenarioId.split("sid=")[1].trim();
        }
    }

    @After
    public void after(Scenario scenario) throws InterruptedException {
        System.out.println("Reach in after");
        this.scenarioContext.resetScenarioContext();

        if (null != driver) {
            try {
                if (scenario.isFailed()) {
                    // This takes a screenshot from the driver at save it to the specified location
                    System.out.println("Taking screenshot");
                    TakesScreenshot ts = (TakesScreenshot) driver;
                    byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                    scenario.embed(screenshot, "image/png");
                    Thread.sleep(5000);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            softAssertMy.assertAll();
            driver.close();
            /*try {
                softAssertMy.assertAll();
            } catch (AssertionError error) {
                System.out.println("Taking screenshot");
                TakesScreenshot ts = (TakesScreenshot) driver;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
                Thread.sleep(5000);
                softAssertMy.assertAll();
            }*/
        }
    }


/*    @BeforeStep
    public void beforeStep() {
        System.out.println("======>  This is before step  <======");
        //Do something before executing the step
    }*/

    @AfterStep
    public void afterStep(Scenario scenario) {
        System.out.println("Reached After step");
        //this.scenarioContext.resetScenarioContext();

        if (null != driver) {
            try {
                //if (scenario.isFailed()) {
                    // This takes a screenshot from the driver at save it to the specified location
                    System.out.println("Taking screenshot");
                    TakesScreenshot ts = (TakesScreenshot) driver;
                    byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                    scenario.embed(screenshot, "image/png");
                    Thread.sleep(5000);

                //}

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ScenarioContext getScenarioContext() {
        return this.scenarioContext;
    }

    private void setOrName() {
        try {
            Properties orMapping = new Properties();
            File ORfile = new File(FrameworkConstants.SRC_MAIN_RESOURCES + "Config/config.properties");
            FileInputStream fs = new FileInputStream(ORfile);
            orMapping.load(fs);
            sOrName = orMapping.getProperty(sFeatureName, sFeatureName);
            // System.out.println("setting object repository as " + this.sOrName);
        } catch (FileNotFoundException ex) {
            System.out.println("Config/config.properties file is not found.");
            sOrName = sFeatureName;
        } catch (Exception e) {
            System.out.println("Exception occurred while loading Config/config.properties");
            sOrName = sFeatureName;
        }
    }


    public String getFeatureName() {
        return sFeatureName;
    }

    public String getOrName() {
        return sOrName;
    }

    public synchronized void setBrowserCapabilities(boolean proxyEnabled) throws Exception {
        driver = null;
        String execution = Configuration.getConfig("execution");
        String sBrowser = Configuration.getConfig("browser");
        if (Strings.isNullOrEmpty(execution)) {
            Assert.fail("execution parameter in config/cli must not be empty. It should be local/remote");
        }
        if (Strings.isNullOrEmpty(sBrowser)) {
            Assert.fail("browser parameter in config/cli must not be empty. It should be chrome/firefox");
        }

        if (execution.equalsIgnoreCase("Local") && (!sBrowser.contains("device"))) {
            driver = WebDriverFactory.getLocalDriver(sBrowser, proxyEnabled);

        }

        if (execution.equalsIgnoreCase("Local") && (sBrowser.contains("device"))) {
            driver = WebDriverFactory.getRemoteDriver(sBrowser);
        } else
            System.out.println("config.properties file - mention 'local' or 'remote' only in execution property");
        this.webElementFactory = new WebElementFactory(driver, brandName);
    }

    public synchronized void setCapabilities(Boolean proxyEnabled) throws Exception {
        //int threadNumber = World.getThreadNumber();
        //System.out.println("Thread Number:: " + threadNumber);
        setBrowserCapabilities(proxyEnabled);
    }

    public void reportStepInfo(String text) {
        System.out.println(this.scenario.getName() + " >>>>>>> " + System.lineSeparator() + text);
        this.scenario.write(text);
    }

    public synchronized WebDriver getWebDriver() {
        return driver;
    }

    public String getBrandNameForScenarioContext() {
        return brandNameForScenarioContext;
    }

    public void setBrandNameForScenarioContext(String brandNameForScenarioContext) {
        this.brandNameForScenarioContext = brandNameForScenarioContext;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public void setBrandName(String brandName, String Localname) {
        this.brandName = brandName;
        this.webElementFactory.setOrName(Localname);
        DataRepository.setDrName(Localname);
        this.Localname = Localname;
    }


    public String getScenarioId() {
        return this.scenarioId;
    }

    public WebElementFactory getWebElementFactory() {
        return this.webElementFactory;
    }
}
