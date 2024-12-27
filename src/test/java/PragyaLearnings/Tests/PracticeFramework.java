package PragyaLearnings.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class PracticeFramework {
	
	public static void main(String[] args) throws InterruptedException {
	 String ProdName = "ZARA COAT 3";
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("prag@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Johri@10");
		driver.findElement(By.id("login")).click();
	
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
	    List <WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	    WebElement prod = products.stream().filter(product->
	    product.findElement(By.cssSelector("b")).getText().equals(ProdName)).findFirst().orElse(null);
	    prod.findElement(By.xpath("//div[@class=\"card-body\"]/button[2]")).click();
	    
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
	    driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
        
       
         
	    List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(ProdName));
		Assert.assertTrue(match);
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,6000)"); //Scroll page by using javascript.
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".totalRow button")).click();
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build().perform();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ta-results list-group ng-star-inserted")));
        driver.findElement(By.xpath("//section/button[1]")).click();
        
        
        driver.findElement(By.cssSelector("a[class='submit']")).click();
		String ConfirmMssg = driver.findElement(By.className("hero-primary")).getText();
		Assert.assertTrue(ConfirmMssg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(ConfirmMssg);
		driver.close();
	}

}
