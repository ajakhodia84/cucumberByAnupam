@Reg_homePage @Regression
Feature: Verifying functioning of home page

  Background: User starts from Gasket's home page
    Given User is at "gasket" brand's "login" page of "us" locale
    And I wait for page load
    Then I verify user "4ee7b67d.wlgore.onmicrosoft.com@amer.team.val" with password "Gore@1234" is able to login
    And I wait for page load
    Then I expect User navigated to "Home" Page

  Scenario: Validating content displayed on the home page
  Then I expect text displayed for "homepage.siteMasthead.title" is "WELCOME TO GORE® GASKETS ONLINE STORE"
  Then I expect text displayed for "homepage.siteMasthead.subtitle" is "For Authorized Distributors of GORE® Industrial Gaskets"
    Then I expect text displayed for "homepage.siteMasthead.description" is "For urgent next day orders please contact customer service."
    Then I expect text displayed for "homepage.header.productCategory.one" is "Joint Sealants"
    Then I expect text displayed for "homepage.header.productCategory.two" is "Cut Gaskets"
    Then I expect text displayed for "homepage.header.productCategory.three" is "Sheet Gasketing"
    Then I expect text displayed for "homepage.header.productCategory.four" is "Gasket Tape"
    #Then I expect text displayed for "homepage.header.productCategory.more" is "More"
    Then I expect text displayed for "homepage.siteMasthead.title" is "WELCOME TO GORE® GASKETS ONLINE STORE"
    Then I expect text displayed for "homepage.siteMasthead.subtitle" is "For Authorized Distributors of GORE® Industrial Gaskets"
    Then I expect text displayed for "homepage.siteMasthead.description" is "For urgent next day orders please contact customer service."
    Then I expect text displayed for "homepage.siteMasthead.categoryCard.one.title" is "Joint Sealant"
    Then I expect text displayed for "homepage.siteMasthead.categoryCard.two.title" is "Cut Gaskets"
    Then I expect text displayed for "homepage.siteMasthead.categoryCard.three.title" is "Sheet Gasketing"
    Then I expect text displayed for "homepage.siteMasthead.categoryCard.four.title" is "Gasket Tape"
    Then I expect text displayed for "homepage.orderHistory.title" is "Order History"
    Then I expect text displayed for "homepage.orderHistory.viewFullOrderHistory.link" is "View Full Order History"
    Then I expect text displayed for "homepage.orderHistory.date.label" is "Order Placed"
    Then I expect text displayed for "homepage.orderHistory.poNumber.label" is "PO Number"
    Then I expect text displayed for "homepage.orderHistory.total.label" is "Total"
    Then I expect text displayed for "homepage.orderHistory.status.label" is "Status"
    Then I expect text displayed for "homepage.orderHistory.orderDetailsLink.label" is "Order Details"
    Then I expect text displayed for "homepage.featuredProduct.title" is "Featured Parts"
    Then I expect text displayed for "homepage.quickOrder.title" is "Quick Order"
     Then I expect text displayed for "homepage.quickOrder.partNumber.placeHolder" is "Part Number"
     Then I expect text displayed for "homepage.quickOrder.quantity.placeHolder" is "Quantity"
    Then I expect text displayed for "homepage.quickOrder.addLine.label" is "Add 3 Lines"
    Then I expect text displayed for "homepage.footer.customerService.label" is "Contact Customer  Service"
    Then I expect text displayed for "homepage.footer.service.number" is "1-800-455-2791"
    Then I expect text displayed for "homepage.footer.supportTeam.label" is "Email Our Support Team"
    Then I expect text displayed for "homepage.footer.mailto.link" is "gaskets@wlgore.com"
    Then I expect text displayed for "homepage.footer.faq.label" is "Frequently Asked Questions"
    Then I expect text displayed for "homepage.footer.privacyNotice.label" is "Privacy Notice"
    Then I expect text displayed for "homepage.footer.legalNotice.label" is "Legal Notice"
    Then I expect text displayed for "homepage.footer.tou.label" is "Terms of Use"
    Then I expect text displayed for "homepage.footer.termsConditions.label" is "Terms & Conditions"
    Then I expect text displayed for "homepage.footer.cookieNotice.label" is "Cookie Notice"
    Then I expect text displayed for "homepage.footer.legalDeclaration.label" is "GORE, Together, improving and designs are trademarks of W. L. Gore & Associates ©2009-2021 W. L. Gore & Associates, Inc"
  
  Scenario Outline: Validating Featured Product content and functionality
    Then I expect text displayed for "homepage.featuredProduct.title" is "Featured Parts"
    Then I expect total product displayed are equal to total product data provided below ie "3"
    Then I expect image "<imageDisplayedOrNot>" displayed for "<productNumber>"
    Then I expect Product name displayed for "<productNumber>" is "<productName>"
    Then I expect clicking featured product "<productNumber>" navigates to Produc Details Page
    And I wait for page load
    Then I expect User navigated to "ProductDetail" Page

    Examples: 
      | productNumber | imageDisplayedOrNot | productName                                  |
      |             1 | is                  | GORE® GR Sheet Gasketing |
      |             2 | is                  | UPG - Class 150 - 1/8" - Ring                |
      |             3 | is                  | GORE® Joint Sealant                     |

      
    Scenario: Validate the content and error message displayed for Quick Order
     Then I expect text displayed for "homepage.quickOrder.title" is "Quick Order"
     Then I expect "5" rows are displayed to add products
     Then I expect "placeholder" attribute for "homepage.quickOrder.partNumber.input" is "Part Number"
     Then I expect "placeholder" attribute for "homepage.quickOrder.quantity.input" is "Quantity"
     Then I expect "homepage.quickOrder.addtoCartCTA" is "disabled"
     Then I expect text displayed for "homepage.quickOrder.addtoCartCTA" is "Add all to Cart"
     Then I expect text displayed for "homepage.quickOrder.add3Lines" is "Add 3 Lines"
     And I enter part number as "Invalid-Part-Number" in row "1"
     And I enter quantity as "0" in row "1"
     Then I expect "homepage.quickOrder.addtoCartCTA" is "enabled"
     And I enter part number as "XYZ001" in row "2"    
     When I click on "homepage.quickOrder.addtoCartCTA"
     And I wait for page load 
     Then I expect error text "Please enter a quantity" is displayed for "Quantity" field in row "1"
     And I expect error text "Please enter a quantity" is displayed for "Quantity" field in row "2"
     And I expect error icon is displayed for both fields in row "1"
     And I expect error icon is displayed for both fields in row "2"
     Then I enter quantity as "10" in row "1"
     Then I enter quantity as "5" in row "2"
     When I click on "homepage.quickOrder.addtoCartCTA"
     And I wait for page load 
     Then I expect error text "Invalid Part Number" is displayed for "Part Number" field in row "1"
     And I expect error text "Invalid Part Number" is displayed for "Part Number" field in row "2"
     And I expect error icon is displayed for both fields in row "1"
     And I expect error icon is displayed for both fields in row "2"
    
    Scenario: Validate the functionality of 'Add all to Cart' CTA and 'Add 3 Lines' link

    Then I login using UserID "4ee7b67d.wlgore.onmicrosoft.com@amer.team.val" with password "Gore@1234" and lands on home page


 Scenario: Validating functioning of Site Masthead
    Then I proceed to test "service mailto Link"
    And I expect "homepage.siteMasthead.service" is a mailto link "mailto:gaskets@wlgore.com"
    Then I proceed to test "link redirection of site Masthead cards"
    And I click on "homepage.siteMasthead.categoryCard.one.title" and verify url is "https://val-wlgore.cs172.force.com/Gasket/s/productlist?categoryId=a1Q3Z000003cmXoUAI"
    And I click on "homepage.siteMasthead.categoryCard.two.title" and verify url is "https://val-wlgore.cs172.force.com/Gasket/s/productlist?categoryId=a1Q3Z000003cmXqUAI"
    And I click on "homepage.siteMasthead.categoryCard.three.title" and verify url is "https://val-wlgore.cs172.force.com/Gasket/s/productlist?categoryId=a1Q3Z000003cmXpUAI"
    And I click on "homepage.siteMasthead.categoryCard.four.title" and verify url is "https://val-wlgore.cs172.force.com/Gasket/s/productlist?categoryId=a1Q3Z000003cmXrUAI"
    #And I expect "homepage.footer.privacyNotice.label" redirects to "https://devmergecc-wlgore"


  Scenario: Validating functioning of Order History
    Then I expect order history table is sorted by order date "homepage.orderHistory.date"
    Then I verify for blank values in order history table
    And I proceed to test "order history links redirection"
    Then I click on "homepage.orderHistory.viewFullOrderHistory.link" and verify url is "https://val-wlgore.cs172.force.com/Gasket/s/orderhistory"
    Then I click on "homepage.orderHistory.orderDetailsLink" and verify url is "https://val-wlgore.cs172.force.com/Gasket/s/orderdetail?id=a1t59000000GyiRAAS"




  Scenario: Validate the functionality of 'Add all to Cart' CTA and 'Add 3 Lines' link
    Then I expect "5" rows are displayed to add products
    Then I enter part number as "GR0560" in row "1"
    And I enter quantity as "2" in row "1"
    Then I expect "homepage.quickOrder.addtoCartCTA" is "enabled"
    Then I enter part number as "Invalid-Part-Number" in row "2"
    And I enter quantity as "20" in row "2"
    Then I enter part number as "GR1602815SC" in row "3"
    And I enter quantity as "30" in row "3"
    Then I enter part number as "1000B" in row "4"
    And I enter quantity as "40" in row "4"
    When I click on "homepage.quickOrder.addtoCartCTA"
    And I wait for element's "homepage.quickOrder.addtoCartCTA" "class" value to be "btn-primary large btn-add-to-cart"
    And I wait for page load
    Then I expect error text "Invalid Part Number" is displayed for "Part Number" field in row "2"
    And I expect error icon is displayed for both fields in row "2"
    Then I enter part number as "0020D" in row "2"
    When I click on "homepage.quickOrder.addtoCartCTA"
    And I wait for page load
    Then I expect no error text displayed in quick order
    And I expect "homepage.quickOrder.addtoCartCTA" is "enabled"
    #And I expect round of message is displayed for "GR0560&GR0560&1000B"
    And I expect Cart value text displayed should contain "4" as product types but only applicable for desktop
    When I click on "homepage.quickOrder.add3Lines" 
    Then I expect "8" rows are displayed to add products
    When I click on "homepage.quickOrder.add3Lines" 
    Then I expect "11" rows are displayed to add products
    When I click on "homepage.quickOrder.add3Lines" 
    Then I expect "14" rows are displayed to add products
    When I click on "homepage.quickOrder.add3Lines" 
    Then I expect "17" rows are displayed to add products
    When I click on "homepage.quickOrder.add3Lines" 
    Then I expect "20" rows are displayed to add products
    When I click on "homepage.quickOrder.add3Lines" 
    Then I expect "20" rows are displayed to add products
    When I refresh the page
    And I wait for page load
    Then I expect "5" rows are displayed to add products
  
