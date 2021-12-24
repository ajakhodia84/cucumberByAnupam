package framework.cucumber.runner;

import framework.reporting.CreateReport;
import framework.selenium.support.ObjectRepository;
import framework.shared.FrameworkConstants;
import framework.shared.TimeUtil;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;

public class CustomrRunner {

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        System.out.println("Execution started at - " + TimeUtil.getCurrentDate("yyyy-MM-dd-HH-mm-ss"));
        deletePreviousJsonReports();
        ObjectRepository.loadCommonOr(FrameworkConstants.COMMON_OR_NAME);
    }

    private void deletePreviousJsonReports() {
        File reportFolder = new File(FrameworkConstants.REPORTS_FOLDER);
        File[] files = reportFolder.listFiles();
        System.out.println("Deleting previous reports..");
        if (files.length == 0) {
            System.out.println("No previous report found in " + FrameworkConstants.REPORTS_FOLDER + " folder");
            return;
        }
        for (File f : files) {
            System.out.println("Deleted report ::> " + f.getName());
            FileUtils.deleteQuietly(f);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        System.out.println("Creating Report..");
        CreateReport.createFinalReport();
        System.out.println(TimeUtil.getCurrentDate("yyyy-MM-dd-HH-mm-ss"));
        System.out.println("Execution completed at - " + TimeUtil.getCurrentDate("yyyy-MM-dd-HH-mm-ss"));
    }
}
