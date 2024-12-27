package PragyaLearnings.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PragyaLearnings.Tests.pageobjects.CartPage;
import PragyaLearnings.pageobjects.CheckoutPage;

public class AbstractComponent {
	 WebDriver driver;
      public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
      @FindBy(css ="[routerlink*='cart']")
      WebElement CartHeader; 
      
      @FindBy(css ="[routerlink*='cart']")
      WebElement MyOrders;

	public void waitForElementToAppear ( By FindBy) {
    	  
	  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	  wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	  
	  
      }
	public void waitForWebElementToAppear ( WebElement FindBy) {
  	  
		  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		  wait.until(ExpectedConditions.visibilityOf(FindBy));
		  
		  
	      }
	
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		  wait.until(ExpectedConditions.invisibilityOf(ele));
		  
	}
	public PragyaLearnings.pageobjects.CartPage goToCartPage()  {
		//Thread.sleep(3000);
		CartHeader.click();
		PragyaLearnings.pageobjects.CartPage cartpage = new PragyaLearnings.pageobjects.CartPage(driver);
		return cartpage;
		
		

	}
	public PragyaLearnings.pageobjects.OrderPage goToOrdersPage()  {
		//Thread.sleep(3000);
		MyOrders.click();
		PragyaLearnings.pageobjects.OrderPage orderpage = new PragyaLearnings.pageobjects.OrderPage(driver);
		return orderpage;
		
		

	}
	
	
	
	
	
	
	public void scrollDown() {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,2000)"); //Scroll page by using javascript.
		
	}
	public void scrollToBottom() {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
}
