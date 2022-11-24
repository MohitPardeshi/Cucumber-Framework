import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = "@LoginLinkTest",
        features = "src/test/resources/Feature Files",
        glue={"stepDefinations","hooks"},
        //dryRun = true,
        plugin = {"pretty","html:target/reports/cucumberHtmlReports.html", "rerun:target/rerun.txt"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
