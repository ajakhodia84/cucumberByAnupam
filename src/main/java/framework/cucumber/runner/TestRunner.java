package framework.cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import steps.generic.keywords.World;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


@CucumberOptions(
		strict = true,
		features = "src/main/resources/Features",
		glue = { "steps" },
		tags = {"@Reg_globalFooter"},
		//tags={"@Reg_homePage","@Reg_globalHeader","@Reg_AccountDetails"},
		plugin = { "json:src/main/resources/Report/Automated_Test_Execution.json" })
public class TestRunner extends CustomrRunner {

	private TestNGCucumberRunner testNGCucumberRunner;
	World world;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void feature(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper cucumberFeature) throws Throwable {
		testNGCucumberRunner.runScenario(pickleWrapper.getPickleEvent());
	}

	@DataProvider
	public Object[][] scenarios() {
		if (testNGCucumberRunner == null) {
			return new Object[0][0];
		}
		return testNGCucumberRunner.provideScenarios();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		testNGCucumberRunner.finish();

	}
}
