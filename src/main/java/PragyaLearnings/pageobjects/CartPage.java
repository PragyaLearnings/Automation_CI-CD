package PragyaLearnings.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PragyaLearnings.Tests.AbstractComponents.AbstractComponent;

public class CartPage extends PragyaLearnings.AbstractComponent.AbstractComponent {

	WebDriver driver;
    //initialization
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); 
	}
	@FindBy(css=".totalRow button")
	WebElement CheckoutEle;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitle;
	
	//Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productname));
	public boolean verifyProuductDisplay(String productname) {
		Boolean match = productTitle.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productname));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		scrollDown();
		CheckoutEle.click();
		CheckoutPage checkoutpage = new CheckoutPage(driver);
		return checkoutpage;
	}

	

	
}


