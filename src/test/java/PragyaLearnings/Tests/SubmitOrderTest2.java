package PragyaLearnings.Tests;

import java.io.IOException;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PragyaLearnings.TestComponents.BaseTest;
import PragyaLearnings.pageobjects.CartPage;
import PragyaLearnings.pageobjects.CheckoutPage;
import PragyaLearnings.pageobjects.ConfirmationPage;
import PragyaLearnings.pageobjects.LandingPage;
import PragyaLearnings.pageobjects.ProductCatalogue;
import PragyaLearnings.pageobjects.OrderPage;
import java.io.File;
public class SubmitOrderTest2 extends BaseTest{
	String productName = "ZARA COAT 3";
	
@Test (dataProvider = "getData", groups = {"Purchase"})//add groups to only run Purchase related TCs
	public void SubmitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		//String productname = "ZARA COAT 3";
		
		PragyaLearnings.pageobjects.ProductCatalogue productcatalogue = landingPage.LoginApplication(input.get("email"),input.get("password"));
		
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.AddToCart(input.get("productName"));
		PragyaLearnings.pageobjects.CartPage cartpage = productcatalogue.goToCartPage();
		
		Boolean match = cartpage.verifyProuductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		PragyaLearnings.pageobjects.CheckoutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.checkout("India");
		PragyaLearnings.pageobjects.ConfirmationPage  confirmationpage = checkoutpage.SubmitOrder();
		String confirmMessage = confirmationpage.GetConfirmationMessage();
		System.out.println(confirmMessage); 
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	    
	    
	
	}
@Test(dependsOnMethods= {"SubmitOrder"})
public void OrderHistoryTest() {
	PragyaLearnings.pageobjects.ProductCatalogue productcatalogue = landingPage.LoginApplication("prag@gmail.com","Johri@10");
	OrderPage orderpage = productcatalogue.goToOrdersPage();
	Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
	
}






@DataProvider
public Object[][] getData() throws IOException { 
	/*
	 * HashMap<String,String> map = new HashMap<String, String >(); map.put("email",
	 * "prag@gmail.com"); map.put("password", "Johri@10"); map.put("productName",
	 * "ZARA COAT 3");
	 * 
	 * HashMap<String,String> map1 = new HashMap<String, String >();
	 * map1.put("email", "RShetty@gmail.com"); map1.put("password", "IamKing@000");
	 * map1.put("productname", "ADIDAS ORIGINAL"); return new Object[][]
	 * {{map},{map1}};
	 */
	
	//return new Object[][] {{"prag@gmail.com","Johri@10","ZARA COAT 3"},{"RShetty@gmail.com","IamKing@000","ADIDAS ORIGINAL"}}; //Object = is generic that can be any data type either string, int etc. 
	//first curly brackets represents one dataset
	
	
List<HashMap<String,String>> data= 	getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\PragyaLearnings\\Data\\PurchaseOrder.json");
return new Object[][] {{data.get(0)},{data.get(1)}};
}





//@DataProvider
//public Object[][] getData() { 
	//return new Object[][] {{"prag@gmail.com","Johri@10","ZARA COAT 3"},{"RShetty@gmail.com","IamKing@000","ADIDAS ORIGINAL"}}; //Object = is generic that can be any data type either string, int etc. 
	// first curly brackets represents one dataset
//}
}