package stepDefinations;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import util.Constants;
import util.YAMLHelper;

import java.io.File;
import java.io.IOException;

public class ReusableStepDefinations {
    WebDriver driver;
    @Given("^I navigate to (.+)$")
    public void i_navigate_to_tutorial_ninja(String applicationName) throws IOException {
        System.out.println("Application Name : "+applicationName);
        String url= YAMLHelper.readYML(Constants.URLS_FILE).get(applicationName);
        System.out.println("The URL : "+url);
    }

    @Then("^I take screenshot$")
    public void i_take_screenshot() {
       File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

    }
}
