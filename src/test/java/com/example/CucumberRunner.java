package com.example; /**
 * Created by RCPopescu on 8/24/2016.
 */
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
public class CucumberRunner {

}
