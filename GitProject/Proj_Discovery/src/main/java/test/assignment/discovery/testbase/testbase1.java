package test.assignment.discovery.testbase;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * @author ankit.gaurav
 *
 */
public class testbase1 {
	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;
	
}
