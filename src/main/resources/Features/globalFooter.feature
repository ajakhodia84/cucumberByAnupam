@Reg_globalFooter
Feature: Verify functioning of Global Header

  Background: User starts from Gasket's home page
    Given User is at "gasket" brand's "login" page of "us" locale
    And I wait for page load
    Then I verify user "4ee7b67d.wlgore.onmicrosoft.com@amer.team.val" with password "Gore@1234" is able to login
    And I wait for page load

  Scenario: Validating contents shown in the footer
    Then I proceed to test "contents shown on footer"
    Then I expect text displayed for "homepage.footer.customerService.label" is "Contact Customer Service"
    Then I expect text displayed for "homepage.footer.service.number" is "1-800-455-2791"
    Then I expect text displayed for "homepage.footer.supportTeam.label" is "Email Our Support Team"
    Then I expect text displayed for "homepage.footer.mailto.link" is "gaskets@wlgore.com"
    Then I expect text displayed for "homepage.footer.faq" is "Frequently Asked Questions"
    Then I expect text displayed for "homepage.footer.privacyNotice" is "Privacy Notice"
    Then I expect text displayed for "homepage.footer.legalNotice" is "Legal Notice"
    Then I expect text displayed for "homepage.footer.tou" is "Terms of Use"
    Then I expect text displayed for "homepage.footer.termsConditions" is "Terms & Conditions"
    Then I expect text displayed for "homepage.footer.legalDeclaration" is "GORE, Together, improving life and designs are trademarks of W. L. Gore & Associates ©2009-2021 W. L. Gore & Associates, Inc."
    Then I expect text displayed for "homepage.footer.logo.tagLine" is "Together, improving life"

  Scenario: Verifying functioning of links present in footer
    Then I proceed to test "footer links redirection"
    Then I click on "homepage.footer.faq" and verify url is "https://val-wlgore.cs172.force.com/Gasket/s/faq"
    Then I verify link "homepage.footer.privacyNotice" opens in new tab and has title "Privacy Notice | Gore"
    Then I click on "homepage.footer.legalNotice" and verify url is "https://val-wlgore.cs172.force.com/Gasket/s/legalnotice"    
    Then I click on "homepage.footer.termsConditions" and verify url is "https://val-wlgore.cs172.force.com/Gasket/s/termsandconditions"
    Then I verify image "homepage.footer.logo" is displayed on the page
    Then I proceed to test "gasket@wlgore.com is a mailto Link"
    And I expect "homepage.footer.mailto.link" is a mailto link "mailto:gaskets@wlgore.com"
    Then I proceed to test "1-800-455-2791 is a Tel Link"
    And i expect "homepage.footer.service.number" is a Tel link "tel:18004552791"
