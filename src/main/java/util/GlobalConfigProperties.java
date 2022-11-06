package util;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class GlobalConfigProperties {
    public static GlobalConfigProperties globalConfigProperties;
    private static LinkedHashMap<String,String> properties;

    private GlobalConfigProperties(String path) throws IOException {
        Properties prop=PropertiesFileHelper.readProperty(path);
        properties=new LinkedHashMap<>();
        properties.putAll((Map)prop);
        System.out.println(properties);
    }

    public static void loadProperties() throws IOException {
        if(globalConfigProperties==null){
            globalConfigProperties=new GlobalConfigProperties(Constants.CONFIG_PROPERTIES);
        }
    }

    public String getProperty(String key){
        if(properties.containsKey(key)){
            return properties.get(key);
        }else {
            return null;
        }
    }

}
