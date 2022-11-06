package util;

import driverFactory.DriverUtil;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWait {
    static WebDriverWait wait;

    public static void initializeWait(){
        String waitTime=GlobalConfigProperties.globalConfigProperties.getProperty("explicit_wait_timeout");
        wait=new WebDriverWait(DriverUtil.getDriver(), Duration.ofSeconds(Long.parseLong(waitTime)));
    }

    public static WebDriverWait getWait(){
        return wait;
    }
}
