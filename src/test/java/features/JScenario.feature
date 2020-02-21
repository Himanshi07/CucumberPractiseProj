@EleventhScenario
Feature: My Wishlist 
  To verify the Wishlist section
  Background:
  Given User has logged in and is at My Dashboard
  
   @Add
   Scenario: Verify Adding Item to Basket from My Wishlist section
   And Adds Item to Wishlist
   When User clicks Add to Basket for any Item present 
   Then Item should get Added to Basket
    
    @Edit
    Scenario: Verify Editing Items in My Wishlist 
    When User clicks Edit option for any Item showing 
    Then User should be redirected to Product details page for Updating the Information
    
    @Update
    Scenario: Verify Updating Items in My Wishlist 
    When User enters Comment & increment/decrement QTY for multiple products  
    And clicks Update Wishlist button
    Then All the changes made should be Updated
    
    @Delete
    Scenario: Verify Deleting Items from My Wishlist 
    When User clicks Delete icon for any product 
    Then A pop up should appear to Confirm deletion
    And On clicking Ok Item should be removed from the Wishlist
    
    @ShareWishlist
    Scenario Outline: Verify Share Wishlist option
    And clicks Share Wishlist button
    When User Enters valid <EmailId> & <Message>
    And  clicks Share wishlist button
    Then Wishlist shared successfully message should be shown
    
    Examples: 
      |  EmailId  								             |   Message             |
      | "test@wcl.com"							           | "For testing purpose" |
      | "himanshi.sharma@williamscommerce.com" | "Test test"           |
      
    
    @AddAll
    Scenario: Verify by Adding All Items to Cart at once
    When User clicks Add All to Cart button
    Then All Items should get added to Cart and No item should be there in My Wishlist
    
  
    