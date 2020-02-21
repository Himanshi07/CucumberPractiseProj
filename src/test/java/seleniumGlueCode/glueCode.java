package seleniumGlueCode;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.codehaus.plexus.util.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.*;


public class glueCode {

	
	public static WebDriver driver;
	
	//---------------------Methods to be Called-----------------------//
	
	private void screenshot(String fileName,String extension) throws IOException 
	{
		
		TakesScreenshot scr = (TakesScreenshot)driver;
		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		File scrFile = scr.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("./Screenshot/" + fileName +" "+timestamp+extension));
	}

	
	private void Homepage()
	{
		System.setProperty("webdriver.gecko.driver","./Drivers/geckodriver.exe" );
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://award.wcltest.com/");
	
	}
	
	
	private void SignIn() 
	{
		System.setProperty("webdriver.gecko.driver","./Drivers/geckodriver.exe" );
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://award.wcltest.com/customer/account/login/");
		
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("himanshi.sharma@williamscommerce.com");
	    driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("tester@111");
	    driver.findElement(By.cssSelector("#send2")).click();
		
	}
	
	private void Switch()
	{
		//String parentWindow = driver.getWindowHandle();
		String childWindow = null;
		
		Set<String> handle = driver.getWindowHandles();
		Iterator<String> link = handle.iterator();
		
		while(link.hasNext())
		{
			childWindow = link.next();
		}
		driver.switchTo().window(childWindow);
	}
	
	private void Add()
	{

		driver.findElement(By.cssSelector(".medals")).click();
		driver.findElement(By.cssSelector(".nav-1-2-2 > a:nth-child(1)")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div[5]/div/div[2]/div[3]/ul/li[2]/div/div[2]/ul/li/a/span[2]")).click();
		
		driver.navigate().back();
		driver.findElement(By.xpath("/html/body/div[3]/div/div[5]/div/div[2]/div[3]/ul/li[3]/div/div[2]/ul/li/a/span[2]")).click();
		
		driver.navigate().back();
		driver.findElement(By.xpath("/html/body/div[3]/div/div[5]/div/div[2]/div[3]/ul/li[4]/div/div[2]/ul/li/a/span[2]")).click();
		
		
	}	
	
	//---------------------------First Scenario---------------------------//
	
	@Given("^User is  on homepage$")
	public void user_is_on_homepage() throws Throwable {
		
		Homepage();
	}

	@Given("^clicks on Sign In$")
	public void clicks_on_Sign_In() throws Throwable {
	    
		driver.findElement(By.linkText("Sign in")).click();
	}

	@Given("^User is at Customer Login page$")
	public void user_is_at_Customer_Login_page() throws Throwable {
	    
		String Org = driver.getTitle();
		String Exp = "Customer Login";
		Assert.assertEquals(Exp, Org);
		System.out.println("User is at Customer Login Page");
	}

	@Given("^user submits valid username and Password$")
	public void user_submits_valid_username_and_Password() throws Throwable {
	   
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("himanshi.sharma@williamscommerce.com");
	    driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("tester@111");
	    driver.findElement(By.cssSelector("#send2")).click();

	}

	@Then("^user gets redirected to My Dashboard$")
	public void user_gets_redirected_to_My_Dashboard() throws Throwable {
	    
		String Org = driver.getTitle();
		String Exp = "My Account";
		Assert.assertEquals(Exp, Org);
		System.out.println("User is at My Dashboard");
		
		Thread.sleep(2000);
		driver.close();
	}

	@When("^user submits invalid \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_submits_invalid_username_and_password(String username, String password) throws Throwable {
	   
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(username);
	    driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(password);
	    driver.findElement(By.cssSelector("#send2")).click();
		
	}

	@Then("^Validation message for Required fields will show up")
	public void Validation_message_for_Required_fields_will_show_up() throws Throwable {
		
		Thread.sleep(1000);
		screenshot("InvalidLogin",".png");
		
		Thread.sleep(2000);
		driver.close();
	}

	//---------------------Second Scenario--------------------------//
	
	@Given("^User is at \"([^\"]*)\" website$")
	public void user_is_at_website(String arg1) throws Throwable {
		
		System.setProperty("webdriver.gecko.driver","./Drivers/geckodriver.exe" );
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://award.wcltest.com/");

	}
	
	
	@Given("^search for Medals$")
	public void search_for_Medals() throws Throwable {
	   
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".UI-SEARCH")).sendKeys("Medals");
	    driver.findElement(By.cssSelector(".nav-submit-button > button:nth-child(1)")).click();
	    Thread.sleep(2000);

	}

	@Given("^Add the first product to Basket$")
	public void add_the_first_product_to_Basket() throws Throwable {
	   
		Thread.sleep(3000);
		
		driver.findElement(By.cssSelector("li.item:nth-child(1) > div:nth-child(2) > div:nth-child(3) > button:nth-child(1)")).click();
		System.out.println("Success");

	}

	@Then("^User should see the product in my Basket$")
	public void user_should_see_the_product_in_my_Basket() throws Throwable {
	    
		Thread.sleep(3000);
		Switch();
	    screenshot("ProductAdded",".png");
		
		driver.findElement(By.cssSelector("span.button:nth-child(3) > a:nth-child(1)")).click();  
		
		Thread.sleep(2000);
		driver.close();
		
	}

	
	@Given("^Enters oak medal and click Search icon$")
	public void enters_oak_medal_and_click_Search_icon() throws Throwable {
	   
		driver.findElement(By.cssSelector(".UI-SEARCH")).sendKeys("oak medal");
		driver.findElement(By.cssSelector(".nav-submit-button > button:nth-child(1)")).click();

	}

	@Then("^User will be redirected to search result page$")
	public void user_will_be_redirected_to_search_result_page() throws Throwable {
		
		driver.getPageSource().contains("Search results for");
		System.out.println("Successfully on search result page");
		
		
		JavascriptExecutor executor = (JavascriptExecutor)driver ;
		executor.executeScript("window.scrollBy(0,2000)");
		
		screenshot("SearchPage",".png");
		
	}

	@Then("^here i add few products to cart$")
	public void here_i_add_few_products_to_cart() throws Throwable 
	{
	    
			JavascriptExecutor executor = (JavascriptExecutor)driver ;
			executor.executeScript("window.scrollBy(0,2000)");
			
			driver.findElement(By.cssSelector("li.item:nth-child(1)")).click();
			
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("#options_462_text")).sendKeys("Nice");
				
			Select first = new Select(driver.findElement(By.xpath("//*[@id='select_463']")));
			first.selectByVisibleText("Do-it-yourself Mounting");
						
			driver.findElement(By.xpath("/html/body/div[3]/div/div[5]/div[1]/div[2]/div[3]/div/form/div[3]/div[4]/div/div[2]/button")).click();
				
			driver.navigate().back();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#product-collection-image-3177")).click();
			driver.findElement(By.cssSelector("#options_476_text")).sendKeys("Test");
			Select second = new Select(driver.findElement(By.xpath("//*[@id='select_477']")));
			second.selectByVisibleText("Mounting by AWARD +£32.00");
			
			driver.findElement(By.cssSelector(".btn-cart")).click();
					
			driver.navigate().back();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector(".watches")).click();
			driver.findElement(By.cssSelector(".nav-6-1-3 > a:nth-child(1)")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#product-collection-image-16683")).click();
			
			Select third = new Select(driver.findElement(By.cssSelector("#attribute198")));
			third.selectByVisibleText("ROYAL AIR FORCE");
			
			driver.findElement(By.cssSelector("#options_3020_text")).sendKeys("Testtest");
			driver.findElement(By.cssSelector("#options_3019_text")).sendKeys("Test32");
			driver.findElement(By.cssSelector(".btn-cart")).click();
						
			System.out.println("Doneeeee");
		
	}
		

	@Then("^Added products should be shown in Basket$")
	public void added_products_should_be_shown_in_Basket() throws Throwable {
	    
		Switch();
		driver.findElement(By.cssSelector("span.button:nth-child(3)")).click();
		
		Thread.sleep(2000);
		driver.close();
	}

	
	@Given("^User has logged In$")
	public void user_has_logged_In() throws Throwable {
	    
		driver.findElement(By.cssSelector(".header-account > a:nth-child(1)")).click();
		driver.findElement(By.id("email")).sendKeys("himanshi.sharma@williamscommerce.com");
		driver.findElement(By.id("pass")).sendKeys("tester@111");
		driver.findElement(By.id("send2")).click();
	    

	}

	@When("^User navigates to My Wishlist and add product present to My Basket$")
	public void user_navigates_to_My_Wishlist_and_add_product_present_to_My_Basket() throws Throwable {
	   
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='top']/body/div[3]/div/div[5]/div/div[1]/div/div[2]/ul/li[5]/a")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[@class='button btn-cart']")).click();
		screenshot("ProductInBasket",".png");

	}


	//----------------------Third Scenario--------------------------//
	
	@Given("^User is Logged in$")
	public void user_is_Logged_in() throws Throwable {
	   
		SignIn();
	}

	@Given("^User is at My Dashboard$")
	public void user_is_at_My_Dashboard() throws Throwable {
	    
		String Org = driver.getTitle();
		String Exp = "My Account";
		Assert.assertEquals(Exp, Org);
		System.out.println("User is at My Dashboard");
		
	}

	@When("^User click on My Orders$")
	public void user_click_on_My_Orders() throws Throwable {
	    
		driver.findElement(By.xpath("//a[text()='My Orders']")).click();
		
	}

	@Then("^Tabel having Order details should show Recent Order details at Top$")
	public void tabel_having_Order_details_should_show_Recent_Order_details_at_Top() throws Throwable {
	    
		screenshot("Order Details",".png");
		
		Thread.sleep(2000);
		driver.close();
	}

	@Given("^clicks Address Book option$")
	public void clicks_Address_Book_option() throws Throwable {
	  
		driver.findElement(By.cssSelector("div.block-content:nth-child(2) > ul:nth-child(1) > li:nth-child(3) > a:nth-child(1)")).click();
		
	}

	@Given("^gets redirected to Address Book page$")
	public void gets_redirected_to_Address_Book_page() throws Throwable {
	    
		String Org = driver.getTitle();
		String Exp = "Address Book";
		Assert.assertEquals(Exp, Org);
		System.out.println("User is at Address Book page");
	}

	@When("^User clicks Add New Address button$")
	public void user_clicks_Add_New_Address_button() throws Throwable {
	   
		driver.findElement(By.xpath("/html/body/div[3]/div/div[5]/div/div[2]/div/div[1]/button")).click();
	}

	@Then("^User needs to fill Manadatory fields under Contact Information & Address Section$")
	public void user_needs_to_fill_Manadatory_fields_under_Contact_Information_Address_Section() throws Throwable {
	   
		
		//Need to ADD code for new address in all fields everytime 
		
		driver.findElement(By.xpath("//*[@id='telephone']")).sendKeys("8957653647");
		driver.findElement(By.xpath("//*[@id='street_1']")).sendKeys("New yee test");
		driver.findElement(By.xpath("//*[@id='city']")).sendKeys("Hempshire");
		driver.findElement(By.xpath("//*[@id='zip']")).sendKeys("WC E11");
		driver.findElement(By.cssSelector("button.button:nth-child(3)")).click();
	}

	@Then("^New Address will show up below Additionl Address Entries$")
	public void new_Address_will_show_up_below_Additionl_Address_Entries() throws Throwable {
	  
		String Text = driver.findElement(By.cssSelector(".success-msg > ul:nth-child(1) > li:nth-child(1) > span:nth-child(1)")).getText();
		System.out.println(Text);
		
		Thread.sleep(2000);
		driver.close();

	}

	@Given("^clicks Newsletter Subscriptions option$")
	public void clicks_Newsletter_Subscriptions_option() throws Throwable {
	   
		driver.findElement(By.cssSelector("div.block-content:nth-child(2) > ul:nth-child(1) > li:nth-child(6) > a:nth-child(1)")).click();
	}

	@When("^Ticks General Subscription checkbox$")
	public void ticks_General_Subscription_checkbox() throws Throwable {
	    
		boolean Check = driver.findElement(By.xpath("//*[@id='subscription']")).isSelected();
		System.out.println("Person is subscribed" + Check); 
		
		driver.findElement(By.cssSelector(".buttons-set > button:nth-child(2)")).click();
		
	}

	@Then("^gets The subscription has been saved message$")
	public void gets_The_subscription_has_been_saved_message() throws Throwable {
	    
		String msg = driver.findElement(By.cssSelector(".messages")).getText();
		System.out.println(msg);

		Thread.sleep(2000);
		driver.close();
		
	}

	@Given("^clicks Gift Cards option$")
	public void clicks_Gift_Cards_option() throws Throwable {
	    
		driver.findElement(By.xpath("/html/body/div[3]/div/div[5]/div/div[1]/div/div[2]/ul/li[7]/a")).click();
		Thread.sleep(2000);
	}

	@When("^Submits \"([^\"]*)\"$")
	public void submits_Gift_Code(String GiftCode) throws Throwable {
	   
		driver.findElement(By.xpath("//*[@id='am_giftcard_code']")).sendKeys(GiftCode);
		driver.findElement(By.cssSelector(".buttons-set > button:nth-child(1)")).click();
		
	}

	@Then("^\"([^\"]*)\" should be shown$")
	public void should_be_shown(String message) throws Throwable {
	       
		
		String a = driver.findElement(By.cssSelector(".error-msg > ul:nth-child(1) > li:nth-child(1)")).getText();
		String b = message;
		Assert.assertEquals(b, a);
		System.out.println("Verified");	

		Thread.sleep(2000);
		driver.close();
   }
	

 //----------------------Fourth Scenario--------------------------//


	@Given("^User is at homepage$")
	public void user_is_at_homepage() throws Throwable {
		
		Homepage();
	
	} 
	
	@Given("^User enters a Keyword to search$")
	public void user_enters_a_Keyword_to_search() throws Throwable {
	    
		
		driver.findElement(By.cssSelector(".UI-SEARCH")).sendKeys("Medals");
	}

	@When("^Loading gets complete$")
	public void loading_gets_complete() throws Throwable {
	    
		Thread.sleep(2000);
				
	}

	@Then("^dropdown should show related results having search keyword$")
	public void dropdown_should_show_related_results_having_search_keyword() throws Throwable {
	    
		screenshot("RelatedProducts",".png");
		
		Thread.sleep(2000);
		driver.close();
	}


	@Given("^User is at Search result page$")
	public void user_is_at_Search_result_page() throws Throwable {
	    
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".UI-SEARCH")).sendKeys("Medals");
		
		driver.findElement(By.cssSelector(".nav-submit-button > button:nth-child(1)")).click();
		Thread.sleep(2000);
	}

	@When("^User Clicks any Shop By option under Category$")
	public void user_Clicks_any_Shop_By_option_under_Category() throws Throwable {
	    
		driver.findElement(By.cssSelector("li.amshopby-cat:nth-child(7) > a:nth-child(1)")).click();
				
	}

	@Then("^Results should be shown according to the selection made$")
	public void results_should_be_shown_according_to_the_selection_made() throws Throwable {
	   
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,300)");
		
		screenshot("CategoryName",".png");
		
		Thread.sleep(2000);
		driver.close();
	}

	@When("^User selects \"([^\"]*)\" and click Asc/Desc arrow$")
	public void user_selects_and_click_Asc_Desc_arrow(String options) throws Throwable {
	    
		Select price = new Select(driver.findElement(By.xpath("/html/body/div[3]/div/div[5]/div/div[2]/div[2]/div[3]/div[1]/div[1]/div/select")));
		price.selectByVisibleText(options);
		
		driver.findElement(By.cssSelector(".category-products > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(3)")).click();
	    Thread.sleep(2000);
	}

	@Then("^results should be shown accordingly$")
	public void results_should_be_shown_accordingly() throws Throwable {
	   
		JavascriptExecutor executor = (JavascriptExecutor)driver ;
		executor.executeScript("window.scrollBy(0,700)");
		Thread.sleep(1000);
		screenshot("SortedCategories",".png");
		
		Thread.sleep(2000);
		driver.close();
	}

	
	@When("^User clicks on Grid/List view$")
	public void user_clicks_on_Grid_List_view() throws Throwable {
	    
		driver.findElement(By.cssSelector(".category-products > div:nth-child(1) > div:nth-child(1) > p:nth-child(1) > a:nth-child(3)")).click();
	    Thread.sleep(2000);
		
	}

	@Then("^Products should be shown accordingly$")
	public void products_should_be_shown_accordingly() throws Throwable {
	   
		JavascriptExecutor executor = (JavascriptExecutor)driver ;
		executor.executeScript("window.scrollBy(0,800)");
		Thread.sleep(1000);
		screenshot("ListView",".png");
		
		Thread.sleep(1000);
		driver.close();
	}

	@When("^User Selects a Category and narrow down his search$")
	public void user_Selects_a_Category_and_narrow_down_his_search() throws Throwable {
		
		driver.findElement(By.cssSelector(".UI-SEARCH")).sendKeys("watches");
		driver.findElement(By.cssSelector(".nav-submit-button > button:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.amshopby-cat:nth-child(3) > a:nth-child(1)")).click();
		
		JavascriptExecutor executor = (JavascriptExecutor)driver ;
		executor.executeScript("window.scrollBy(0,400)");		
		Thread.sleep(2000);
	}

	@When("^Click 'Adds to Basket' for random Item$")
	public void click_Adds_to_Basket_for_random_Item() throws Throwable {
	    
		driver.findElement(By.xpath("/html/body/div[3]/div/div[5]/div/div[2]/div[2]/div[3]/ul/li[1]/div/div[2]/button")).click();
		
		driver.findElement(By.cssSelector("#options_3043_text")).sendKeys("Beautiful");
		driver.findElement(By.cssSelector("#options_3044_text")).sendKeys("7007");
		driver.findElement(By.cssSelector("div.qty-minusplus:nth-child(4)")).click();
		driver.findElement(By.cssSelector(".btn-cart")).click();
	}

	@Then("^Product should be added to the Cart successfully$")
	public void product_should_be_added_to_the_Cart_successfully() throws Throwable {
	    
		screenshot("ProductAddedCategoryWise",".png");
		
		Thread.sleep(2000);
		driver.close();
	}
	
	
	 //----------------------Fifth Scenario--------------------------//
	
	@Given("^User is at Shopping Cart Page$")
	public void user_is_at_Shopping_Cart_Page() throws Throwable {
		
		SignIn();
		
		driver.findElement(By.cssSelector(".skip-cart")).click();
		Thread.sleep(2000);
		Switch();
		
		driver.findElement(By.cssSelector("a.button:nth-child(2)")).click();  
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("a.close:nth-child(2)")).click();
		Thread.sleep(2000);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,300)");
	}


	@When("^User Checks for the Name,Price, Qty & Subtotal of added products$")
	public void user_Checks_for_the_Name_Price_Qty_Subtotal_of_added_products() throws Throwable {
	   
		screenshot("ShoppingCartLookFeel",".png");
	}

	@Then("^Name, Price, Qty and Subtotal should be shown correctly for each Product$")
	public void name_Price_Qty_and_Subtotal_should_be_shown_correctly_for_each_Product() throws Throwable {
	   
		System.out.println("Products present in Cart are clicked");
		
		Thread.sleep(2000);
		driver.close();
	}

	@Given("^User clicks on Edit link of any added product$")
	public void user_clicks_on_Edit_link_of_any_added_product() throws Throwable {
	   
				
		driver.findElement(By.cssSelector("tr.even > td:nth-child(4) > ul:nth-child(3) > li:nth-child(1) > a:nth-child(1)")).click();
		
	}

	@Given("^gets Redirected to Product detail page$")
	public void gets_Redirected_to_Product_detail_page() throws Throwable {
	    
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,300)");
		
		screenshot("ToBeUpdatedpage",".png");
	}

	@When("^User edit the fields and click Update button$")
	public void user_edit_the_fields_and_click_Update_button() throws Throwable {
	   
		WebElement a = driver.findElement(By.xpath("//*[@id='qty']"));
		a.clear();
		a.sendKeys("2");
		driver.findElement(By.cssSelector(".add-to-cart-buttons > button:nth-child(1) > span:nth-child(1) > span:nth-child(1)")).click();
	}

	@Then("^User should be redirected to Shopping Cart page with updated details of that particular product$")
	public void user_should_be_redirected_to_Shopping_Cart_page_with_updated_details_of_that_particular_product() throws Throwable {
	    
		
		String msg = driver.findElement(By.cssSelector(".success-msg > ul:nth-child(1) > li:nth-child(1)")).getText();
		System.out.println(msg);
		screenshot("UpdatedProduct",".png");
		
		Thread.sleep(2000);
		driver.close();
	}

	@When("^User clicks on Delete icon for any added product$")
	public void user_clicks_on_Delete_icon_for_any_added_product() throws Throwable {
	   
		driver.findElement(By.cssSelector("tr.even > td:nth-child(6) > a:nth-child(1)")).click();
	}

	@Then("^Product should be removed from the list$")
	public void product_should_be_removed_from_the_list() throws Throwable {
	    
		/*JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,2500)"); */
		
		screenshot("ProductDeleted",".png");
		
		Thread.sleep(2000);
		driver.close();
	}

	@When("^User Changes the Quantity for any product$")
	public void user_clicks_on_Quantity_field_for_any_product() throws Throwable {
	    
	WebElement a= driver.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(4) > input:nth-child(1)"));
	a.clear();
	a.sendKeys("3");
				
	}


	@When("^Clicks on Updated button appearing beside the Quantity Textfield$")
	public void clicks_on_Updated_button_appearing_beside_the_Quantity_Textfield() throws Throwable {

		driver.findElement(By.cssSelector("button.btn-update:nth-child(2) > span:nth-child(1) > span:nth-child(1)")).click();
		
	}

	@Then("^Quantity of Product should get updated$")
	public void quantity_of_Product_should_get_updated() throws Throwable {
	    
		screenshot("SingleProductUpdated",".png");
		
		Thread.sleep(2000);
		driver.close();
		
	}

	@When("^User changes Quantity for multiple products one by one$")
	public void user_changes_Quantity_for_multiple_products_one_by_one() throws Throwable {
	   
		WebElement a= driver.findElement(By.cssSelector("tr.odd > td:nth-child(4) > input:nth-child(1)"));
		a.clear();
		a.sendKeys("2");
		
		WebElement b= driver.findElement(By.cssSelector("tr.even > td:nth-child(4) > input:nth-child(1)"));
		b.clear();
		b.sendKeys("5");
	}

	@When("^clicks on Update Shopping Cart link$")
	public void clicks_on_Update_Shopping_Cart_link() throws Throwable {
	    
		driver.findElement(By.cssSelector("button.button2:nth-child(3) > span:nth-child(1) > span:nth-child(1)")).click();
	}

	@Then("^Quantity for all products should be updated at once$")
	public void quantity_for_all_products_should_be_updated_at_once() throws Throwable {
	    
		screenshot("MultipleProductsUpdated",".png");
		
		Thread.sleep(2000);
		driver.close();
	}
	
	@Given("^Product is already added to Basket$")
	public void product_is_already_added_to_Basket() throws Throwable {
	    
		SignIn();
		
		driver.findElement(By.cssSelector(".skip-cart")).click();
		Thread.sleep(2000);
		Switch();
		
		driver.findElement(By.cssSelector("a.button:nth-child(2)")).click();  
		Thread.sleep(2000);
	
	}

	@Given("^User clicks Add to Basket for Free product$")
	public void user_clicks_Add_to_Basket_for_Free_product() throws Throwable {
	   
		driver.findElement(By.cssSelector(".btn-cart")).click();
		Thread.sleep(1000);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,500)");
	}

	@Then("^Free product gets added to basket$")
	public void free_product_gets_added_to_basket() throws Throwable {
	    
		screenshot("FreeProductAdded",".png");
	}

	@Given("^User navigates to Shopping Cart Page$")
	public void user_navigates_to_Shopping_Cart_Page() throws Throwable {
		
		SignIn();
		
		driver.findElement(By.cssSelector(".skip-cart")).click();
		Thread.sleep(2000);
		Switch();
				
		driver.findElement(By.cssSelector("a.button:nth-child(2)")).click();  
		Thread.sleep(2000);
		
		
	}
	@When("^User clicks on Empty Cart link$")
	public void user_clicks_on_Empty_Cart_link() throws Throwable {
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,500)");
		driver.findElement(By.cssSelector("#empty_cart_button > span:nth-child(1) > span:nth-child(1)")).click();
	}

	@Then("^'Shopping Cart is Empty' message should be shown$")
	public void shopping_Cart_is_Empty_message_should_be_shown() throws Throwable {
	    
		String msg = driver.findElement(By.xpath("/html/body/div[3]/div/div[5]/div/div/div[1]/h1")).getText();
		System.out.println(msg);
		
		Thread.sleep(2000);
		driver.close();
		
	}

	
	
	 //----------------------Sixth Scenario--------------------------//
	
	@Given("^User is at Homepage$")
	public void user_is_at_Homepage() throws Throwable {
	    Homepage();
	}
	
	@Given("^Navigates through Header Menu Category & Subcategory$")
	public void navigates_through_Header_Menu_Category_Subcategory() throws Throwable {
	    
		driver.findElement(By.cssSelector(".medals")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".nav-1-2-1 > a:nth-child(1)")).click();
		Thread.sleep(2000);
	}

	@Given("^User is at Product list page$")
	public void user_is_at_Product_list_page() throws Throwable {
	    
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    jse.executeScript("window.scrollBy(0,300)");
		Thread.sleep(1000);
	    screenshot("PDPbefore",".png");
	}

	@When("^User Sorts product by \"([^\"]*)\"$")
	public void user_Sorts_product_by(String values) throws Throwable {
	    
		Thread.sleep(1000);
		Select s = new Select(driver.findElement(By.xpath("/html/body/div[3]/div/div[5]/div/div[2]/div[3]/div[1]/div[1]/div/select")));
	    s.selectByVisibleText(values);
		    
	}

	@When("^Clicks Asc/Desc arrow$")
	public void clicks_Asc_Desc_arrow() throws Throwable {
		
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".category-products > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(3)")).click();
	}

	@When("^selects View As$")
	public void selects_View_As() throws Throwable {
	    
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".category-products > div:nth-child(1) > div:nth-child(1) > p:nth-child(1) > a:nth-child(3)")).click();
		
	}

	@Then("^Products should be shown according to the Sorting$")
	public void products_should_be_shown_according_to_the_Sorting() throws Throwable {
	    
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,500)");
		
		screenshot("PDPafter",".png");
		
		Thread.sleep(2000);
		driver.close();
	}


	@When("^User selects per page value$")
	public void user_selects_per_page_value() throws Throwable {
	    
		Select s = new Select(driver.findElement(By.cssSelector(".category-products > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > select:nth-child(2)")));
	    s.selectByVisibleText("24");
	}

	@Then("^Selected number of products if present will show up$")
	public void selected_number_of_products_if_present_will_show_up() throws Throwable {
	  
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,500)");
		screenshot("ProductsPresentPDP",".png");
		
		Thread.sleep(2000);
		driver.close();
	}

	@When("^User clicks 'Add to Basket' for any product$")
	public void user_clicks_Add_to_Basket_for_any_product() throws Throwable {
	    
		driver.findElement(By.xpath("/html/body/div[3]/div/div[5]/div/div[2]/div[3]/ul/li[1]/div/h2/a")).click();
		driver.findElement(By.cssSelector(".add-to-cart-buttons > button:nth-child(1)")).click();
		
				
		Switch();
		driver.findElement(By.cssSelector("span.button:nth-child(2)")).click();
			
		Thread.sleep(2000);
		driver.navigate().back();
		
		driver.findElement(By.cssSelector("li.item:nth-child(2) > div:nth-child(2) > div:nth-child(3) > button:nth-child(1)")).click();
		
		}

	@Then("^Item should get added to the Basket$")
	public void item_should_get_added_to_the_Basket() throws Throwable {
	   
		Thread.sleep(1000);
		screenshot("ProductAddedPDP",".png");
		
		Thread.sleep(2000);
		driver.close();
	}

	@When("^User clicks 'Add to Wishlist' for any product$")
	public void user_clicks_Add_to_Wishlist_for_any_product() throws Throwable {
	    
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,400)");
		driver.findElement(By.xpath("/html/body/div[3]/div/div[5]/div/div[2]/div[3]/ul/li[3]/div/div[2]/ul/li")).click();
		
	}

	
	@Then("^For Guest user it should redirect to login page first$")
	public void for_Guest_user_it_should_redirected_to_login_page_first() throws Throwable {
		
		String title = driver.getTitle();
		System.out.println("We are at" + title + " page");
		
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("himanshi.sharma@williamscommerce.com");
	    driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("tester@111");
	    driver.findElement(By.cssSelector("#send2")).click();
				
		String text = driver.getTitle();
		System.out.println("We are at" + text + " page Now");
		
		screenshot("AddedToWishlist",".png");
		
		Thread.sleep(2000);
		driver.close();
	}
	
	 //----------------------Seventh Scenario--------------------------//
	
	@Given("^I am at Product Details page$")
	public void i_am_at_Product_Details_page() throws Throwable {
	   
		Homepage();
		
		driver.findElement(By.cssSelector(".watches")).click();
		driver.findElement(By.cssSelector(".nav-6-1-3 > a:nth-child(1)")).click();
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,500)");
		
		driver.findElement(By.cssSelector("#product-collection-image-16683")).click();
		Thread.sleep(2000);
		}

	@Given("^fills mandatory fields & Changes Qty$")
	public void fills_mandatory_fields_Changes_Qty() throws Throwable {
	   
		Select s = new Select(driver.findElement(By.cssSelector("#attribute198")));
		s.selectByVisibleText("ROYAL AIR FORCE");
		
		driver.findElement(By.cssSelector("div.qty-minusplus:nth-child(4)")).click();
		
	}

	@When("^I click Add to Basket button$")
	public void i_click_Add_to_Basket_button() throws Throwable {
		
		driver.findElement(By.cssSelector(".btn-cart > span:nth-child(1) > span:nth-child(1)")).click();
	}

	@Then("^Product successfully added message should be shown$")
	public void product_successfully_added_message_should_be_shown() throws Throwable {
	   
		Switch();
		String msg = driver.findElement(By.cssSelector("#added_item_name")).getText();
		System.out.println(msg);
		
		Thread.sleep(2000);
		driver.close();	
	}

	@When("^I clicks Add to Wishlist for any product$")
	public void i_clicks_Add_to_Wishlist_for_any_product() throws Throwable {
	   
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,500)");
		driver.findElement(By.cssSelector(".link-wishlist > span:nth-child(2)")).click();
	}
	
	@Then("^Product should get added to Wishlist$")
	public void product_should_get_added_to_Wishlist() throws Throwable {
		
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("himanshi.sharma@williamscommerce.com");
	    driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("tester@111");
	    driver.findElement(By.cssSelector("#send2")).click();
	    
		screenshot("ToWishlistPDP",".png");
		
	}

	@Then("^For Guest user redirected to login page first$")
	public void for_Guest_user_redirected_to_login_page_first() throws Throwable {
		
		String a = driver.getTitle();
		System.out.println(a);
		
		Thread.sleep(2000);
		driver.close();
				
	}

	@When("^User clicks any Sharing option$")
	public void user_clicks_any_Sharing_option() throws Throwable {
	    
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,500)");
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".link-facebook > span:nth-child(1)")).click();
		
	    driver.findElement(By.cssSelector(".icon-pinterest")).click();
	}

	@Then("^relevant social site should open in new Tab$")
	public void relevant_social_site_should_open_in_new_Tab() throws Throwable {
	
		screenshot("SocialSite",".png");

		Thread.sleep(2000);
		driver.close();
	}

    //-----------------Eighth Scenario---------------------//
	
	@Given("^User is at Shopping Cart page$")
	public void user_is_at_Shopping_Cart_page() throws Throwable {
	   
		SignIn();
		
		driver.findElement(By.cssSelector(".jewellery")).click();
		driver.findElement(By.cssSelector(".nav-7-10 > a:nth-child(1)")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("li.item:nth-child(2) > div:nth-child(2) > div:nth-child(3) > button:nth-child(1)")).click();
		
		Switch();
		driver.findElement(By.cssSelector("span.button:nth-child(3)")).click();
		
		//driver.findElement(By.cssSelector("a.button:nth-child(2)")).click();
		
	}

	@When("^User enters Promo code in Discounted Codes field & Apply$")
	public void user_enters_Promo_code_in_Discounted_Codes_field_Apply() throws Throwable {
	    
		WebElement a =driver.findElement(By.cssSelector("#coupon_code"));
		a.clear();
		a.sendKeys("LB91");
		driver.findElement(By.xpath("/html/body/div[3]/div/div[5]/div/div/div/div[2]/form[1]/div/div/div/div/button/span/span")).click();
		
		String h = driver.findElement(By.cssSelector(".success-msg > ul:nth-child(1) > li:nth-child(1) > span:nth-child(1)")).getText();
		System.out.println(h);
		
	}

	@Then("^Discounted Amount should be shown below Subtotal$")
	public void discounted_Amount_should_be_shown_below_Subtotal() throws Throwable {
	    
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,700)");
		
		String s =driver.findElement(By.cssSelector(".cart-totals")).getText();
		System.out.println(s);
			
	}

	@Then("^Should be reflected in GRAND TOTAL$")
	public void should_be_reflected_in_GRAND_TOTAL() throws Throwable {
	    
		screenshot("DiscountedSummary",".png");
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.field-wrapper:nth-child(3) > div:nth-child(2) > button:nth-child(2) > span:nth-child(1) > span:nth-child(1)")).click();
		
		Thread.sleep(2000);
		driver.close();
	}

	
	@Given("^User has selected few items from Product page$")
	public void user_has_selected_few_items_from_Product_page() throws Throwable {
	  
		System.out.println("yeee");
	}

	
	@When("^User checks Shopping Cart page$")
	public void user_checks_Shopping_Cart_page() throws Throwable {
	   
		System.out.println("shopping page");
		
	}
	@Then("^Discounted Amount shows below Subtotal$")
	public void Discounted_Amount_shows_below_Subtotal() throws Throwable {
	
		System.out.println("Done");
		
		Thread.sleep(2000);
		driver.close();
	}
	
	
	//---------------------Ninth Scenario--------------------------------//
	
	@Given("^User has added items to the cart$")
	public void user_has_added_items_to_the_cart() throws Throwable {
	    
		Homepage();
		
		driver.get("https://award.wcltest.com/medals/gallant-and-distinguished-conduct/miniature");
		driver.findElement(By.cssSelector("li.item:nth-child(2) > div:nth-child(2) > div:nth-child(3) > button:nth-child(1) > span:nth-child(1) > span:nth-child(1)")).click();
		
		Switch();
		driver.findElement(By.cssSelector(".close-button-popup")).click();  
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("li.item:nth-child(3) > div:nth-child(2) > div:nth-child(3) > button:nth-child(1) > span:nth-child(1) > span:nth-child(1)")).click();
		Switch();
		driver.findElement(By.cssSelector("span.button:nth-child(3)")).click();  
	}

	
	@Given("^is redirected to Checkout page$")
	public void is_redirected_to_Checkout_page() throws Throwable {
	    
		driver.findElement(By.cssSelector(".top > li:nth-child(1) > button:nth-child(1) > span:nth-child(1) > span:nth-child(1)")).click();
	    
	}

	@When("^User choose to Continue with Checkout as Guest option$")
	public void user_choose_to_Continue_with_Checkout_as_Guest_option() throws Throwable {
		
		driver.findElement(By.cssSelector("#onepage-guest-register-button")).click();
		
	}

	@Then("^User fill Billing Information section$")
	public void user_fill_Billing_Information_section() throws Throwable {
	   
		driver.findElement(By.xpath("//*[@id='billing:prefix']")).sendKeys("Dr");  
		driver.findElement(By.xpath("//*[@id='billing:firstname']")).sendKeys("Life");
		driver.findElement(By.xpath("//*[@id='billing:lastname']")).sendKeys("Beautiful");
		driver.findElement(By.xpath("//*[@id='billing:email']")).sendKeys("Rangoli@wcl.com");
		driver.findElement(By.xpath("//*[@id='billing:street1']")).sendKeys("454 new street");
		driver.findElement(By.xpath("//*[@id='billing:city']")).sendKeys("London");
		
		driver.findElement(By.xpath("//*[@id='billing:postcode']")).sendKeys("LE 23");
		driver.findElement(By.xpath("//*[@id='billing:telephone']")).sendKeys("548885487");
	    
		driver.findElement(By.cssSelector("li.control:nth-child(3) > label:nth-child(2)")).click();
		driver.findElement(By.cssSelector("#billing-buttons-container > button:nth-child(2)")).click();
		Thread.sleep(3000);
		
	}

	@Then("^Add new Shipping Address$")
	public void add_new_Shipping_Address() throws Throwable {
	   
		WebElement pre = driver.findElement(By.xpath("//*[@id='shipping:prefix']"));
		pre.clear();
		pre.sendKeys("Mr");  
		
		WebElement name = driver.findElement(By.xpath("//*[@id='shipping:firstname']"));
		name.clear();
		name.sendKeys("Namya");
		
		driver.findElement(By.xpath("//*[@id='shipping:lastname']")).sendKeys("QA");
		
		driver.findElement(By.xpath("//*[@id='shipping:street1']")).sendKeys("777 new street");
		driver.findElement(By.xpath("//*[@id='shipping:city']")).sendKeys("London");
		
		driver.findElement(By.xpath("//*[@id='shipping:postcode']")).sendKeys("WC 11");
		driver.findElement(By.xpath("//*[@id='shipping:telephone']")).sendKeys("978878787"); 
		
		driver.findElement(By.cssSelector("button.button:nth-child(3)")).click();
		
	}

	@Then("^Select Shipping Method$")
	public void select_Shipping_Method() throws Throwable {
		
		Thread.sleep(3000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(500,0)");
		
		/*driver.findElement(By.cssSelector("button.button:nth-child(3)")).click();
		Thread.sleep(2000);*/
		driver.findElement(By.xpath("//*[@id='flint_delivery_instructions']")).sendKeys("Testing purpose only");
		driver.findElement(By.cssSelector("#shipping-method-buttons-container > button:nth-child(2)")).click();
		Thread.sleep(2000);
	}

	@Then("^Select Payment Information as Cash On delivery$")
	public void select_Payment_Information_as_Cash_On_delivery() throws Throwable {
		
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(500,0)");
		
		driver.findElement(By.cssSelector("#dt_method_cashondelivery > label:nth-child(2)")).click();
		driver.findElement(By.cssSelector("#payment-buttons-container > button:nth-child(2)")).click();
		Thread.sleep(2000);
	}
	
	@Then("^User Clicks on Place Order button$")
	public void user_Clicks_on_Place_Order_button_and_gets_Order_Confirmation_message() throws Throwable {
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,400)");
		
		screenshot("Order Review",".png");
		driver.findElement(By.cssSelector(".btn-checkout")).click();
	
	}
	
	@Given("^gets redirected to Order Confirmation page$")
	public void gets_redirected_to_Order_Confirmation_page() throws Throwable {
    
		String msg =driver.findElement(By.cssSelector(".col-main > p:nth-child(3)")).getText();
		System.out.println(msg);
		
	}
	
	@Given("^Clicks on Continue Shopping button$")
	public void  clicks_on_Continue_Shopping_button() throws Throwable
	{
		driver.findElement(By.cssSelector(".buttons-set > button:nth-child(1)")).click();
		Thread.sleep(2000);
		String a= driver.getTitle();
		System.out.println("User is at Homepage" + a);
		
		Thread.sleep(1000);
		driver.close();
	}

	@Then("^Select Payment Information as SagePay$")
	public void select_Payment_Information_as_SagePay() throws Throwable {
	    
		driver.findElement(By.cssSelector("#dt_method_sagepayform > label:nth-child(2)")).click();
	}

	@Then("^User Clicks on Place Order button and Redirects to SagePay for Payment$")
	public void user_Clicks_on_Place_Order_button_and_Redirects_to_SagePay_for_Payment() throws Throwable {
		
		
		driver.findElement(By.cssSelector("#payment-buttons-container > button:nth-child(2)")).click();
		Thread.sleep(2000);
		screenshot("SagePayGuest",".png");
		driver.findElement(By.cssSelector(".btn-checkout")).click();
	} 
	

	@Then("^Chooses appropriate options and Places order$")
	public void chooses_appropriate_options_and_Places_order() throws Throwable {
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("li.layout__item:nth-child(1) > form:nth-child(1) > button:nth-child(2) > span:nth-child(2)")).click();
		driver.findElement(By.xpath("//*[@id='form-card_details.field-pan']")).sendKeys("4929000000006");
		driver.findElement(By.xpath("//*[@id='form-card_details.field-expiry_mm']")).sendKeys("12");
		driver.findElement(By.xpath("//*[@id='form-card_details.field-expiry_yy']")).sendKeys("23");
		driver.findElement(By.xpath("//*[@id='form-card_details.field-cvc']")).sendKeys("123");
		driver.findElement(By.cssSelector(".btn--positive")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".btn--positive")).click();
	}

	@Then("^Recieves a Confirmation message$")
	public void recieves_a_Confirmation_message() throws Throwable {
	   
		String a = driver.findElement(By.cssSelector(".page-title > h1:nth-child(1)")).getText();
		System.out.println(a);
		
		Thread.sleep(2000);
		driver.close();
		
	}

	
	
	@When("^User choose to Continue with Register & Checkout option$")
	public void user_choose_to_Continue_with_Register_Checkout_option() throws Throwable {
	    
		driver.findElement(By.cssSelector("ul.form-list:nth-child(3) > li:nth-child(2) > label:nth-child(2)")).click();
		driver.findElement(By.cssSelector("#onepage-guest-register-button")).click();
				
	}
   
	@Then("^User fills Billing Information section$")
	public void user_fills_Billing_Information_section() throws Throwable {
	   
		driver.findElement(By.xpath("//*[@id='billing:prefix']")).sendKeys("Dr");  
		driver.findElement(By.xpath("//*[@id='billing:firstname']")).sendKeys("Nykaa");
		driver.findElement(By.xpath("//*[@id='billing:lastname']")).sendKeys("Jain");
		driver.findElement(By.xpath("//*[@id='billing:email']")).sendKeys("Nikki@wcl.com");
		driver.findElement(By.xpath("//*[@id='billing:street1']")).sendKeys("773 new street");
		driver.findElement(By.xpath("//*[@id='billing:city']")).sendKeys("New Hampshire");
		
		driver.findElement(By.xpath("//*[@id='billing:postcode']")).sendKeys("WC1 12");
		driver.findElement(By.xpath("//*[@id='billing:telephone']")).sendKeys("485784487");
		driver.findElement(By.xpath("//*[@id='billing:customer_password']")).sendKeys("tester@001");
		driver.findElement(By.xpath("//*[@id='billing:confirm_password']")).sendKeys("tester@001");
		
		driver.findElement(By.cssSelector("#billing-buttons-container > button:nth-child(2)")).click();
		Thread.sleep(3000);
		
	}
	
	@Then("^Select Payment Information as COD$")
	public void select_Payment_Information_as_COD() throws Throwable {
	   
		driver.findElement(By.cssSelector("#dt_method_cashondelivery > label:nth-child(2)")).click();
		driver.findElement(By.cssSelector("#payment-buttons-container > button:nth-child(2)")).click();
		Thread.sleep(7000);
	}
	
	@Then("^Recieves an Order Confirmation message$")
	public void recieves_an_Order_Confirmation_message() throws Throwable {
	  
		screenshot("Order Confirmation_Registered",".png");
		
		Thread.sleep(2000);
		driver.close();
		
	}

	@When("^User enters Email Id and Password$")
	public void user_enters_Email_Id_and_Password() throws Throwable {
	   
		driver.findElement(By.cssSelector("#login-email")).sendKeys("himanshi.sharma@williamscommerce.com");
		driver.findElement(By.cssSelector("#login-password")).sendKeys("tester@111");
		driver.findElement(By.cssSelector("div.buttons-set:nth-child(3) > button:nth-child(1)")).click();
	}

	@Then("^User gets redirected to Checkout page$")
	public void user_gets_redirected_to_Checkout_page() throws Throwable {
	  
		String a = driver.findElement(By.cssSelector(".page-title > h1:nth-child(1)")).getText();
		System.out.println(a);
	}

	@Then("^Selects already Added Address$")
	public void selects_already_Added_Address() throws Throwable {
	   
		Select s = new Select(driver.findElement(By.cssSelector("#billing-address-select")));
		s.selectByIndex(1);
	}
	
	@Then("^Continue with Shipping Method$")
	public void continue_with_Shipping_Method() throws Throwable{
		
		driver.findElement(By.cssSelector("#billing-buttons-container > button:nth-child(2)")).click();
		
		driver.findElement(By.cssSelector("button.button:nth-child(3)")).click();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(300,0)");
		
		driver.findElement(By.xpath("//*[@id='flint_delivery_instructions']")).sendKeys("Testing purpose only");
		driver.findElement(By.cssSelector("#shipping-method-buttons-container > button:nth-child(2)")).click();
		Thread.sleep(2000);
	}

