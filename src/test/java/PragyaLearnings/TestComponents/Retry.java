package PragyaLearnings.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
    int count = 0;
    int maxtry = 1;
	@Override
	public boolean retry(ITestResult result) { //this is for to rerun the test case when any test case we think is failed due to some flaky
		// TODO Auto-generated method stub     //reason
		
		if(count<maxtry) {
			count++;
			return true;
		}
		
		return false;
	}
	
}
// if want to rerun the test case then need to add this "retryAnalyzer = Retry.class" inside test or group