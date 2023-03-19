package util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.TreeMap;

public class JSONHelper {

    public static TreeMap<String, Object> readJSON(String path) throws IOException {
        //jsonValidator(path);
        FileInputStream fileInputStream=new FileInputStream(path);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
        TreeMap<String,Object>treeMap=objectMapper.readValue(fileInputStream, TreeMap.class);
        return treeMap;
    }

    public static void jsonValidator(String path) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonFactory jsonFactory=objectMapper.getFactory();
        JsonParser jsonParser=jsonFactory.createParser(path);
        JsonNode jsonNode= objectMapper.readTree(jsonParser);
        System.out.println("Validator : "+jsonNode.toString());
    }
}
