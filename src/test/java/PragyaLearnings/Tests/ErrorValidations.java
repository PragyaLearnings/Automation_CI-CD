package PragyaLearnings.Tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import PragyaLearnings.TestComponents.BaseTest;
import PragyaLearnings.TestComponents.Retry;
import PragyaLearnings.Tests.pageobjects.CartPage;
import PragyaLearnings.Tests.pageobjects.CheckoutPage;
import PragyaLearnings.Tests.pageobjects.ConfirmationPage;
import PragyaLearnings.Tests.pageobjects.LandingPage;
import PragyaLearnings.Tests.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest{
	
	
@Test(groups = {"ErrorHandling"} , retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException {
		String productname = "ZARA COAT 3";
		
		landingPage.LoginApplication("prag@gmail.com","Johr@10");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
		
	    
	    
	
	}
@Test
public void ProductErrorValidation() throws InterruptedException, IOException {
	String productname = "ZARA COAT 3";
	
	PragyaLearnings.pageobjects.ProductCatalogue productcatalogue = landingPage.LoginApplication("prag@gmail.com","Johri@10");
	
	List<WebElement> products = productcatalogue.getProductList();
	productcatalogue.AddToCart(productname);
	PragyaLearnings.pageobjects.CartPage cartpage = productcatalogue.goToCartPage();
	
	Boolean match = cartpage.verifyProuductDisplay("ZARA COAT 3");
	Assert.assertTrue(match);
	
    
    

}

}
