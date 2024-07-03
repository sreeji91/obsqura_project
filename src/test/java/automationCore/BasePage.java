package automationCore;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utility.DriverUtility;
import utility.PropertyReadUtility;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class BasePage {
	public WebDriver driver;
	static String url = PropertyReadUtility.readConfigFile("login_url");
	static String browser = PropertyReadUtility.readConfigFile("browser");

	ExtentSparkReporter extentSparkReporter;
	ExtentReports extentReports;
	ExtentTest Test;
	protected static ThreadLocal<ExtentTest> extentTestlocal = new ThreadLocal<ExtentTest>();

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		extentSparkReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/test-output/extentReport_Latest.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
		// configuration items to change the look and feel
		// add content, manage tests etc
		extentSparkReporter.config().setDocumentTitle("PRJ_OBSACCOUNT Automation");
		extentSparkReporter.config().setReportName("Test Report");
		extentSparkReporter.config().setTheme(Theme.STANDARD);
		extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

	}



	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
		DriverUtility objDriverManager = new DriverUtility();
		objDriverManager.launchBrowser(url, browser);
		driver = objDriverManager.driver;

		
	}

	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) throws Exception {
		Test = extentReports.createTest("Test Case:- " + result.getName());
		extentTestlocal.set(Test);
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTestlocal.get().addScreenCaptureFromPath(savedScreenshot());
			extentTestlocal.get().log(Status.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTestlocal.get().log(Status.PASS, result.getTestName());
		} else {
			extentTestlocal.get().log(Status.SKIP, result.getTestName());
		}
	}

	

	public String savedScreenshot() throws Exception {

		Calendar cal = Calendar.getInstance();
		Date time = (Date) cal.getTime();
		String timestamp = time.toString().replace(":", "").replace(" ", "");

		System.out.println(time);
		System.out.println(timestamp);

		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(
		System.getProperty("user.dir") + "\\src\\main\\resources\\Screenshots\\test_" + timestamp + ".png");

//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
		return DestFile.getAbsolutePath();
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		extentReports.flush();
		driver.close();
	}

}


