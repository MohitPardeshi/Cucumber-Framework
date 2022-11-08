package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import customeExceptions.InvalidDataException;
import driverFactory.DriverUtil;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestDataHandler {
    public static LinkedHashMap<String,Object> testDataMap;

    /**
     * Reading all the test data files from the input folder
     */
    public static void initiateTestDataMap() throws IOException {
       testDataMap=new LinkedHashMap<>();
       String inputDataPath=Constants.INPUT_DATA;
       File directory=new File(inputDataPath);
       File[] files=directory.listFiles((dir1,name)-> name.endsWith(".json"));
       ObjectMapper objectMapper=new ObjectMapper();
       LinkedHashMap pageData;
       if(files!=null){
           for(File file: files){
               FileInputStream fileInputStream=new FileInputStream(file.toPath().toFile());
               pageData=objectMapper.readValue(fileInputStream,LinkedHashMap.class);
               String fileName=file.getName();
               testDataMap.put(FilenameUtils.removeExtension(fileName).toLowerCase(),pageData);
           }
       }else
           throw new FileNotFoundException("JSON data files are not present : "+inputDataPath+" Folder");
        System.out.println(testDataMap);
    }

    public static void fillTestData(String key, String pageName) throws InvalidDataException {
        LinkedHashMap<String,Object> testData=TestDataHandler.traverseToTestMap(pageName,key,testDataMap);
        System.out.println(testData);
        injectTestData(testData,pageName);
    }

    private static LinkedHashMap<String, Object> traverseToTestMap(String pageName,String key, LinkedHashMap<String, Object> searchMap) throws InvalidDataException {
        LinkedHashMap<String, Object> testMap = null;
        for(Map.Entry entry: searchMap.entrySet()){
            if(entry.getKey().toString().equalsIgnoreCase(pageName)){
                LinkedHashMap<String,Object> pageMap= (LinkedHashMap<String, Object>) entry.getValue();
                for(Map.Entry entry1:pageMap.entrySet()){
                    if(entry1.getKey().toString().equalsIgnoreCase(key)){
                        testMap= (LinkedHashMap<String, Object>) entry1.getValue();
                    }
                }
            }
        }
        if(testMap==null)
            throw new InvalidDataException("Page Name : "+pageName+" Data Key : "+key+" Not Found");
        return testMap;
    }

    public static void injectTestData(LinkedHashMap<String,Object> inputMap,String pageName){
        //Traversing the Array & getting element Name first
        StringBuilder element;
        for(Map.Entry entry:inputMap.entrySet()){
            String dataValue= (String) entry.getValue();
            element=new StringBuilder(pageName+"."+entry.getKey());
            fillElement(String.valueOf(element),dataValue);
        }
    }

    public static void fillElement(String element,String dataValue){
        ElementObject elementObject=new ElementObject(element);
        By by= GetElementBy.getElementBy(elementObject.getAccessType(),elementObject.getAccessName());
        WebElement webElement= GetElementBy.getElement(by);
        String tagName=GetElementBy.getElementType(webElement);
        injectData(tagName,webElement,dataValue);
        //WebElement ele= ExplicitWait.getWait().until(ExpectedConditions.elementToBeClickable(by));
    }
    
    public static String getStoredTitle(String pageName){
        String title = null;
        for(Map.Entry entry: testDataMap.entrySet()){
            if(entry.getKey().toString().equalsIgnoreCase(pageName)){
                LinkedHashMap<String,Object> pageMap= (LinkedHashMap<String, Object>) entry.getValue();
                try {
                    title= (String) pageMap.get("TITLE");
                }
                catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Title not found");
                }
            }
        }
        return title;
    }

    public static void injectData(String tagName,WebElement element,String dataValue){
        switch (tagName){
            case "text" :
                element.sendKeys(dataValue);
                break;
            case "button":
                element.click();
                break;
            default:
                element.click();
        }
    }
}
