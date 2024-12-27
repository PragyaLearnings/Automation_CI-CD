package Salesforce;



import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SwitchToOCEO extends InternalWait{
     
	
	WebDriver driver;
	public SwitchToOCEO(WebDriver driver, String AppName) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css=".navItem span")
	List<WebElement> Apps;
	
	//public void OCEOLogin(String AppName) throws InterruptedException {
		//LoginOCEO(AppName);
		
	//}
	
	public List<WebElement> AppList() {
	return Apps;
	}
	
	public void EmployeesTab(String AppName) throws InterruptedException {
		LoginOCEO(AppName);
		//WebElement prod = getProductList().stream().filter(product->
		//product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		//Long c = names.stream().filter(s->s.startsWith("A")).count();
		
		WebElement App = AppList().stream().filter(Apps->Apps.getText().equals("Employees")).findFirst().orElse(null);
		System.out.println(App);
	
		for(int i = 0; i<Apps.size(); i++) {
			System.out.println(Apps.get(i).getText());
			
			
			
		}
		System.out.println("Done");
	}
}
