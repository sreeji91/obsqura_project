package utility;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendTestManager {
	public static ExtentReports extent;

	@BeforeSuite
	public ExtentReports extendreportgenerate() {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extentReport.html");
		// create ExtentReports and attach reporter(s)
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		// test=extent.createTest(null);
		return extent;
	}

	@AfterSuite
	public void closereport() {
		extent.flush();
	}

}
