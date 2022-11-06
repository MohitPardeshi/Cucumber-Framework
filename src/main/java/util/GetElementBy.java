package util;

import org.openqa.selenium.By;

public class GetElementBy {

    public static By getElementBy(String accessType, String accessName){
        By by = null;
        switch(accessType){
            case "xpath" :
            by=By.xpath(accessName);
            break;
            case "css" :
                by=By.cssSelector(accessName);
                break;
            case "name" :
                by=By.name(accessName);
                break;
            case "id" :
                by=By.id(accessName);
                break;
            case "className" :
                by=By.className(accessName);
                break;
            case "tagName" :
                by=By.tagName(accessName);
                break;
            case "linktext" :
                by=By.linkText(accessName);
                break;
            case "partialLinkText" :
                by=By.partialLinkText(accessName);
                break;
        }
        return by;
    }

}
