package Salesforce;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class SalesforcePractice {
	public static void main(String[] args) throws InterruptedException {
	WebDriver driver = new EdgeDriver();
	driver.manage().window().maximize();
	String AppName = "Optimizer"; 
    LoginSalesForce loginsalesforce = new LoginSalesForce(driver);
    loginsalesforce.goTo();
    loginsalesforce.login("R12_MM_roche_admin@roche-eu-oce.com.fcqa" , "Roche@2024");
   SwitchToOCEO switchtoOCEO = new SwitchToOCEO(driver,AppName);
	//switchtoOCEO.OCEOLogin(AppName);
	switchtoOCEO.EmployeesTab(AppName);
	 driver.close(); 
	}	
	
}

