package framework.selenium.support;

import framework.exceptions.ObjectNotFoundInORException;
import framework.shared.FrameworkConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Properties;

public class DataRepository {

    private static HashMap<String, String> commonDrMap = new HashMap<String, String>();
    private static HashMap<String, HashMap<String, String>> brandDrMap = new HashMap<String, HashMap<String, String>>();
    public static String DrName;

    public static void setDrName(String drName) {
        DrName = drName;
    }

    public static String getValue(String objectName) throws ObjectNotFoundInORException {
        return getTextValue(objectName, DrName);
    }

    //synchronized purpose is, not to share this method with thread at the same time. One thread completes its work then another one starts
    //using this method
    public static synchronized void loadDr(String DrName) {
        System.out.println(DrName + " Data Repository is loading");
        HashMap<String, String> localDrMap = new HashMap<String, String>();
        try {
            if (!brandDrMap.containsKey(DrName)) {

                Properties dataRepository = new Properties();
                File DRfile = new File(FrameworkConstants.DATA_REPOSITORY_FOLDER + DrName + ".properties");
                FileInputStream fs = new FileInputStream(DRfile);
                dataRepository.load(new InputStreamReader(fs, Charset.forName("UTF-8")));
                dataRepository.forEach((key, value) -> localDrMap.put((String) key, (String) value));
                fs.close();
                brandDrMap.put(DrName, localDrMap);
            }
        } catch (FileNotFoundException e) {
            System.out.println("data repository with " + DrName + " name is not present");
            brandDrMap.put(DrName, localDrMap);
        } catch (Exception e) {
            System.out.println("Error while loading Data Repository");
        }
    }

    public static synchronized String getTextValue(String ObjectName, String DrName)
            throws ObjectNotFoundInORException {
        String value = null;
        if (!brandDrMap.containsKey(DrName)) {
            loadDr(DrName);
        }
        value = brandDrMap.get(DrName).get(ObjectName);
        if (value != null) {
            System.out.println(ObjectName + "::DR[" + DrName + "]::Value[" + value + "]");
            return value;
        }
        throw new ObjectNotFoundInORException(ObjectName + "::Not Found In DR[ " + DrName + "]");
    }
}