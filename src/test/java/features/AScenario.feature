@FirstScenario
Feature: SignIn
  To verify the login functionality 
  
  Background:
  	Given User is  on homepage
    And clicks on Sign In
  
  	Scenario: Signin as a authenticated user
    Given User is at Customer Login page
    And user submits valid username and Password
    Then user gets redirected to My Dashboard
        
       
    Scenario Outline: Signin as an unauthenticated user
    Given User is at Customer Login page
    When user submits invalid <username> and <password>
    Then Validation message for Required fields will show up 
    
    Examples:  
   			| username           | password      |
  			| "Test"             | "Tester@111"	 |
   			| "himanshi@wcl.com" | "abc"         |
   			| "test@wcl.com"     | "tester@1asd" |
 
   			
   			
      
     
    
    
    
    