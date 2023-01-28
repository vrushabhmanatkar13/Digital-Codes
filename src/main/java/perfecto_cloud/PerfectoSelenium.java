package perfecto_cloud;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResult;
import com.perfecto.reportium.test.result.TestResultFactory;

import io.github.bonigarcia.wdm.WebDriverManager;


public class PerfectoSelenium {
	RemoteWebDriver driver;
	ReportiumClient reportiumClient;
	//Replace <<cloud name>> with your perfecto cloud name (e.g. testingcloud ) or pass it as maven properties: -DcloudName=<<cloud name>>
	String cloudName = "icc";
	
	//Replace <<security token>> with your perfecto security token or pass it as maven properties: -DsecurityToken=<<SECURITY TOKEN>>  More info: https://developers.perfectomobile.com/display/PD/Generate+security+tokens
	String securityToken = "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIxZjg0NWY3OS1hNTYyLTQ2MzItOGU3ZS0xMDU4NGFhNjEyODQifQ.eyJpYXQiOjE2Njg2MjE1NzIsImp0aSI6IjhkODAzZGI3LTVhZmItNGFmNi05OTMwLTFmOWI1ODcxY2UwOCIsImlzcyI6Imh0dHBzOi8vYXV0aDUucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL2ljYy1wZXJmZWN0b21vYmlsZS1jb20iLCJhdWQiOiJodHRwczovL2F1dGg1LnBlcmZlY3RvbW9iaWxlLmNvbS9hdXRoL3JlYWxtcy9pY2MtcGVyZmVjdG9tb2JpbGUtY29tIiwic3ViIjoiMzlhY2Y4YTgtNTg2ZC00OWU5LWJjY2YtNTJjYWUwMzUwYWU2IiwidHlwIjoiT2ZmbGluZSIsImF6cCI6Im9mZmxpbmUtdG9rZW4tZ2VuZXJhdG9yIiwibm9uY2UiOiIwMTRmZmNmZS0yNjE1LTQ4NDQtYmMwMC0zN2MxMDhhMjZkOTUiLCJzZXNzaW9uX3N0YXRlIjoiZjY2YzZlNDMtMjA3MC00YzQzLTgyNWYtNDJmMTJmNjliNmI5Iiwic2NvcGUiOiJvcGVuaWQgZW1haWwgcHJvZmlsZSBvZmZsaW5lX2FjY2VzcyJ9.DuO3PfMGrfaD-KNRYFaClgHgh7Pxi7_jJPDcIG802Kc";

	
	@Test(enabled = true)
	public void webTest() throws Exception {
		//Web: Make sure to Auto generate capabilities for device selection: https://developers.perfectomobile.com/display/PD/Select+a+device+for+manual+testing#Selectadeviceformanualtesting-genCapGeneratecapabilities
		DesiredCapabilities capabilities = new DesiredCapabilities("windows", "10", Platform.ANY);
		capabilities.setCapability("platformName", "Windows");
		capabilities.setCapability("platformVersion", "10");
		capabilities.setCapability("browserName", "firefox");
		capabilities.setCapability("browserVersion", "latest");
		capabilities.setCapability("location", "US East");
		capabilities.setCapability("resolution", "1024x768");

		// The below capability is mandatory. Please do not replace it.
		capabilities.setCapability("securityToken", PerfectoLabUtils.fetchSecurityToken(securityToken));
		
		driver = new RemoteWebDriver(new URL("https://"+PerfectoLabUtils.fetchCloudName(cloudName)+".perfectomobile.com/nexperience/perfectomobile/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

		reportiumClient = PerfectoLabUtils.setReportiumClient(driver, reportiumClient,"tag1"); //Creates reportiumClient
		reportiumClient.testStart("Perfecto desktop web test", new TestContext("tag3")); //Starts the reportium test
		reportiumClient.stepStart("browser navigate to perfecto"); //Starts a reportium step
		WebDriver wdriver=driver;
		wdriver.get("https://www.perfecto.io");
		reportiumClient.stepEnd();

		reportiumClient.stepStart("Verify title");
		String aTitle = driver.getTitle();
		PerfectoLabUtils.assertTitle(aTitle, reportiumClient); //compare the actual title with the expected title
		reportiumClient.stepEnd();
	}

	@AfterMethod(enabled = true)
	public void afterMethod(ITestResult result) throws IOException {
		//STOP TEST
		TestResult testResult = null;

		if(result.getStatus() == result.SUCCESS) {
			testResult = TestResultFactory.createSuccess();
		}
		else if (result.getStatus() == result.FAILURE) {
			testResult = TestResultFactory.createFailure(result.getThrowable());
		}
		reportiumClient.testStop(testResult);

		
		driver.close();
		driver.quit();
		// Retrieve the URL to the DigitalZoom Report 
		String reportURL = reportiumClient.getReportUrl();
		System.out.println(reportURL);
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get(reportURL);
		driver.manage().window().maximize();
		
	
	}
	

	
	
	
}

