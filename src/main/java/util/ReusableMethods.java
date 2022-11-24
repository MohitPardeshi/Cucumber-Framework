package util;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReusableMethods {
    public static boolean isInteractable(WebElement e){
        try {
            ExplicitWait.getWait().until(ExpectedConditions.elementToBeClickable(e));
        }catch (ElementNotInteractableException exception){
            return false;
        }catch (Exception exception){
            return false;
        }
        return true;
    }
}
