package driverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import util.GlobalConfigProperties;

import java.time.Duration;

public class DriverUtil {
    public WebDriver driver;
    public static ThreadLocal<WebDriver> driverThreadLocal;


    public DriverUtil(){
        driverThreadLocal=new ThreadLocal<WebDriver>();
    }

    /**
     * This method initiate the browser & returns the browser
     *
     * @param browser
     * @return
     */
    public WebDriver initializeDriver(String browser){
        switch(browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driverThreadLocal.set(new ChromeDriver());
                break;
            case "safari":
                driverThreadLocal.set(new SafariDriver());
                break;
            default:
                System.out.println("Incorrect Browser so default browser is chrome");
                WebDriverManager.chromedriver().setup();
                driverThreadLocal.set(new ChromeDriver());
        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(
                Duration.ofSeconds(Long.parseLong(GlobalConfigProperties.getProperty("implicit_timeout"))));
        return getDriver();
    }

    /**
     * This method is used to get Driver from Thread Local
     * @return
     */
    public static synchronized WebDriver getDriver(){
        return driverThreadLocal.get();
    }
}
