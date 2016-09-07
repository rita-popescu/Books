
Feature: To write tests using cucumber
  Background:
    Given I receive a directory of books
    Scenario: To get the title of a book with a certain ISBN
      When I give the ISBN "123"
      Then I receive the title "("Robert C. Martin"), "agile software development1", "123", "Self-Help")"