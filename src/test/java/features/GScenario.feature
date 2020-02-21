
@SeventhScenario
Feature: Verify features at Product details page
 
 Background: 
  Given I am at Product Details page
  
  @PDPFields
  Scenario: Verify by filling Mandatory Fields 
    And fills mandatory fields & Changes Qty
    When I click Add to Basket button
    Then Product successfully added message should be shown
    
  @Wishlist
  Scenario: Verify by adding product to Wishlist
		When I clicks Add to Wishlist for any product
    Then Product should get added to Wishlist 
    And For Guest user redirected to login page first 
    
  @Share
  Scenario: Verify Sharing option
    When User clicks any Sharing option
    Then relevant social site should open in new Tab
    
