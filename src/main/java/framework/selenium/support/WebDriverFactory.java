package framework.selenium.support;

import framework.shared.FrameworkConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;

public class WebDriverFactory {
	protected static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);

	public static WebDriver getChromeDriver(boolean proxyEnabled) {
		try {
			WebDriver driver = null;
			if (System.getProperty("os.name").toLowerCase().contains("win")) {
				System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
						FrameworkConstants.DRIVERS_FOLDER + "chromedriver.exe");
			}
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			options.addArguments("--incognito");
			options.addArguments("--start-maximized");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-features=VizDisplayCompositor");
			options.setAcceptInsecureCerts(true);
			options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			// options.setCapability("chrome.switches",
			// Arrays.asList("--ignore-certificate-errors"));
			options.setCapability(CapabilityType.LOGGING_PREFS, getLoggingLevel());
			driver = new ChromeDriver(options);
			return driver;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			System.out.println("Exception occurred in getting chromedriver object");
			return null;
		}
	}

	public static ChromeOptions getChromeOptionsForHeadless() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--test-type");
		chromeOptions.addArguments("--disable-popup-blocking");
		chromeOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");

		DesiredCapabilities capChrome = DesiredCapabilities.chrome();
		capChrome.setJavascriptEnabled(true);

		chromeOptions.setCapability(ChromeOptions.CAPABILITY, capChrome);

		return chromeOptions;
	}

	public static WebDriver getChromeHeadLessDriver(ChromeOptions cap) {

		if (System.getProperty("os.name").contains("Mac")) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
					FrameworkConstants.DRIVERS_FOLDER + "chromedriver.exe");
			return new ChromeDriver(cap);
		} else if (System.getProperty("os.name").contains("Window")) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
					FrameworkConstants.DRIVERS_FOLDER + "chromedriver.exe");
			return new ChromeDriver(cap);
		} else if (System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chrome");
			return new ChromeDriver(cap);
		}
		return null;
	}

	public static WebDriver getFirefoxDriver(boolean proxyEnabled) {
		try {
			WebDriver driver = null;
			if (System.getProperty("os.name").toLowerCase().contains("win")) {
				System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY,
						FrameworkConstants.DRIVERS_FOLDER + "geckodriver.exe");
			}
			FirefoxOptions options = new FirefoxOptions();
			options.setAcceptInsecureCerts(true);
			options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			options.setCapability(CapabilityType.LOGGING_PREFS, getLoggingLevel());
			driver = new FirefoxDriver(options);
			return driver;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception occurred in getting firefoxdriver object");
			return null;
		}
	}

	public static RemoteWebDriver getRemoteWebDriver(String browser) {
		final String USERNAME = "anupamjakhodia2";
		final String AUTOMATE_KEY = "5yiNZULBzSJ4vkwYVWTY";
		final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

		RemoteWebDriver driver = null;
		DesiredCapabilities caps = new DesiredCapabilities();
		HashMap<String, Boolean> networkLogsOptions = new HashMap<>();

		switch (browser.toLowerCase()) {
		case "iphonedevice":
			caps.setCapability("browserName", "iPhone");
			caps.setCapability("device", "iPhone XS");
			caps.setCapability("realMobile", "true");
			caps.setCapability("os_version", "14");			
			//networkLogsOptions.put("captureContent", true);
			caps.setCapability("browserstack.networkLogs", "true");
			caps.setCapability("browserstack.networkLogsOptions", networkLogsOptions);
			caps.setCapability("browserstack.console", "info");
			break;
		case "ipadprodevice":
			caps.setCapability("browserName", "iPhone");
			caps.setCapability("device", "iPad Pro 12.9 2020");
			caps.setCapability("realMobile", "true");
			caps.setCapability("os_version", "14");			
			//networkLogsOptions.put("captureContent", true);
			caps.setCapability("browserstack.networkLogs", "true");
			caps.setCapability("browserstack.networkLogsOptions", networkLogsOptions);
			caps.setCapability("browserstack.console", "info");
			break;
		case "ipadminidevice":
			caps.setCapability("browserName", "iPhone");
			caps.setCapability("device", "iPad 8th");
			caps.setCapability("realMobile", "true");
			caps.setCapability("os_version", "14");
			//networkLogsOptions.put("captureContent", true);
			caps.setCapability("browserstack.networkLogs", "true");
			caps.setCapability("browserstack.networkLogsOptions", networkLogsOptions);
			caps.setCapability("browserstack.console", "info");
			break;
		case "androiddevice":
			caps.setCapability("browserName", "android");
			caps.setCapability("device", "Samsung Samsung Galaxy S20");
			caps.setCapability("realMobile", "true");
			caps.setCapability("os_version", "10.0");
			//networkLogsOptions.put("captureContent", true);
			caps.setCapability("browserstack.networkLogs", "true");
			caps.setCapability("browserstack.networkLogsOptions", networkLogsOptions);
			caps.setCapability("browserstack.console", "info");
			break;
		case "macCatalina":
			caps.setCapability("os", "OS X");
			caps.setCapability("os_version", "Catalina");
			caps.setCapability("browser", "Safari");
			caps.setCapability("browser_version", "13.0");
			caps.setCapability("browserstack.local", "false");
			//caps.setCapability("browserstack.selenium_version", "3.14.0");
			//networkLogsOptions.put("captureContent", true);
			caps.setCapability("browserstack.networkLogs", "true");
			caps.setCapability("browserstack.networkLogsOptions", networkLogsOptions);
			caps.setCapability("browserstack.console", "info");
			break;	
		default:
			Assert.fail(browser + " Device is not supported yet on remote executions");
			driver = null;
			break;
		}

		caps.setCapability("name", "anupamjakhodia2's First Test");

		try {
			driver = new RemoteWebDriver(new URL(URL), caps);
			Print.printGreen("RemoteWebDriver is initialezed properly");
			return driver;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			Print.printRed("Exception occurred in getting remoteWebDriver object for the device " + browser);
			return null;

		}

	}

	public static WebDriver getLocalDriver(String browser, boolean proxyEnabled) {
		WebDriver driver = null;
		switch (browser.toLowerCase()) {
		case "chrome":
			driver = getChromeDriver(proxyEnabled);
			break;
		case "firefox":
			driver = getFirefoxDriver(proxyEnabled);
			break;
		case "chromeheadless":
			System.out.println("Launching headless browser");
			ChromeOptions optionheadless = getChromeOptionsForHeadless();
			driver = getChromeHeadLessDriver(optionheadless);

		default:
			Assert.fail(browser + " browser is not supported yet on local executions");
			driver = null;
			break;
		}
		return driver;
	}

	public static WebDriver getRemoteDriver(String browser) {
		WebDriver driver = null;

		try {
			driver = getRemoteWebDriver(browser);
			Print.printGreen(browser + " Device is properly initialized");
			return driver;
		} catch (Exception e) {
			Print.printRed(browser + " Device is not supported yet on local executions");
			return null;
		}

	}

	private static LoggingPreferences getLoggingLevel() {
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.BROWSER, Level.ALL);
		logPrefs.enable(LogType.CLIENT, Level.OFF);
		logPrefs.enable(LogType.DRIVER, Level.OFF);
		logPrefs.enable(LogType.PERFORMANCE, Level.OFF);
		logPrefs.enable(LogType.PROFILER, Level.OFF);
		logPrefs.enable(LogType.SERVER, Level.OFF);
		return logPrefs;
	}
}
