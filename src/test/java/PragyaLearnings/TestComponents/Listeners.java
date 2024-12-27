package PragyaLearnings.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import PragyaLearnings.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //saying thread safe means no matter even if you run concurrently,each object creation have its own thread
                                                 // so it won't interrupt the other overriding variable.

	@Override
	    public void onTestStart(ITestResult result) {
		 
	      test = extent.createTest(result.getMethod().getMethodName());
	       
	      extentTest.set(test);//so that every test will get Unique thread number to execute test and will not override 
	      
	      
	
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	    	extentTest.get().log(Status.PASS, "Test Passed");
	    }

		/*
		 * @Override public void onTestFailure(ITestResult result) {//ITestResult class
		 * with object result captures Which TC is failed //test.log(Status.FAIL,
		 * "Test Failed"); extentTest.get().fail(result.getThrowable()); //it will give
		 * the error of failed test. //Screenshot code(where TC has failed) //Response
		 * if API get failed
		 * 
		 * //step1 - take screenshot step2- attach it to the report try {
		 * driver=(WebDriver) result.getTestClass().getRealClass().getField("driver")
		 * .get(result.getInstance()); }catch (Exception e1) { e1.printStackTrace(); }
		 * 
		 * String filepath = null; try { filepath =
		 * getScreenshot(result.getMethod().getMethodName(),driver); //sending driver to
		 * screenshot to start } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 * 
		 * 
		 * extentTest.get().addScreenCaptureFromPath(filepath,
		 * result.getMethod().getMethodName());
		 * 
		 * }
		 */// Trycatch illustrates = That means if screenshot does not exist,it prints in the output, saying that there is no path present. 

	    @Override
	    public void onTestSkipped(ITestResult result) { 
	        //System.out.println("Test Skipped: " + result.getName());
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        // Not commonly used
	    }

	    @Override
	    public void onStart(ITestContext context) {
	        //System.out.println("Start of Execution: " + context.getName());
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        //System.out.println("End of Execution: " + context.getName());
	    	
	    	
	          extent.flush();
	    }
	
	
}
