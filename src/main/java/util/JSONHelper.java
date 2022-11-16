package util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.TreeMap;

public class JSONHelper {

    public static TreeMap<String, Object> readJSON(String path) throws IOException {
        FileInputStream fileInputStream=new FileInputStream(path);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
        TreeMap<String,Object>treeMap=objectMapper.readValue(fileInputStream, TreeMap.class);
        return treeMap;
    }
}
