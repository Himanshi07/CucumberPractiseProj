@SecondScenario
Feature: Add Product
   To verify by adding products to cart
   
 Background:
 		Given User is at "https://award.wcltest.com/" website
 		
		@Single
    Scenario: Customer Adds a product to Cart
    And search for Medals
    And Add the first product to Basket
    Then User should see the product in my Basket
    
    @Multiple
    Scenario: Customer Adds Multiple products to Cart
    And Enters oak medal and click Search icon
    Then User will be redirected to search result page
    And here i add few products to cart
    Then Added products should be shown in Basket
    	
    @FromWishlist
    Scenario: Customer Adds a product from My Wishlist to Cart
    Given User has logged In
    When User navigates to My Wishlist and add product present to My Basket
    Then Added products should be shown in Basket