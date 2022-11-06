package util;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class PageObjectGenerator {
    public static TreeMap<String, Object> pageObject=null;

    public static void initialize() throws IOException {
        String pageObjectPath=Constants.PAGE_OBJECT;
        pageObject=JSONHelper.readJSON(pageObjectPath);
        System.out.println(pageObject);
    }

    public static String[] getAccessNameAccessType(String element){
        String[] accessTypeAccessName=null;
        String[] split=element.split("\\.");
        String pageName=split[0];
        String elementName=split[1];
        for(Map.Entry entry: pageObject.entrySet()){
            if(entry.getKey().toString().equalsIgnoreCase(pageName)) {
                LinkedHashMap<String, Object> pages = (LinkedHashMap<String, Object>) entry.getValue();
                //System.out.println("pages : "+pages);
                for(Map.Entry entry1: pages.entrySet()){
                    //System.out.println(entry1.getKey().toString());
                    //System.out.println("The value of elementName : "+elementName);
                    if(entry1.getKey().toString().equalsIgnoreCase(elementName)){
                        LinkedHashMap<String,Object> elementValues= (LinkedHashMap<String, Object>) entry1.getValue();
                        //System.out.println("Element Values : "+elementValues);
                        accessTypeAccessName=new String[2];
                        accessTypeAccessName[0]=elementValues.get("accessType").toString();
                        accessTypeAccessName[1]=elementValues.get("accessName").toString();
                    }
                }
            }
        }
        return accessTypeAccessName;
    }

}
