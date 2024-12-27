package PragyaLearnings.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import PragyaLearnings.Tests.AbstractComponents.AbstractComponent;

public class CheckoutPage extends PragyaLearnings.AbstractComponent.AbstractComponent {

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(xpath="//button[contains(@class , 'ta-item')][2]")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By ResultedCountry = By.cssSelector(".ta-results");
	
	public void checkout(String CountryName ) {
	Actions a = new Actions(driver);
	a.sendKeys(Country, CountryName).build().perform();
	waitForElementToAppear(ResultedCountry);
	selectCountry.click();
	
	
	}
	public ConfirmationPage SubmitOrder() {
		scrollToBottom();
		 submit.click();
		 return new ConfirmationPage(driver);
	}
}
