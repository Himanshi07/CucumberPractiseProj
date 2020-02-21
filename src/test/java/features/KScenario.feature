
@12thScenario
Feature: Verify General Scenario
  
  Background: 
  Given User is at Homepage
  And Scrolls down to Footer section
  
  @Subscription
  Scenario: Verify User Subscription
    Given User enters Valid Email Id in Subscription textfield
    And clicks Sign Up
    Then User should get a Thankyou message
    
  @RequestCatalogue
  Scenario: Verify Request Catalogue page
    Given User clicks on Request a Catalogue image
    When Fills mandatory fields in the Form 
    And Submit the form
    Then User should get a Thankyou message
   
   @ContactUs
   Scenario: Verify Contact Us page
    Given User clicks on Contact Us link & Redirects to Contact Us page
    When User Fill mandatory fields in the Form & Submit it
    Then User should get a Thankyou message
      
