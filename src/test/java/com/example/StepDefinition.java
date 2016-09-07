package com.example;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by RCPopescu on 8/24/2016.
 */
public class StepDefinition {
    @Given("^sample feature file is ready$")
    public void sample_feature_file_is_ready() throws Throwable {
        System.out.println("Given statement executed successfully");
    }

    @When("^I run the feature file$")
    public void i_run_the_feature_file() throws Throwable {
        System.out.println("When statement executed successfully");
    }

    @Then("^run should be successful\\.$")
    public void run_should_be_successful() throws Throwable {
        System.out.println("Then statement executed successfully");
    }
}
