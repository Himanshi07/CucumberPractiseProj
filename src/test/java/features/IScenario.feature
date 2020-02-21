
@NinthScenario
Feature: Verify Checkout scenarios
  
  Background: 
  Given User has added items to the cart
  And  is redirected to Checkout page
  
  @GuestUserCOD
  Scenario: Checkout as Guest User- COD
    When User choose to Continue with Checkout as Guest option
    Then User fill Billing Information section
    And  Add new Shipping Address
    And  Select Shipping Method
    And  Select Payment Information as Cash On delivery
    Then User Clicks on Place Order button
    And gets redirected to Order Confirmation page

	@GuestUserSagePay
  Scenario: Checkout as Guest User- SagePay
    When User choose to Continue with Checkout as Guest option
    Then User fill Billing Information section
    And  Add new Shipping Address
    And  Select Shipping Method
    And  Select Payment Information as SagePay 
    Then User Clicks on Place Order button and Redirects to SagePay for Payment
    And Chooses appropriate options and Places order
    And Recieves a Confirmation message
    
  @RegisteredUserCOD
  Scenario: Checkout using Register & Checkout- COD
    When User choose to Continue with Register & Checkout option
    Then User fills Billing Information section
    And  Select Shipping Method
    And  Select Payment Information as COD
    Then User Clicks on Place Order button 
    And gets redirected to Order Confirmation page
    And Clicks on Continue Shopping button

	#@RegisteredUserSage
	#Scenario: Checkout as Register & Checkout- SagePay
   # When User choose to Continue with Register & Checkout option
   # Then User fills Billing Information section
   # And  Select Shipping Method
   # And  Select Payment Information as SagePay 
   # Then User Clicks on Place Order button and Redirects to SagePay for Payment
   # And Chooses appropriate options and Places order
   # And Recieves an Order Confirmation message
      
 # @ReturningCustCOD
 # Scenario: Checkout as Returning Customer- COD
   # When User enters Email Id and Password
   # Then User gets redirected to Checkout page 
   # And  Selects already Added Address
   # And  Continue with Shipping Method
   # And  Select Payment Information as COD 
   # Then User Clicks on Place Order button and gets Order Confirmation message
    
  @ReturningCustSage
  Scenario: Checkout as Returning Customer- Sage
    When User enters Email Id and Password
    Then User gets redirected to Checkout page 
    And  Selects already Added Address
    And  Continue with Shipping Method
    And  Select Payment Information as SagePay 
   	Then User Clicks on Place Order button and Redirects to SagePay for Payment
    And Chooses appropriate options and Places order
    And Recieves an Order Confirmation message 
   	
   
 # @MultipleAddressGuest
 # Scenario: Verify Checkout with Multiple Addresses
 # 	Given User clicks Checkout with Multiple Addresses link
 #  And  Gets redirected to Login Page & clicks Create an Account button
 #  And  Fill the mandatory textfields(Disabling Captcha) and Click Register  
 #  When User gets redirected to Ship to Multiple Addresses page
 #  And  Click on Enter a New Address button 
 #  Then New address gets added and shows up in SEND TO dropdown
   
   