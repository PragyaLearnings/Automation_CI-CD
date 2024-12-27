package PragyaLearnings.Tests;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SeleniumFrameworkDesign.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		String productname = "ZARA COAT 3";
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		//pageobjects.LandingPage LandingPage = new pageobjects.LandingPage(driver); //object create
		driver.findElement(By.id("userEmail")).sendKeys("prag@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Johri@10");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	WebElement prod = products.stream().filter(product->
	product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); //bottom green bar
	//ng-animating (Buffering)
	//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
    Thread.sleep(3000);
	driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	
	
	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productname));
	Assert.assertTrue(match);
	JavascriptExecutor js= (JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,600)"); //Scroll page by using javascript.
	Thread.sleep(3000);
	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	Actions a = new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	driver.findElement(By.xpath("//button[contains(@class , 'ta-item')][2]")).click();
	driver.findElement(By.cssSelector(".action__submit")).click();
	
	String confirmMessage = driver.findElement(By.className("hero-primary")).getText();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	System.out.println(confirmMessage);
	driver.close();
	
	
	}

}//https://rahulshettyacademy.com/client/auth/login
