package Salesforce;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InternalWait {
      
	WebDriver driver;
	
	public InternalWait(WebDriver driver) {
		this.driver =driver ;
		
	    PageFactory.initElements(driver,this);
		
	}
	
	
	@FindBy(css=".appLauncher")	
	WebElement AppLauncher;
	
	@FindBy(css=".slds-input")
	WebElement SearchApp;
	
	@FindBy(css="span p")
	WebElement Apps;
	
	public void WaitToAppear() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		
	}
	
	
	public void LoginOCEO(String AppName) throws InterruptedException {
		Thread.sleep(80);
		driver.findElement(By.cssSelector("class*=.appLauncher")).click();
		SearchApp.sendKeys(AppName);
		WaitToAppear();
		Apps.click();
		
	}
	
	
}

