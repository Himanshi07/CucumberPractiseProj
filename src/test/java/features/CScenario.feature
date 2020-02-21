
@ThirdScenario
  Feature:  My Account
   To verify options in My Account section
  
   Background: 
   Given User is Logged in
   
  	@Orders
    Scenario: Verify Order update 
    Given User is at My Dashboard
    When User click on My Orders
    Then Tabel having Order details should show Recent Order details at Top

    @Address
    Scenario: Verify by adding New Address in Address Book
    Given User is at My Dashboard
    And clicks Address Book option 
    And gets redirected to Address Book page
    When User clicks Add New Address button 
    Then User needs to fill Manadatory fields under Contact Information & Address Section
    And New Address will show up below Additionl Address Entries
    
    @Subscription
     Scenario: Verify User is subscribed to Newsletter or not
     Given User is at My Dashboard
     And clicks Newsletter Subscriptions option 
     When Ticks General Subscription checkbox
     Then gets The subscription has been saved message
     
     
     @GiftCard
     Scenario Outline: Verify Gift Cards available
     Given User is at My Dashboard
     And clicks Gift Cards option 
     When Submits <GiftCode> 
     Then <message> should be shown
     
     Examples: 
     |   GiftCode      |  message                        | 
     | "PROMO_SW369"   | "This Gift Code already exists" |
     | "test"          | "Wrong Gift Card code"          |