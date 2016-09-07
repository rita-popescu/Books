package com.example;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.example.Example;

import static org.junit.Assert.assertEquals;

/**
 * Created by RCPopescu on 8/24/2016.
 */
public class ExampleSteps {
    private Example _target;
    private String _actualResult;
    @Given("^I am officiating a FizzBuzz game$")
    public void i_am_officiating_a_FizzBuzz_game() throws Throwable {
        _target = new Example();
    }

    @When("^the number (\\d+) is played$")
    public void the_number_is_played(int playedNumber) throws Throwable {
        _actualResult = _target.checkPlay(playedNumber);
    }

    @Then("^I should be told the correct answer is \"([^\"]*)\"$")
    public void i_should_be_told_the_correct_answer_is(String expectedResult) throws Throwable {
        assertEquals(expectedResult, _actualResult);
    }

}
