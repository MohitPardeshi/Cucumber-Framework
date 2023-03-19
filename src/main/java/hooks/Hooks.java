package hooks;

import driverFactory.DriverUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import util.*;

import java.io.IOException;
import java.sql.SQLException;

import static util.ExplicitWait.initializeWait;

public class Hooks {
    private DriverUtil driverUtil;
    @Before()
    public void setUpHook() throws IOException, SQLException {
        System.out.println("Inside Before Hook");
        //Load property files
        GlobalConfigProperties.loadProperties();
        //Load the Page Objects
        PageObjectGenerator.initialize();
        //Initiating the browser
        String browserName= String.valueOf(GlobalConfigProperties.getProperty("browser"));
        driverUtil=new DriverUtil();
        driverUtil.initializeDriver(browserName);
        //initiate the Explicit wait
        initializeWait();
        //initiating the Test Data map
        TestDataHandler.initiateTestDataMap();
    }

    @After
    public void tearDown() throws SQLException {
        DriverUtil.closeBrowser();
    }
}
