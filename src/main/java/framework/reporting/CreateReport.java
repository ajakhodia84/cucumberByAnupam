package framework.reporting;

import framework.shared.CustomFileFilter;
import framework.shared.FrameworkConstants;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.reducers.ReducingMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CreateReport {

    public static void createFinalReport() {
        try {
            String sInputFilePaths = FrameworkConstants.REPORTS_FOLDER;
            String sTrackName = "Bdd Automation Report";
            File reportOutputDirectory = new File(FrameworkConstants.CUCUMBER_HTML_REPORTS_FOLDER + "Report-"
                    + FrameworkConstants.getExecutionTimeStamp());
            List<String> jsonFiles = new ArrayList<String>();
            File reportInputFolder = new File(sInputFilePaths);
            //lists all .json file present under sInputFilePaths directory (Report folder)
            File[] filesInput = reportInputFolder.listFiles(new CustomFileFilter(new String[] {".json"}));
            List<File> listInputFiles = Arrays.asList(filesInput);
            for (File inputFile : listInputFiles) {
                jsonFiles.add(sInputFilePaths + inputFile.getName());
            }
            Configuration configuration = new Configuration(reportOutputDirectory, sTrackName);
            configuration.setBuildNumber(new Date().toString());
            configuration.addReducingMethod(ReducingMethod.MERGE_FEATURES_BY_ID);
            ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
            reportBuilder.generateReports();
        } catch (Exception e) {
            // TODO Auto-generated catch blocks
            e.printStackTrace();
            System.out.println("Exception in generating final report");
        }
    }
}
