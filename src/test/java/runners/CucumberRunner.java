package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"stepDefinitions"}      ,
        monochrome = true,
        tags = "@Test",
        plugin = {
                "pretty",
                "html:target/test-reports.html"
        }
)

public class CucumberRunner {
}
