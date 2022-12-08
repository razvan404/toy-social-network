package application.configuration;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * The class <b>Config</b> is used to easily manage the locations of the files in the application.
 */
public class Config {
    /**
     * @return a list of properties
     * @throws RuntimeException when the properties cannot be loaded
     */
    public static Properties getProperties() throws RuntimeException {
        try {
            Properties properties = new Properties();
            properties.load(Config.class.getResourceAsStream("config.properties"));
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * @param key the key of the property
     * @return the property associated to the key
     * @throws RuntimeException when the properties cannot be loaded
     */
    public static String getProperty(String key) {
        return getProperties().getProperty(key);
    }
}
