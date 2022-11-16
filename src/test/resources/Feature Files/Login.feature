@Login
Feature: Login to the application

  @SimpleLogin
  Scenario: Initialize the browser & login
    Given I navigate to TutorialNinja
    When I click on homePage.myAccount
    And I click on homePage.login
    And I enter sctqatest@grr.la into login.emailAddress
    And I enter Spring123$ into login.password
    And I click on login.loginButton

  @parameter
  Scenario: Login through Test Data
    Given I navigate to TutorialNinja
    And I land on homePage
    And I take screenshot
    When I fill NAVIGATE_TO_LOGIN data from homePage onto the page
    And I land on login
    And I fill QA_TEST data from login onto the page
    And I land on MyAccount
    And I take screenshot

  @Parameterized
 Scenario Outline: Login through Test Data
    Given I navigate to TutorialNinja
    And I land on homePage
    When I fill NAVIGATE_TO_LOGIN data from homePage onto the page
    And I land on login
    And I fill <Login_Data> data from login onto the page

    Examples:
    |Login_Data         |
    | QA_TEST |
    | DUMMY_TEST        |