$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Sample.feature");
formatter.feature({
  "id": "to-test-my-cucumber-test-is-running-i-want-to-run-a-sample-feature-file",
  "tags": [
    {
      "name": "@RitaTest",
      "line": 1
    }
  ],
  "description": "",
  "name": "To test my cucumber test is running I want to run a sample feature file",
  "keyword": "Feature",
  "line": 2
});
formatter.scenario({
  "id": "to-test-my-cucumber-test-is-running-i-want-to-run-a-sample-feature-file;cucumber-setup",
  "description": "",
  "name": "cucumber setup",
  "keyword": "Scenario",
  "line": 3,
  "type": "scenario"
});
formatter.step({
  "name": "sample feature file is ready",
  "keyword": "Given ",
  "line": 4
});
formatter.step({
  "name": "I run the feature file",
  "keyword": "When ",
  "line": 5
});
formatter.step({
  "name": "run should be successful.",
  "keyword": "Then ",
  "line": 6
});
formatter.match({
  "location": "StepDefinition.sample_feature_file_is_ready()"
});
formatter.result({
  "duration": 144613018,
  "status": "passed"
});
formatter.match({
  "location": "StepDefinition.i_run_the_feature_file()"
});
formatter.result({
  "duration": 122222,
  "status": "passed"
});
formatter.match({
  "location": "StepDefinition.run_should_be_successful()"
});
formatter.result({
  "duration": 113778,
  "status": "passed"
});
});