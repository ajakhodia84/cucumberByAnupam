@Reg_globalHeader
Feature: Verify functioning of Global Header

  Background: User starts from Gasket's home page
    Given User is at "gasket" brand's "login" page of "us" locale
    And I wait for page load
    Then I verify user "4ee7b67d.wlgore.onmicrosoft.com@amer.team.val" with password "Gore@1234" is able to login
    And I wait for page load
    Then I expect User navigated to "Home" Page

  Scenario: Validating contents shown on the header
    And I wait for page load
    Then I expect text displayed for "homepage.header.productCategory.one" is "Joint Sealants"
    Then I expect text displayed for "homepage.header.productCategory.two" is "Cut Gaskets"
    Then I expect text displayed for "homepage.header.productCategory.three" is "Sheet Gasketing"
    Then I expect text displayed for "homepage.header.productCategory.four" is "Gasket Tape"
    Then I expect text displayed for "homepage.header.productCategory.more" is "More"

  Scenario: Validating Gore logo functionality
    Then I expect "Gore Logo image" is displayed for "homepage.header.logo.image" element locator
    Then I click on "homepage.header.logo.wrapper"
    And I wait for page load
    Then I click on "homepage.header.hamburgerMenu" if I am using mobile
    Then I click on "Gasket 1" product category in global header to navigate to respective PLP Page
    And I wait for page load
    Then I expect "Gore Logo image" is displayed for "homepage.header.logo.image" element locator
    Then I click on "homepage.header.logo.wrapper"
    And I wait for page load
    Then I expect User navigated to "Home" Page

  Scenario: Validation for Product Categories displayed on Global Header
    Then I click on "homepage.header.hamburgerMenu" if I am using mobile
    Then I expect all product categories displayed in Global Header
    Then I verify clicking product category link navigates to respective PLP page
    Then I close the hamburger menu if its open

  Scenario: Validation for Accounts Menu
    Then I click on "homepage.header.hamburgerMenu" if I am using mobile
    Then I expect text displayed for Account menu option is "Hello," and User's First name "Test User"
    Then I click on Account menu option
    Then I expect all Account menu options displayed in Accout Menu dropdown
    Then I click on "Sign Out" from Account Options list
    And I wait for page load
    Then I expect User navigated to "Login" Page
    Then I expect text displayed for "loginpage.header.title" is "WELCOME TO THE GASKETS STORE"

  Scenario: Validation for Cart icon, Cart Que icon and label and types of products displayed in global header
    Then I expect Cart icon is displayed
    Then I expect Cart que icon is not displayed when cart is empty
    Then I expect Cart que icon is displayed when cart is not empty
    Then I expect Cart label is displayed only for desktop
    Then I expect Cart value text is not displayed when cart is empty but only applicable for desktop
    Then I expect Cart value text displayed should contain "2" as product types but only applicable for desktop
    Then I expect "homepage.header.cartValue" is displayed in color "#da291c"
    Then I click on "homepage.header.cartIcon"
    Then I expect User navigated to "CheckoutCart" Page

  Scenario: Validation for Search and Auto-suggest in Search bar
    Then I expect Search icon is displayed
    Then I expect Search label is displayed only for desktop
    When I click on "homepage.header.searchicon"
    Then I expect Search Bar is maximized
    When I enter text "Gor" in search bar, See Results gets enabled
    Then I expect autosuggestions is displayed in 300ms
    When I click on See Results CTA
    And I wait for page load
    Then I expect User navigated to "ProductList" Page
    When I click on "homepage.header.searchicon"
    When I enter text "Gas" in search bar, See Results gets enabled
    Then I expect autosuggestions is displayed in 300ms
    And I press Enter Key for "homepage.header.searchInput"
    And I wait for page load
    Then I expect User navigated to "ProductList" Page
    When I click on "homepage.header.searchicon"
    When I enter text "SEA" in search bar, See Results gets enabled
    Then I expect autosuggestions is displayed in 300ms
    And I select "GORE® Joint Sealant FT" from Auto-suggestion list
    And I wait for page load
    Then I expect User navigated to "ProductDetail" Page
    When I click on "homepage.header.searchicon"
    When I enter text "Not available" in search bar, See Results gets enabled
    Then I expect autosuggestions are not displayed in 300ms
    When I click on "homepage.header.searchCloseIcon"
    Then I expect Search bar is minimized and closed
    When I click on "homepage.header.searchicon"
    When I enter text "Gore" in search bar, See Results gets enabled
    Then I expect autosuggestions is displayed in 300ms
    And I select "UPG - Class 300 - 1/4 - Ring" from Auto-suggestion list
