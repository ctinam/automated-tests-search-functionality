package Runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "StepDefinitions",
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/HTMLReport.html"
        }
)
public class TestRunner {}
