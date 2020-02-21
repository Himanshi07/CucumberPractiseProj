
@FifthScenario
Feature: Products added in Cart
  To verify products are showing at Shopping cart page

  @ForEach
  Scenario: Verify whether description, Correct Qty and Unit price is displaying
    Given User is at Shopping Cart Page
    When User Checks for the Name,Price, Qty & Subtotal of added products
    Then Name, Price, Qty and Subtotal should be shown correctly for each Product

      
  @Editoperations
  Scenario: Verify by Editing product present in Cart page
  	Given User is at Shopping Cart Page
    And User clicks on Edit link of any added product
    And gets Redirected to Product detail page
    When User edit the fields and click Update button
    Then User should be redirected to Shopping Cart page with updated details of that particular product

    @DeleteOperations
  Scenario: Verify Deleting product present in Cart page
  	Given User is at Shopping Cart Page
    When User clicks on Delete icon for any added product
    Then Product should be removed from the list
        
    @UpdateOps
   Scenario: Verify Updating product present in Cart page
  	Given User is at Shopping Cart Page
    When User Changes the Quantity for any product
    And Clicks on Updated button appearing beside the Quantity Textfield
    Then Quantity of Product should get updated  
    
    @UpdateOps2
   Scenario: Verify Updating whole cart at once 
  	Given User is at Shopping Cart Page
    When User changes Quantity for multiple products one by one
    And clicks on Update Shopping Cart link
    Then Quantity for all products should be updated at once  
    
    @FreeItem
    Scenario: Verify by Adding Free Item to cart
  	Given Product is already added to Basket
    And User clicks Add to Basket for Free product
    Then Free product gets added to basket
    
    @EmptyCart
   Scenario: Verify by Emptying the Cart 
  	Given User navigates to Shopping Cart Page
    When User clicks on Empty Cart link
    Then 'Shopping Cart is Empty' message should be shown 
    
   