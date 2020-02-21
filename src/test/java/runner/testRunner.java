package runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features= {"src/test/java/features/AScenario.feature"},
glue={"seleniumGlueCode"},
plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}, 
monochrome = true)

public class testRunner {
	
	@AfterClass
    public static void writeExtentReport() { 
        Reporter.loadXMLConfig(new File("config/report.xml"));
}
}

//"src/test/java/features/AScenario.feature","src/test/java/features/BScenario.feature","src/test/java/features/CScenario.feature", "src/test/java/features/DScenario.feature","src/test/java/features/EScenario.feature","src/test/java/features/FScenario.feature","src/test/java/features/GScenario.feature","src/test/java/features/HScenario.feature"