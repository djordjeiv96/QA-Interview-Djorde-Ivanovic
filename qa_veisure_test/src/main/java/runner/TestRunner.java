package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"C:/QA-Interview-Djorde-Ivanovic/qa_veisure_test/src/test/resources/api/features", "C:/QA-Interview-Djorde-Ivanovic/qa_veisure_test/src/test/resources/ui/features" },
        glue = {"stepDefinitions"},
        plugin = {"pretty","io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"}

)
public class TestRunner extends AbstractTestNGCucumberTests {
}
