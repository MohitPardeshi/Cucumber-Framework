package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

public class YAMLHelper {

    public static LinkedHashMap<String, String> readYML(String path) throws IOException {
        FileInputStream fileInputStream=new FileInputStream(path);
        ObjectMapper objectMapper=new ObjectMapper(new YAMLFactory());
        LinkedHashMap<String, String> data=objectMapper.readValue(fileInputStream,LinkedHashMap.class);
        return data;
    }

}
