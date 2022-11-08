package hooks;

import driverFactory.DriverUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import util.ExplicitWait;
import util.GlobalConfigProperties;
import util.PageObjectGenerator;
import util.TestDataHandler;

import java.io.IOException;

import static util.ExplicitWait.initializeWait;

public class Hooks {
    private DriverUtil driverUtil;
    private WebDriver driver;
    @Before()
    public void setUpHook() throws IOException {
        System.out.println("Inside Before Hook");
        //Load property files
        GlobalConfigProperties.loadProperties();
        //Load the Page Objects
        PageObjectGenerator.initialize();
        //Initiating the browser
        String browserName=GlobalConfigProperties.globalConfigProperties.getProperty("browser");
        driverUtil=new DriverUtil();
        driver=driverUtil.initializeDriver(browserName);
        //initiate the Explicit wait
        initializeWait();
        //initiating the Test Data map
        TestDataHandler.initiateTestDataMap();
    }

    @After
    public void tearDown(){
      //  driver.close();
      //  driver.quit();
    }
}
