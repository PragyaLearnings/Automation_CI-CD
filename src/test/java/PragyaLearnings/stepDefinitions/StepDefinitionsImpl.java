package PragyaLearnings.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import PragyaLearnings.TestComponents.BaseTest;
import PragyaLearnings.pageobjects.ConfirmationPage;
import PragyaLearnings.pageobjects.LandingPage;
import PragyaLearnings.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionsImpl extends BaseTest{
public LandingPage landingPage;
public ProductCatalogue productcatalogue;	
public ConfirmationPage confirmationpage;	

	@Given ("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = LaunchApplication();
	}
	
	@Given ("logged in with username (.+) and password (.+)$") //--> Regular expression--> where we need to data to use
	public void logged_in__with_username_and_password(String username, String password) {
		 productcatalogue = landingPage.LoginApplication(username,password);
	}
	
	
	@When ("^I add product(.+) to Cart$")
	public void add_product_to_cart(String productName) {
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.AddToCart(productName);
	}
	 @And ("^Checkout (.+) and submit the order$")//we can use when also in place of And
	 public void Checkout_and_submit_order(String productName) {
		 PragyaLearnings.pageobjects.CartPage cartpage = productcatalogue.goToCartPage();
			
			Boolean match = cartpage.verifyProuductDisplay(productName);
			Assert.assertTrue(match);
			PragyaLearnings.pageobjects.CheckoutPage checkoutpage = cartpage.goToCheckout();
			checkoutpage.checkout("India");
			confirmationpage = checkoutpage.SubmitOrder();
	 }
	 
	 @Then ("{string} message is displayed on ConfirmationPage")
	 public void message_displayed_on_confirmationPage(String string) {
		 String confirmMessage  = confirmationpage.GetConfirmationMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
			System.out.println(confirmMessage);
			driver.close();
	 }
	 @Then ("{string} message is displayed")
	 public void Message_displayed(String string) {
		
	 Assert.assertEquals(string, landingPage.getErrorMessage());
	 driver.close();	
	 }
}
