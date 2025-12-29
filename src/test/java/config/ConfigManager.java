package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties;

    private static Properties loadProperty()
    {
        if(properties==null)
        {
            System.out.println("Insideload property");
            try
                    (InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties"))
            {
            if(is ==null)
            {
                throw new RuntimeException("Config file not persent in class path");
            }
            properties=new Properties();
            properties.load(is);
        } catch (IOException e) {
                throw new RuntimeException("Fail to load config file "+e.getMessage());
            }
        }
        return properties;
    }


    public static  String getProperties(String key)
    {
        return loadProperty().getProperty(key);
    }
}
