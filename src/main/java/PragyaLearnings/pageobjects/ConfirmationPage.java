package PragyaLearnings.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import PragyaLearnings.Tests.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends PragyaLearnings.AbstractComponent.AbstractComponent {
     WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
    //String confirmMessage = driver.findElement(By.className("hero-primary")).getText();
	@FindBy(className="hero-primary")
	WebElement Message;
	
	public String GetConfirmationMessage() {
		 return Message.getText();
	}
}