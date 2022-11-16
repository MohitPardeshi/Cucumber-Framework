package util;

import driverFactory.DriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public static String getElementType(WebElement element){
        String tagName=element.getTagName();
        System.out.println(tagName);
        switch (tagName){
            case "input":
                String elementType=element.getAttribute("type");
                return getElementInputType(elementType);
        }
        return tagName;
    }

    private static String getElementInputType(String elementType) {
        switch (elementType){
            case "text" :
            case "password" :
                return "text";
            case "submit": return "button";
            default:
                return "button";
        }
    }

    public static WebElement getElement(By by){
       return DriverUtil.getDriver().findElement(by);
    }
}
