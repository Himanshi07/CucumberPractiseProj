
@SixthScenario
	Feature: Verify functionalities at Product List page
  
  Background:
   Given User is at Homepage
   And Navigates through Header Menu Category & Subcategory
  
  @Sort
  Scenario Outline: Verify Sort By option
    Given User is at Product list page 
   	When User Sorts product by <values> 
   	And  Clicks Asc/Desc arrow
   	And  selects View As
    Then Products should be shown according to the Sorting

 Examples: 
     			 | values  | 
     			 | "Name"  |
     			 | "Price" |
     			 
  @Show
  Scenario: Verify by selecting per page dropdown
    Given User is at Product list page
    When User selects per page value
    Then Selected number of products if present will show up 
    
  @AddProd
   Scenario: Verify by adding product to the Cart
    Given User is at Product list page
    When User clicks 'Add to Basket' for any product
    Then Item should get added to the Basket 
   
  @AddWishlist
	Scenario: Verify by adding product to Wishlist
		Given User is at Product list page
    When User clicks 'Add to Wishlist' for any product
    Then For Guest user it should redirect to login page first  
    
    
    
