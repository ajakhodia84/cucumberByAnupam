@Reg_AccountDetails
Feature: Verify functionality and content displayed on Account Details page

  Background: User starts from Gasket's Home page
    Given User is at "gasket" brand's "login" page of "us" locale
    And I wait for page load
    Then I login using UserID "4ee7b67d.wlgore.onmicrosoft.com@amer.team.val" with password "Gore@1234" and lands on home page
    And I wait for page load


  Scenario: Validating contents shown on the account details page
    Then I navigate to "account details page" accessed by url "gasket.us.accountDetails"
    And I wait for page load
    Then I expect text displayed for "accountDetail.title" is "Account Details"
    Then I expect text displayed for "accountDetail.description" is "To edit any of your information please contact "
    Then I expect text displayed for "accountDetail.customerServiceCTA" is "Customer Service"
    Then I expect text displayed for "accountDetail.contactInformation.title" is "Contact Information:"
    Then I expect text displayed for "accountDetail.contactInformation.userName" is "Automation Test Gasket US"
    Then I expect text displayed for "accountDetail.contactInformation.email" is "imran.ansari@publicissapient.com"
    Then I expect text displayed for "accountDetail.contactInformation.phoneNumber" is "+13333333333"
    Then I expect text displayed for "accountDetail.contactInformation.changeMyPasswordCTA" is "Change My Password"
    Then I expect text displayed for "accountDetail.companyInformation.title" is "Company Information:"
    Then I expect text displayed for "accountDetail.companyInformation.address" is "Lynn Wilson\nDanseal U/S\nKirstinehöj 38 C\nNew York, US-2770\nUnited States of America"
    Then I expect text displayed for "accountDetail.shippingAddress.title" is "Shipping Addresses:"
    Then I expect text displayed for "accountDetail.shippingAddress.addressOnFile" is "Addresses on File"
    Then I expect text displayed for "accountDetail.shippingAddress.viewShippingAddressCTA" is "View Shipping Addresses"
    Then I expect text displayed for "accountDetail.orders.title" is "Orders:"
    Then I expect text displayed for "accountDetail.openOrders" is "Open Orders"
    Then I expect text displayed for "accountDetail.totalOrders" is "Total Orders"
    Then I expect text displayed for "accountDetail.viewOrderHistory" is "View Order History"

