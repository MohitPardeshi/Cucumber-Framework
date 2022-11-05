package util;

import java.io.IOException;
import java.util.Objects;
import java.util.TreeMap;

public class PageObjectGenerator {
    public static TreeMap<String, Object> pageObject=null;

    public static void initialize() throws IOException {
        String pageObjectPath=Constants.PAGE_OBJECT;
        pageObject=JSONHelper.readJSON(pageObjectPath);
        System.out.println(pageObject);
    }

}
