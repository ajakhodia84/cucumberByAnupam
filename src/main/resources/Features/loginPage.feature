@Reg_loginPage
Feature: Verify functionality and content displayed login page

  Background: User starts from Gasket's home page
    Given User is at "gasket" brand's "login" page of "us" locale
    And I wait for page load

  Scenario: Validating text shown on the page
    Then I expect text displayed for "loginpage.header.title" is "WELCOME TO THE GASKETS STORE"
    Then I expect text displayed for "loginpage.login.button" is "Sign In"
    Then I expect text displayed for "loginpage.forget.password.link" is "Forgot Password?"
    Then I expect text displayed for "loginpage.footer.ContactUsPhone" is "1-800-455-2791"

  Scenario: Verifying, User sees correct error states on login page
    Then I verify blank input fields Error states
    Then I verify invalid username or password error states for user "jfloam@wlgore.com.slvtest1.val"
    Then I verify account locked error state for user "jfloam@wlgore.com.gldprd2.val"

  Scenario: Verifying, User sees correct links redirection on login page
    And I wait for page load
    Then I verify link "loginpage.privacy.policy.link" opens in new tab and has title "Privacy Notice | Gore"    
    Then I expect "loginpage.forget.password.link" redirects to "https://val-wlgore.cs172.force.com/Gasket/s/login/ForgotPassword"

  Scenario: Verifying, User is able to login successfully
    And I wait for page load
    #please keep different user name here than provided in scenario "Verifying, User sees correct error states on login page"
    Then I verify user "4ee7b67d.wlgore.onmicrosoft.com@amer.team.val" with password "Gore@1234" is able to login
