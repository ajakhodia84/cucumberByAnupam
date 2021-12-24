@Reg_ProductListPage
Feature: Verify functionality and content displayed on Product list page

  Background: User starts from Gasket's home page
    Given User is at "gasket" brand's "login" page of "us" locale
    And I wait for page load
    Then I verify user "4ee7b67d.wlgore.onmicrosoft.com@amer.team.val" with password "Gore@1234" is able to login
    And I wait for page load
    And I expect User navigated to "Home" Page
    And I click on "homepage.header.hamburgerMenu" if I am using mobile

  Scenario: Validate Page Header, category information and number of product listed on Product list page
    And I click on "Joint Sealants" product category in global header to navigate to respective PLP Page
    And I wait for page load
    Then I expect text displayed for "plp.pageHeader" is "JOINT SEALANTS"
    And I expect "plp.pageHeader" is displayed in color "#DC260A"
    And I expect text displayed for "plp.categoryDetail.description" is "Made from 100% ePTFE, versatile, easy to install GORE® Joint Sealant is a time-tested, cost effective sealing solution for steel flanges with large diameters, rectangular or irregular shapes, and rough or pitted surfaces."
    And I expect number of items mentioned in total items section is correct for "JOINT SEALANTS"
    Then I click on "Cut Gaskets" product category in global header to navigate to respective PLP Page
    And I wait for page load
    Then I expect text displayed for "plp.pageHeader" is "CUT GASKETS"
    And I expect "plp.pageHeader" is displayed in color "#DC260A"
    And I expect text displayed for "plp.categoryDetail.description" is "GORE® Gaskets, made from 100% ePTFE, provide a reliable seal for flanges used with the full spectrum of strong acid, alkali, and solvent process media, including the most challenging thermal cycling and elevated temperature applications. GORE® Universal Pipe Gaskets can be used across multiple pipe flange materials — steel, glass-lined steel (GLS) and fiber-reinforced plastic (FRP) — to reduce safety and production downtime risks from the use of an incorrect gasket material. GORE® GR Cut Gaskets provide reliable sealing for steel piping and equipment."    
    And I expect number of items mentioned in total items section is correct for "CUT GASKETS"
    Then I click on "Sheet Gasketing" product category in global header to navigate to respective PLP Page
    And I wait for page load
    Then I expect text displayed for "plp.pageHeader" is "SHEET GASKETING"
    And I expect "plp.pageHeader" is displayed in color "#DC260A"
    And I expect text displayed for "plp.categoryDetail.description" is "GORE® GR Sheet Gasketing is designed to outperform both conventional (filled and skived) PTFE and other ePTFE gasketing in steel piping and equipment. It provides a reliable seal for flanges used with the full spectrum of strong acid, alkali, and solvent process media, including the most challenging thermal cycling and elevated temperature applications."    
    And I expect number of items mentioned in total items section is correct for "SHEET GASKETING"
    Then I click on "Gasket Tape" product category in global header to navigate to respective PLP Page
    And I wait for page load
    Then I expect text displayed for "plp.pageHeader" is "GASKET TAPE"
    And I expect "plp.pageHeader" is displayed in color "#DC260A"
    And I expect text displayed for "plp.categoryDetail.description" is "GORE® Gasket Tape is made of 100% expanded polytetrafluoroethylene (ePTFE), using a proprietary Gore manufacturing technology that delivers exceptional sealing reliability in the most challenging thermal cycling and elevated temperature applications. GORE® Gasket Tape Series 500 is a highly conformable adhesive-backed tape that makes it easy to create custom gaskets on large, irregularly-shaped or damaged steel flanges. GORE® Gasket Tape Series 1000 has a proprietary barrier core and is optimized to provide an exceptionally tight and reliable seal for the unique challenges of glass lined steel flanges"
    And I expect number of items mentioned in total items section is correct for "GASKET TAPE"


#  Scenario: Checking functioning of More category option present at PLP
#    Then I open product list page "homepage.header.productCategory.one" from header
#    Then I verify current selected PLP should not be present in More categories

#  Scenario: Checking category descriptions
#    Then I open product list page "homepage.header.productCategory.one" from header
#    And I wait for page load
#    Then I verify category title and description for "Joint Sealants" product list page
#    Then I verify products' title and descriptions in "Joint Sealants" product list page
#		And I expect "plp.pageHeader" is displayed in color "#DC260A"	

#    Then I open product list page "homepage.header.productCategory.two" from header
#    And I wait for page load
#    Then I verify category title and description for "Cut Gaskets" product list page
#    Then I verify products' title and descriptions in "Cut Gaskets" product list page
#		And I expect "plp.pageHeader" is displayed in color "#DC260A"

#    Then I open product list page "homepage.header.productCategory.three" from header
#    And I wait for page load
#    Then I verify category title and description for "Sheet Gasketing" product list page
#    Then I verify products' title and descriptions in "Sheet Gasketing" product list page
#		And I expect "plp.pageHeader" is displayed in color "#DC260A"

#    Then I open product list page "homepage.header.productCategory.four" from header
#    And I wait for page load
#    Then I verify category title and description for "Gasket Tape" product list page
#    Then I verify products' title and descriptions in "Gasket Tape" product list page
#		And I expect "plp.pageHeader" is displayed in color "#DC260A"

#    Examples:
#    | category | locator |
#    | Joint sealant | homepage.header.productCategory.one |
#    | Sheet Gasketing | homepage.header.productCategory.three |
#    | Gasket Tape | homepage.header.productCategory.four |



