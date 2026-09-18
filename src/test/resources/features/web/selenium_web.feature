# language: en
Feature: Selenium.dev website validation

  Background:
    Given the user opens the selenium website

  Scenario: TC1 - Home page loads correctly and shows expected title
    Then the page title should be "Selenium"
    And the home page should display the main heading

  Scenario: TC2 - Navigation to Documentation works correctly
    When the user clicks on "Documentation" in the navigation menu
    Then the user should be on the documentation page
    And the documentation page should display its main content

  Scenario: TC3 - Search functionality returns relevant results
    When the user performs a search for "WebDriver"
    Then the search results page should be displayed
    And the results should contain "WebDriver"
