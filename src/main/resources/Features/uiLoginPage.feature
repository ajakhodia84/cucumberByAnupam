@Reg_Ui
Feature: Verify UI of login page

  Background: User starts from Gasket's home page
    Given User is at "gasket" brand's "login" page of "us" locale
    And I wait for page load

  Scenario: Verifying, User sees correct UI design at login page
    And I wait for page load
    Then I check "font-size" of "loginpage.header.title" is "48px"
    Then I check "font-size" of "loginpage.username.placeholder" is "16px"
    Then I check "font-size" of "loginpage.password.placeholder" is "16px"
    Then I check "font-size" of "loginpage.login.button" is "24px"
    Then I check "font-size" of "loginpage.forget.password.link" is "18px"
    Then I check "font-size" of "loginpage.needhelp.div" is "14px"
    Then I check "font-size" of "loginpage.privacy.policy.link" is "14px"
    Then I check "font-size" of "loginpage.copyright.disclaimer" is "14px"
    Then i check "font-family" of "loginpage.header.title" is "GoreGustan"
    Then i check "font-family" of "loginpage.username.input" is "GoreGustan"
    Then i check "font-family" of "loginpage.password.input" is "GoreGustan"
    Then i check "font-family" of "loginpage.login.button" is "GoreGustan"
    Then i check "font-family" of "loginpage.forget.password.link" is "GoreGustan"
    Then i check "font-family" of "loginpage.needhelp.div" is "GoreGustan"
    Then i check "font-family" of "loginpage.privacy.policy.link" is "GoreGustan"
    Then i check "font-family" of "loginpage.copyright.disclaimer" is "GoreGustan"
    Then i check "line-height" of "loginpage.header.title" is "60px"
    Then i check "line-height" of "loginpage.needhelp.div" is "21px"
    Then i check "line-height" of "loginpage.privacy.policy.link" is "21px"
    Then i check "line-height" of "loginpage.copyright.disclaimer" is "21px"
    Then I check the size of "loginpage.logo" is "192px" "77px" expected
    Then I check the size of "loginpage.banner.image" is "667px" "613px" expected
    Then I check the size of "loginpage.header.title" is "380px" "180px" expected
    Then I check the size of "loginpage.username.input" is "277px" "56px" expected
    Then I check the size of "loginpage.password.input" is "277px" "56px" expected
    Then I check the size of "loginpage.login.button" is "277px" "56px" expected
    Then I check the size of "loginpage.forget.password.link" is "147.531px" "28.5px" expected
    Then I check the size of "loginpage.needhelp.div" is "380px" "63px" expected
    Then I check the size of "loginpage.privacy.policy.link" is "fit-content" "" expected
    Then I check the size of "loginpage.copyright.disclaimer" is "380px" "21px" expected
