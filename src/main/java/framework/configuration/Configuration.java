package framework.configuration;

import com.google.common.base.Strings;
import framework.shared.FrameworkConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configuration {

	private static Environment Environment = null;
	private static Map<String,String> configurations = null;
	private static int hubCounter = 0;
	public static boolean createLocalReport = true;
	
	static {
		if(configurations == null) {
			configurations = new HashMap<String, String>();
			System.out.println("loading config :: ");
			try {
				Properties config = new Properties();
				File configFile = new File(FrameworkConstants.CONFIG_FILE_PATH);
				FileInputStream fs = new FileInputStream(configFile);
				config.load(new InputStreamReader(fs, Charset.forName("UTF-8")));
				fs.close();
				config.forEach((key, value) -> configurations.put(((String) key).toLowerCase(), (String) value));
				fs.close();
				String cliEnvironmentVal = System.getProperty("env");
				if (Strings.isNullOrEmpty(cliEnvironmentVal)) {
					setEnvironment(new EnvironmentDeserializer().getEnvironment());
				} else {
					System.out.println("Setting Environment :: " + cliEnvironmentVal);
					setEnvironment(new EnvironmentDeserializer().getEnvironment(cliEnvironmentVal));
				}

			} catch (Exception e) {
				System.out.println("Error while loading config.properties");
			}	
		}

	}
	
	public static String getConfig(String configKey) {
		return configurations.getOrDefault(configKey, null);
	}
	
	public static Environment getEnvironment() {
		return Environment;
	}

	public static void setEnvironment(Environment environment) {
		Environment = environment;
	}
}