//-------------------------Tenth Scenario---------------------------//
	

	
	@Given("^User has logged in and is at My Dashboard$")
	public void user_has_logged_in() throws Throwable {
	    
		SignIn();
		Thread.sleep(2000);		
		String a = driver.getTitle();
		String b = "My Account";
		Assert.assertEquals(b, a);	
		
		Thread.sleep(2000);
		
	}

	@Given("^Adds Item to Wishlist$")
	public void add_Item_to_Wishlist() throws Throwable {
		
		Add();	
		Thread.sleep(2000);
	}
	
	@When("^User clicks Add to Basket for any Item present$")
	public void user_clicks_Add_to_Basket_for_any_Item_present() throws Throwable
	{
		driver.findElement(By.xpath("//*[text()='Add to Basket']")).click();
	}
	
	@Then("^Item should get Added to Basket$")
	public void Item_should_get_Added_to_Basket() throws Throwable
	{
		Switch();
		String a =driver.findElement(By.cssSelector("#added_item_name")).getText();
		System.out.println(a);
	}
	
	@When("^User clicks Edit option for any Item showing$")
	public void user_clicks_Edit_option_for_any_Item_showing() throws Throwable {
	    
		driver.findElement(By.xpath("//*[text() ='Edit'][1]")).click();
	}

	@Then("^User should be redirected to Product details page for Updating the Information$")
	public void user_should_be_redirected_to_Product_details_page_for_Updating_the_Information() throws Throwable {
	    
		
		driver.findElement(By.cssSelector("div.qty-minusplus:nth-child(4)")).click();
		driver.findElement(By.cssSelector(".add-to-cart-buttons > button:nth-child(1)")).click();
		
		Thread.sleep(1000);
		screenshot("EditPage",".png");
		
		Thread.sleep(2000);
		driver.close();
	}

	@When("^User enters Comment & increment/decrement QTY for multiple products$")
	public void user_enters_Comment_increment_decrement_QTY_for_multiple_products() throws Throwable {
	   
		driver.findElement(By.cssSelector("div.block-content:nth-child(2) > ul:nth-child(1) > li:nth-child(5) > a:nth-child(1)")).click();
		Thread.sleep(2000);
	
		WebElement a = driver.findElement(By.cssSelector("#item_893 > td:nth-child(3) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)"));
		a.clear();
		a.sendKeys("2");
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,200)");
		
		driver.findElement(By.xpath("//textarea[@title='Comment']")).sendKeys("Test test");
		WebElement b = driver.findElement(By.cssSelector("#item_895 > td:nth-child(3) > div:nth-child(1) > div:nth-child(1) > input:nth-child(1)"));
		b.clear();
		b.sendKeys("4");
		Thread.sleep(2000);
	}

	@When("^clicks Update Wishlist button$")
	public void clicks_Update_Wishlist_button() throws Throwable {
	   
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,300)");
	
		driver.findElement(By.cssSelector("button.button:nth-child(3)")).click();
	}

	@Then("^All the changes made should be Updated$")
	public void all_the_changes_made_should_be_Updated() throws Throwable {
	    
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,500)");
		
		screenshot("UpdatedWishlist",".png");
		
		Thread.sleep(2000);
		driver.close();
	}

	@When("^User clicks Delete icon for any product$")
	public void user_clicks_Delete_icon_for_any_product() throws Throwable {
	   
		driver.findElement(By.cssSelector("div.block-content:nth-child(2) > ul:nth-child(1) > li:nth-child(5) > a:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@title='Remove Item']")).click();
	}

	@Then("^A pop up should appear to Confirm deletion$")
	public void a_pop_up_should_appear_to_Confirm_deletion() throws Throwable {
		
		Thread.sleep(1000);
		screenshot("DeleteConfirmationPopUp",".png");
		
		driver.switchTo().alert().accept();
		
	}

	@Then("^On clicking Ok Item should be removed from the Wishlist$")
	public void on_clicking_Ok_Item_should_be_removed_from_the_Wishlist() throws Throwable {
	   
		Thread.sleep(2000);
		driver.close();
		
	}

	@Given("^clicks Share Wishlist button$")
	public void clicks_Share_Wishlist_button() throws Throwable {
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.block-content:nth-child(2) > ul:nth-child(1) > li:nth-child(5) > a:nth-child(1)")).click();
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,200)");
		
		driver.findElement(By.cssSelector(".btn-share > span:nth-child(1) > span:nth-child(1)")).click();
		
	}

	@When("^User Enters valid \"([^\"]*)\" & \"([^\"]*)\"$")
	public void user_Enters_valid(String EmailId, String Message) throws Throwable {
	    
		driver.findElement(By.cssSelector("#email_address")).sendKeys(EmailId);
		driver.findElement(By.cssSelector("#message")).sendKeys(Message);
		
	}

	@When("^clicks Share wishlist button$")
	public void clicks_Share_wishlist_button() throws Throwable {
	    
		driver.findElement(By.cssSelector("button.button:nth-child(3)")).click();
	}

	@Then("^Wishlist shared successfully message should be shown$")
	public void wishlist_shared_successfully_message_should_be_shown() throws Throwable {
	    
		String a=  driver.findElement(By.cssSelector(".messages")).getText();
		System.out.println(a);
		
		
		
		Thread.sleep(2000);
		driver.close();
	}
	
	@When("^User clicks Add All to Cart button$")
	public void user_clicks_Add_All_to_Cart_button() throws Throwable {
	    
		driver.findElement(By.cssSelector("div.block-content:nth-child(2) > ul:nth-child(1) > li:nth-child(5) > a:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".btn-add")).click();
	}

	@Then("^All Items should get added to Cart and No item should be there in My Wishlist$")
	public void all_Items_should_get_added_to_Cart_and_No_item_should_be_there_in_My_Wishlist() throws Throwable {
	   
		String h =  driver.findElement(By.cssSelector(".success-msg > ul:nth-child(1) > li:nth-child(1)")).getText();
	    System.out.println(h);
	    
	    Thread.sleep(2000);
		driver.close();
	}

	//-------------------------Eleventh Scenario---------------------------//
	
	@Given("^Scrolls down to Footer section$")
	public void scrolls_down_to_Footer_section() throws Throwable {
	    
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,5000)");
		
	}

	@Given("^User enters Valid Email Id in Subscription textfield$")
	public void user_enters_Valid_Email_Id_in_Subscription_textfield() throws Throwable {
	  
		driver.findElement(By.cssSelector("#newsletter")).sendKeys("ash@wcl.com");
		
	}

	@Given("^clicks Sign Up$")
	public void clicks_Sign_Up() throws Throwable {
	    
		driver.findElement(By.cssSelector(".actions > button:nth-child(1)")).click();
	}

	@Then("^User should get a Thankyou message$")
	public void user_should_get_a_Thankyou_message() throws Throwable {
	   
		String a = driver.findElement(By.cssSelector(".success-msg > ul:nth-child(1) > li:nth-child(1) > span:nth-child(1)")).getText();
		System.out.println(a);
		
		Thread.sleep(2000);
		driver.close();
	}

	@Given("^User clicks on Request a Catalogue image$")
	public void user_clicks_on_Request_a_Catalogue_image() throws Throwable {
	   
		driver.findElement(By.cssSelector("/html/body/div[3]/div/div[7]/div/div[1]/div/a/img")).click();
		
	}

	@When("^Fills mandatory fields in the Form$")
	public void fills_mandatory_fields_in_the_Form() throws Throwable {
	   
		driver.findElement(By.cssSelector("#fieldHWnbGY20")).sendKeys("Neha");
	    driver.findElement(By.cssSelector("#fieldHWnbGY23")).sendKeys("12 hall near London square");
	    driver.findElement(By.cssSelector("#fieldHWnbGY24")).sendKeys("WC 11");
	    
	    Select a = new Select(driver.findElement(By.cssSelector("#fieldHWnbGY25")));
	    a.selectByVisibleText("Veterans Association");
	    
	}	

	@When("^Submit the form$")
	public void submit_the_form() throws Throwable {
	   
		driver.findElement(By.cssSelector("#webform_EZ2ZHi_submit_button")).click();
	}

	@Given("^User clicks on Contact Us link & Redirects to Contact Us page$")
	public void user_clicks_on_Contact_Us_link_Redirects_to_Contact_Us_page() throws Throwable {
	    
		driver.findElement(By.cssSelector("div.col-3:nth-child(3) > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1)")).click();
		Thread.sleep(2000);
	}

	@When("^User Fill mandatory fields in the Form & Submit it$")
	public void user_Fill_mandatory_fields_in_the_Form_Submit_it() throws Throwable {
	    
	}
	
	
}
