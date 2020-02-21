
@FourthScenario
Feature: Verify Search mechanism is working as it should
  
  Background:
 		Given User is at homepage
  
  @SearchResult
  Scenario: Verify Search result in dropdown are relevant to Search term entered 
    Given User enters a Keyword to search
    When Loading gets complete 
    Then dropdown should show related results having search keyword
    
  @SortingOptions
  Scenario: Verify Shop By options 
    Given User is at Search result page
    When User Clicks any Shop By option under Category 
    Then Results should be shown according to the selection made
    
  Scenario Outline: Verify Sort By option
  	Given User is at Search result page
  	When  User selects <options> and click Asc/Desc arrow
  	Then  results should be shown accordingly
 
 Examples:
						| options  |
						| "Price"  |
						| "Name"   |
	
  Scenario: Verify View As options
   Given User is at Search result page
   When User clicks on Grid/List view
   Then Products should be shown accordingly
   
   Scenario: Verify by Adding Item to Cart
     When User Selects a Category and narrow down his search 
     And  Click 'Adds to Basket' for random Item
     Then Product should be added to the Cart successfully