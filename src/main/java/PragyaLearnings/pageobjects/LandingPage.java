package PragyaLearnings.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PragyaLearnings.Tests.AbstractComponents.AbstractComponent;

public class LandingPage extends PragyaLearnings.AbstractComponent.AbstractComponent {

	WebDriver driver;
    //initialization
	public LandingPage(WebDriver driver) {
		super(driver); //sending driver to parentclass(AbstractComponent)
		this.driver = driver;
		PageFactory.initElements(driver, this); //it uses to execute all driver related locators 
	}
	
	//WebElement userEmail  = driver.findElement(By.id("userEmail")) ;
	//Pagefactory = used to reduce the syntax of creating WebElement
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement ErrorMessage;

	//ActionMethods
	public ProductCatalogue LoginApplication(String email,String password3) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password3);
		submit.click();
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		return productcatalogue;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear (ErrorMessage);
		return ErrorMessage.getText();
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client"); 

	}
	
}


