package PragyaLearnings.Tests;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import PragyaLearnings.Tests.pageobjects.CartPage;
import PragyaLearnings.Tests.pageobjects.CheckoutPage;
import PragyaLearnings.Tests.pageobjects.ConfirmationPage;
import PragyaLearnings.Tests.pageobjects.LandingPage;
import PragyaLearnings.Tests.pageobjects.ProductCatalogue;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {
		String productname = "ZARA COAT 3";
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		PragyaLearnings.pageobjects.LandingPage landingPage = new PragyaLearnings.pageobjects.LandingPage(driver); //object creation
		landingPage.goTo();
		PragyaLearnings.pageobjects.ProductCatalogue productcatalogue = landingPage.LoginApplication("prag@gmail.com","Johri@10");
		
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.AddToCart(productname);
		PragyaLearnings.pageobjects.CartPage cartpage = productcatalogue.goToCartPage();
		
		Boolean match = cartpage.verifyProuductDisplay(productname);
		Assert.assertTrue(match);
		PragyaLearnings.pageobjects.CheckoutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.checkout("India");
		PragyaLearnings.pageobjects.ConfirmationPage  confirmationpage = checkoutpage.SubmitOrder();
		String confirmMessage = confirmationpage.GetConfirmationMessage();
		System.out.println(confirmMessage); 
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	    driver.close();
	     
	
	}

}
