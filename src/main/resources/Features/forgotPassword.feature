@Reg_ForgotPasswordPage
Feature: Verify functionality and content displayed on Forgot Password page

  Background: User starts from Gasket's Login page
    Given User is at "gasket" brand's "login" page of "us" locale
    And I wait for page load
    Then I expect "loginpage.forget.password.link" redirects to "https://val-wlgore.cs172.force.com/Gasket/s/login/ForgotPassword"
    And I click on "loginpage.forget.password.link"
    And I wait for page load

  Scenario: Verifying, User sees correct content at login page
    Then I expect "banner image" is displayed for "forgotPasswordPage.banner.image" element locator
    Then I expect "logo image" is displayed for "forgotPasswordPage.logo" element locator
    Then I expect text displayed for "forgotPasswordPage.header.title" is "Forgot your password ? "
    Then I expect text displayed for "forgotPasswordPage.header.subtitle" is "Enter the email address associated with your account, and we’ll email you a link to reset your password."
    Then I expect text displayed for "forgotPasswordPage.sendResendLink.button" is "Send Reset Link"
    Then I expect text displayed for "forgotPasswordPage.privacyPolicy.link" is "Privacy Policy"
    Then I expect text displayed for "forgotPasswordPage.disclaimer" is "©2009-2021 W. L. Gore & Associates, Inc."
    Then I expect text displayed for "forgotPasswordPage.footer.ContactUsPhone" is "1-800-455-2791"

  Scenario: Verifying, Privacy Policy Link opens on seperate Tab and redirected to proper page
    And I wait for page load
    Then I verify link "forgotPasswordPage.privacyPolicy.link" opens in new tab and has title "Privacy Notice | Gore"

  Scenario: Verifying, User sees correct error states on Forgot Password page
    Then I click on "forgotPasswordPage.sendResendLink.button"
    And I wait for page load
    Then I verify "blank input field" Error states text is displayed as "Blank Email." for "forgotPasswordPage.blank.username.error.message"

  Scenario: Verifying, User is able to send request for password change successfully and verify correct content of confirmation screen
    Then I enter "goretest3@outlook.com" in "forgotPasswordPage.username"
    Then I click on "forgotPasswordPage.sendResendLink.button"
    And I wait for page load
    Then I expect "banner image" is displayed for "forgotPasswordEmailSentPage.banner.image" element locator
    Then I expect "logo image" is displayed for "forgotPasswordEmailSentPage.logo" element locator
    Then I expect text displayed for "forgotPasswordEmailSentPage.header.title" is "Thank you!"
    Then I expect text displayed for "forgotPasswordEmailSentPage.header.subtitle" is "Please check your email for a link to reset your password. The link will expire in 24 hours."
    Then I expect text displayed for "forgotPasswordEmailSentPage.privacyPolicy.link" is "Privacy Policy"
    Then I expect text displayed for "forgotPasswordEmailSentPage.disclaimer" is "©2009-2021 W. L. Gore & Associates, Inc."
    Then I expect text displayed for "forgotPasswordEmailSentPage.footer.ContactUsPhone" is "1-800-455-2791"
