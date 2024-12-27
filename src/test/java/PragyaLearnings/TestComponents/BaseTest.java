package PragyaLearnings.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PragyaLearnings.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected WebDriver driver;
	public LandingPage landingPage;
	public WebDriver InitializeDriver() throws IOException {
		
		
		Properties prop = new Properties();
	
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\PragyaLearnings\\Resources\\GlobalData.properties");
		prop.load(fis);
		String BrowserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		// Setting browser from maven command , if system not getting any command from server to run on which browser then read 
		// value from global properties file
		// prop.getProperty("browser");
		
		if(BrowserName.contains("Edge")) {
			//to run code in headless mode 
			EdgeOptions options = new EdgeOptions();
			WebDriverManager.edgedriver().setup();
			if(BrowserName.contains("headless")) {
			options.addArguments("headless");
			} //when we will provide command in maven or in jenkins
			 driver = new EdgeDriver(options);
			//driver = new EdgeDriver();
			 driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(BrowserName.equalsIgnoreCase("firefox")){
			//firefox
		}
		else
		 //chrome
		
			
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		return driver;
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunchApplication() throws IOException {
		driver = InitializeDriver();		
	    landingPage = new PragyaLearnings.pageobjects.LandingPage(driver); //object create
		landingPage.goTo();
		return landingPage;
	}
     @AfterMethod(alwaysRun=true)
     public void TearDown() {
    	 driver.close();
     }
   //if test fails , then to take screenshot and attach it to html report
     public String getScreenshot(String testCaseName , WebDriver driver) throws IOException {
    		TakesScreenshot ts = (TakesScreenshot)driver;
    		File source = ts.getScreenshotAs(OutputType.FILE);
    		File file = new File(System.getProperty("user.dir") +"//reports//" + testCaseName + ".png");
    		FileUtils.copyFile(source, file);
    		return System.getProperty("user.dir") +"//reports//" + testCaseName + ".png";
    		}
     
     
     
     
     public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException {
 		//Read Json to string
 		String JsonContent = FileUtils.readFileToString(new File(FilePath),
 		StandardCharsets.UTF_8); //--> to what string we have to encode this file
 		
 		// String to HashMap-->have Jackson Databind Dependency
 		
 		ObjectMapper mapper = new ObjectMapper();
 		List<HashMap<String,String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){
 		});
 		return data;
 		
 	}
}
