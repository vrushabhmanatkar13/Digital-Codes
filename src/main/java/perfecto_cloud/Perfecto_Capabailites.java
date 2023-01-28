package perfecto_cloud;

import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.internal.annotations.IBeforeTest;
import org.testng.internal.annotations.ITest;

import com.Digitalcodes.Util.Baseclass;
import com.Digitalcodes.Util.LoadData;
import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResult;
import com.perfecto.reportium.test.result.TestResultFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import perfecto_cloud.PerfectoLabUtils;

public class Perfecto_Capabailites {

	static ReportiumClient reportiumClient;
	static Properties prop;
	

public WebDriver Perfecto(String browserName,String securityToken,String cloudName, String tag) throws Exception {
	
	
	
	DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "10", Platform.ANY);
	capabilities.setCapability("platformName", "Windows");
	capabilities.setCapability("platformVersion", "10");
	capabilities.setCapability("browserName", browserName);
	capabilities.setCapability("browserVersion", "latest");
	capabilities.setCapability("location", "US East");
	capabilities.setCapability("resolution", "1024x768");

	// The below capability is mandatory. Please do not replace it.
	capabilities.setCapability("securityToken", PerfectoLabUtils.fetchSecurityToken(securityToken));
	
	RemoteWebDriver Rdriver = new RemoteWebDriver(new URL("https://"+PerfectoLabUtils.fetchSecurityToken(cloudName)+".perfectomobile.com/nexperience/perfectomobile/wd/hub"), capabilities);
        
	reportiumClient = PerfectoLabUtils.setReportiumClient(Rdriver, reportiumClient,tag);
	WebDriver driver=(WebDriver)Rdriver;
    return driver;
}


public static void stepStart(String step){
	
	if(prop.getProperty("platform").equalsIgnoreCase("Perfecto")) {     
		reportiumClient.stepStart(step); //Starts a reportium step

	}
}

public static void stepEnd() {
	if(prop.getProperty("platform").equalsIgnoreCase("Perfecto")) {
		reportiumClient.stepEnd();
	}
}


public static void testStart(String testName, String testtag) throws Exception {
	LoadData data = new LoadData();
	prop=data.load_properties();
	if(prop.getProperty("platform").equalsIgnoreCase("Perfecto")) {
		reportiumClient.testStart(testName, new TestContext(testtag)); //Starts a reportium step
	}
}



public static void Assert(String massege ,boolean expected) {
	
	if (prop.getProperty("platform").equalsIgnoreCase("Perfecto")) {
	reportiumClient.reportiumAssert(massege, expected);
	
	}
}
public static void AssertEquels(String actual, String expected) {
	if (prop.getProperty("platform").equalsIgnoreCase("Perfecto")) {
		
		if (actual.equals(expected)) {
			reportiumClient.reportiumAssert("Actual and Expecated text is matched", true);
		}
		else {
			reportiumClient.reportiumAssert("Actual and Expecated text is not matched", false);
			
		}
			
	}
	
	
}
	public static void logResult( ITestResult result) {
		
		if (prop.getProperty("platform").equalsIgnoreCase("Perfecto")) {
			TestResult testResult=null;
			if(result.getStatus() == result.SUCCESS) {
				 testResult = TestResultFactory.createSuccess();
			}
			else if (result.getStatus() == result.FAILURE) {
				 testResult = TestResultFactory.createFailure(result.getThrowable());
			}
			
			reportiumClient.testStop(testResult);
		}
		
		
			
		}
		
		
	public void getReporturl() {
		if (prop.getProperty("platform").equalsIgnoreCase("Perfecto")) {
		String reportURL = reportiumClient.getReportUrl();
		System.out.println(reportURL);
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get(reportURL);
		driver.manage().window().maximize();
		}
	}
	
	
		}
	
	
	


