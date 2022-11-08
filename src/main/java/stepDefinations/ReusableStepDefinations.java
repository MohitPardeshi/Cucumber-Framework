package stepDefinations;

import customeExceptions.InvalidDataException;
import driverFactory.DriverUtil;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import util.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

public class ReusableStepDefinations {
    WebDriver driver;

    @Given("^I navigate to (.+)$")
    public void i_navigate_to_tutorial_ninja(String applicationName) throws IOException {
        System.out.println("Application Name : "+applicationName);
        String url= YAMLHelper.readYML(Constants.URLS_FILE).get(applicationName);
        System.out.println("The URL : "+url);
        DriverUtil.getDriver().get(url);
    }

    @Then("^I take screenshot$")
    public void i_take_screenshot() {
       File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

    }

    @Then("^I enter (.+) into (.+)$")
    public void i_enter_into_element(String string,String element) {
      // System.out.println("Value to be entered : "+string);
      // System.out.println("Element : "+element);
       ElementObject elementObject=new ElementObject(element);
       By by= GetElementBy.getElementBy(elementObject.getAccessType(),elementObject.getAccessName());
       WebElement ele= ExplicitWait.getWait().until(ExpectedConditions.elementToBeClickable(by));
       ele.sendKeys(string);
    }
    @Then("^I click on (.+)$")
    public void i_click_on_elements(String element) {
        ElementObject elementObject=new ElementObject(element);
        By by= GetElementBy.getElementBy(elementObject.getAccessType(),elementObject.getAccessName());
        WebElement ele= ExplicitWait.getWait().until(ExpectedConditions.elementToBeClickable(by));
        ele.click();
    }

    @When("^I fill (.+) data from (.+) onto the page$")
    public void i_fill_data_from_onto_the_page(String key,String pageName) throws InvalidDataException {
        TestDataHandler.fillTestData(key,pageName);
    }

    @Given("^I land on (.+)$")
    public void i_land_on(String pageName) {
        String expectedTitle=TestDataHandler.getStoredTitle(pageName);
        String actualTitle=DriverUtil.getDriver().getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
    }
}
