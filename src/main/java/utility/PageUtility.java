package utility;

import java.io.File;
import java.io.IOException;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PageUtility {
	public WebDriver driver;
	private String iframe;

	public PageUtility(WebDriver driver) {
		this.driver = driver;

	}

	// Method implementation for switching the control to alert window
	public void Alertaccept() {
		org.openqa.selenium.Alert objalert = driver.switchTo().alert();
		objalert.accept();

	}

	// Method Implementation to dismiss an alert
	public void Alertdismiss() {
		org.openqa.selenium.Alert objalert = driver.switchTo().alert();
		objalert.dismiss();
	}

	// Method implementation for switching the control to frame window
	public void iframes(WebElement element) {
		driver.switchTo().frame(element);
	}

	// Method implementation for click using javascriptExecutor.
	public void JavascriptClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	// Method Implementation to select a value from the dropdown by value

	public void DropdownselectByvalue(WebElement dropdown, String value) {
		Select objSelect = new Select(dropdown);
		objSelect.selectByValue(value);

	}

	// Method Implementation to select a value from the dropdown by Index
	public void DropdownselectByIndex(WebElement dropdown, int value) {
		Select objSelect = new Select(dropdown);

		objSelect.selectByIndex(value);
	}

	// Method Implementation to select a value from the dropdown by visibleText
	public void DropdownselectByVisibleText(WebElement dropdown, String value) {
		Select objSelect = new Select(dropdown);

		objSelect.selectByVisibleText(value);
	}

	// Method implementation for scrolling the window until an element is found
	public void scrolluntilElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(;)", element);
	}
	// Method implementation for scrolling the window until pixel value

	public void scrolluntilPixelValue(int pixel) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,pixel)");
	}

	// Method Implementation to take screenshots
	/*public void screenshot() throws IOException {
		// Convert web driver object to TakeScreenshot

		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		Calendar cal = Calendar.getInstance();
		Date time = (Date) cal.getTime();
		String timestamp = time.toString().replace(":", "").replace(" ", "");

		// Call getScreenshotAs method to create image file

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// Move image file to new destination

		File DestFile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\Screenshots\\test_"+timestamp + ".png");

		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
	}*/
public void screenshot() throws Exception{
		
		Calendar cal=Calendar.getInstance();
		Date time=(Date)cal.getTime();
		String timestamp=time.toString().replace(":", "").replace(" ", "");
		
		System.out.println(time);
		System.out.println(timestamp);

		
		 //Convert web driver object to TakeScreenshot
TakesScreenshot scrShot =((TakesScreenshot)driver);

        //Call getScreenshotAs method to create image file
File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
File DestFile = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\Screenshots\\test_"+timestamp + ".png");

// Copy file at destination
FileUtils.copyFile(SrcFile, DestFile);
}

	// Common method implementation for webElement

	public void click(WebElement value) {
		value.click();
	}

	public void sendkeys(WebElement value, String value1) {
		value.clear();
		value.sendKeys(value1);
	}

	public String getText(WebElement value) {
		String Value = value.getText();
		return Value;
	}

}
