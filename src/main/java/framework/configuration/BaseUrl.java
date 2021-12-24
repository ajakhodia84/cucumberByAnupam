package framework.configuration;

import framework.shared.FrameworkConstants;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class BaseUrl {
	
	private static Map<String, String> baseUrlMap = null;
	
	public static String getBaseUrl(String key) {
		if(baseUrlMap == null) {
			baseUrlMap = readBaseUrlFile();
		}
		return baseUrlMap.get(key);
	}
	
	private static Map<String, String> readBaseUrlFile(){
		Properties prop = new Properties();
		Map<String,String> map = new HashMap<String,String>();
		try
        {
            FileInputStream inputStream = new FileInputStream(FrameworkConstants.TEST_BASEURL_MAPPING_FILE);
            prop.load(inputStream);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Some issue finding or loading file. (" + FrameworkConstants.TEST_BASEURL_MAPPING_FILE + ") ...!!! " + e.getMessage());
            System.exit(0);
        } 
		map.putAll(prop.entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey().toString(), 
                                          e -> e.getValue().toString())));
		return map;
	}

}
