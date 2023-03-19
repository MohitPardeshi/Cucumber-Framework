@Login
Feature: Login to the application

  @SimpleLogin
  Scenario: Initialize the browser & login
    Given I navigate to TutorialNinja
    When I click on element homePage.myAccount
    And I click on element homePage.login
    And I enter sctqatest@grr.la into login.emailAddress
    And I enter Spring123$ into login.password
    And I click on element login.loginButton

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

  @LoginLinkTest
  Scenario: Login through Test Data
    Given I navigate to TutorialNinja
    And I land on homePage
    And I click on all links

  @CorrectPlaceHolder
  Scenario: Login through Test Data
    Given I navigate to TutorialNinja
    And I land on homePage
    And I verify below fields have correct place holders
      |homePage.search|Search|
    When I fill NAVIGATE_TO_LOGIN data from homePage onto the page
    And I land on login
    And I verify below fields have correct place holders
    |login.emailAddress|E-Mail Address|
    |login.password    |Password|

  @BrokenLinksOfPages
  Scenario: Login through Test Data
    Given I navigate to TutorialNinja
    When I land on homePage
    Then I verify all links

  @Navigation
  Scenario: Login through Test Data
    Given I navigate to TutorialNinja
    When I land on homePage
    And I fill NAVIGATE_TO_LOGIN data from homePage onto the page
    Then I navigate back
    And I navigate forward
    And I refresh page