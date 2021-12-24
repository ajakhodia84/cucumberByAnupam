package framework.shared;

import com.google.common.base.Strings;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class GetUrl {
    Properties Config;
    String sUrl;

    public GetUrl() {
        try {
            Config = new Properties();
            File Configfile = new File(FrameworkConstants.TEST_DATA_FOLDER + "urls.properties");
            FileInputStream fs = new FileInputStream(Configfile);
            Config.load(new InputStreamReader(fs, Charset.forName("UTF-8")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Properties getConfiguration() {
        return Config;
    }

    public String getUrl(String key) {
        String value = null;

        try {
            if (Strings.isNullOrEmpty(key)) {
                return null;
            }
            value = Config.getProperty(key.trim(), null);
        } catch (Exception e) {
            System.out.println(e);
        }
        return value;
    }
}
