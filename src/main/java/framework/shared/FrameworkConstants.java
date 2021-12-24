package framework.shared;

public class FrameworkConstants {

    public static final String SRC_MAIN_RESOURCES = "src/main/resources/";
    public static final String REPORTS_FOLDER = SRC_MAIN_RESOURCES + "Report/";
    public static final String TEST_CONFIG_FOLDER =  SRC_MAIN_RESOURCES + "Config/";
    public static final String CUCUMBER_HTML_REPORTS_FOLDER = "test-output/";
    private static String EXECUTION_TIME_STAMP = null;
    public static final String COMMON_OR_NAME = "common-or.properties";
    public static final String OBJECT_REPOSITORY_FOLDER = SRC_MAIN_RESOURCES + "ObjectRepository/";
    public static final String DATA_REPOSITORY_FOLDER = SRC_MAIN_RESOURCES + "DataRepository/";
    public static final String TEST_DATA_FOLDER = SRC_MAIN_RESOURCES + "TestData/";
    public static final String TEST_BASEURL_MAPPING_FILE = TEST_DATA_FOLDER + "baseurlmapping.properties";
    public static final String CONFIG_FILE_PATH = TEST_CONFIG_FOLDER + "config.properties";
    public static final String ENVIRONMENT_JSON_FILE_PATH = TEST_CONFIG_FOLDER + "Environment.JSON";
    public static final String DRIVERS_FOLDER = SRC_MAIN_RESOURCES + "Drivers/";
    public static final int SMALL_WAIT = 15;

    static {
        EXECUTION_TIME_STAMP = TimeUtil.getCurrentDate("yyyy-MM-dd-HH-mm-ss-SSS");
    }

    public static String getExecutionTimeStamp() {
        return EXECUTION_TIME_STAMP;
    }
}
