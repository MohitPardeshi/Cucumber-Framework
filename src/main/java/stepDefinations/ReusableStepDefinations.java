package stepDefinations;

import customeExceptions.InvalidDataException;
import driverFactory.DriverUtil;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import util.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class ReusableStepDefinations {
    public static Scenario scenario;
    @Before
    public void before(Scenario scenario){
       this.scenario=scenario;
    }
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
       byte[] bytes=((TakesScreenshot)DriverUtil.getDriver()).getScreenshotAs(OutputType.BYTES);
       scenario.attach(bytes,"image/png","Image");
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
    @Then("^I click on element (.+)$")
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

    @Given("I click on all links")
    public void i_click_on_all_links_on_page() {
        SoftAssert softAssert=new SoftAssert();
        String parentWindow=DriverUtil.getDriver().getWindowHandle();
       List<WebElement> allListElements=DriverUtil.getDriver().findElements(By.tagName("a"));
       for(WebElement e: allListElements){
                if(ReusableMethods.isInteractable(e)) {
                    System.out.println("Element Get Text : "+e.getText());
                    Actions action = new Actions(DriverUtil.getDriver());
                    action.moveToElement(e).keyDown(Keys.COMMAND).click(e).build().perform();
                    Set<String> windows=DriverUtil.getDriver().getWindowHandles();
                    if(windows.size()>1) {
                        String[] win = windows.toArray(new String[windows.size()]);
                        DriverUtil.getDriver().switchTo().window(win[1]);
                        System.out.println(DriverUtil.getDriver().getTitle());
                        DriverUtil.getDriver().close();
                        DriverUtil.getDriver().switchTo().window(parentWindow);
                    }else{
                        softAssert.assertTrue(false,"New tab is not launched for "+e.getText());
                    }
                }else{
                   // throw new ElementNotInteractableException("Element is not interactable");
                    softAssert.assertTrue(false,"Element is not interactable "+e.getText());
                }
       }
       softAssert.assertAll();
    }

}
