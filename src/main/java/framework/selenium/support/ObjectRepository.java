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

public class ObjectRepository {

    private static HashMap<String, String> commonOrMap = new HashMap<String, String>();
    private static HashMap<String, HashMap<String, String>> brandOrMap = new HashMap<String, HashMap<String, String>>();

    public static void loadCommonOr(String commonOrPath) {
        System.out.println("loading common or :: " + commonOrPath);
        try {
            Properties objectRepository = new Properties();
            File ORfile = new File(FrameworkConstants.OBJECT_REPOSITORY_FOLDER + commonOrPath);
            FileInputStream fs = new FileInputStream(ORfile);
            objectRepository.load(new InputStreamReader(fs, Charset.forName("UTF-8")));
            fs.close();
            objectRepository.forEach((key, value) -> commonOrMap.put((String) key, (String) value));
            fs.close();
        } catch (Exception e) {
            System.out.println("Error while loading Object Repository");
        }
    }

    //synchronized purpose is, not to share this method with thread at the same time. One thread completes its work then another one starts
    //using this method
    public static synchronized void loadOr(String orName) {
        System.out.println(orName + " OR is loading");
        HashMap<String, String> localOrMap = new HashMap<String, String>();
        try {
            if (!brandOrMap.containsKey(orName)) {

                Properties objectRepository = new Properties();
                File ORfile = new File(FrameworkConstants.OBJECT_REPOSITORY_FOLDER + orName + ".properties");
                FileInputStream fs = new FileInputStream(ORfile);
                objectRepository.load(new InputStreamReader(fs, Charset.forName("UTF-8")));
                objectRepository.forEach((key, value) -> localOrMap.put((String) key, (String) value));
                fs.close();
                brandOrMap.put(orName, localOrMap);
            }
        } catch (FileNotFoundException e) {
            System.out.println("object repo with " + orName + " name is not present");
            brandOrMap.put(orName, localOrMap);
        } catch (Exception e) {
            System.out.println("Error while loading Object Repository");
        }
    }

    public static synchronized String getLocatorValue(String ObjectName, String OrName)
            throws ObjectNotFoundInORException {
        String value = null;
        if (!brandOrMap.containsKey(OrName)) {
            loadOr(OrName);
        }
        value = brandOrMap.get(OrName).get(ObjectName);
        if (value != null) {
            System.out.println(ObjectName + "::OR[" + OrName + "]::Locator[" + value + "]");
            return value;
        }
        value = commonOrMap.get(ObjectName);
        if (value != null) {
            System.out.println(ObjectName + "::OR[" + FrameworkConstants.COMMON_OR_NAME + "]::Locator[" + value + "]");
            return value;
        }
        throw new ObjectNotFoundInORException(ObjectName + "::Not Found In OR[ " + OrName + "," + FrameworkConstants.COMMON_OR_NAME + "]");
    }
}
