@Login
Feature: Login to the application

  Scenario: Initialize the browser & login
    Given I navigate to TutorialNinja
    When I click on homePage.myAccount
    And I click on homePage.login
    And I enter sctqatest@grr.la into login.emailAddress
    And I enter Spring123$ into login.password
    And I click on login.loginButton
