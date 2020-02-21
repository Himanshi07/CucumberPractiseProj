
@EigthScenario
Feature: Verify Discount code
 
 Background:
 			Given User is at Shopping Cart page
  
  @Code
  Scenario: Verify by applying Promo code
    When User enters Promo code in Discounted Codes field & Apply
    Then Discounted Amount should be shown below Subtotal 
    And Should be reflected in GRAND TOTAL
    

  @AutoPromo
  Scenario: Verify Auto Promotions 
    Given User has selected few items from Product page
    When User checks Shopping Cart page
    Then Discounted Amount shows below Subtotal 
    

    