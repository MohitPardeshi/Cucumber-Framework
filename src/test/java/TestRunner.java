import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = "@parameter",
        features = "src/test/resources/Feature Files",
        glue={"stepDefinations","hooks"},
        //dryRun = true,
        plugin = {"pretty", "rerun:target/rerun.txt"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
