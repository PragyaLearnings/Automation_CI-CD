package Salesforce;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSalesForce extends InternalWait {

	
	WebDriver driver;
	public LoginSalesForce(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="#username")
	WebElement userName;
	
	@FindBy(css="#password")
	WebElement passWord;
	
	@FindBy(css="#Login")
	WebElement login;
	
	@FindBy(css="#cancel-button")
	WebElement cancel;
	
	public void goTo() {
		driver.get("https://test.salesforce.com/");
		
	}
	public void login(String username, String password) {
		userName.sendKeys(username);
		passWord.sendKeys(password);
		login.click();
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,600)");
		cancel.click();
		WaitToAppear();
	}
	
}

