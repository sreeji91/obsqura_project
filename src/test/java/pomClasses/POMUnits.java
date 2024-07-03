package pomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtility;
import utility.PageUtility;

public class POMUnits {

	WebDriver driver;
	PageUtility objActions;
	WaitUtility objWait;

	public POMUnits(WebDriver driver) {
		this.driver = driver;
		objActions = new PageUtility(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Units']")
	public WebElement units;

	@FindBy(xpath = "//button[@class='btn btn-block btn-primary btn-modal']")
	public WebElement addBtn;

	@FindBy(xpath = "//input[@id='actual_name']")
	public WebElement actual_name;

	@FindBy(xpath = "//input[@id='short_name']")
	public WebElement short_name;

	@FindBy(xpath = "//select[@id='allow_decimal']")
	public WebElement decimal_dropdown;

	@FindBy(xpath = "//button[text()='Save']")
	public WebElement saveBtn;

	@FindBy(xpath = "//input[@type='search']")
	public WebElement search;

	@FindBy(xpath = "//table[@id='unit_table']/tbody/tr[2]/td[1]")
	public WebElement table_actualName;

	@FindBy(xpath = "//*[@id='toast-container']/div")
	public WebElement popUpMessage;

	@FindBy(xpath = "//table[@id='unit_table']/tbody/tr/td[4]/button[2]")
	public WebElement delete_btn;

	@FindBy(xpath = "//div[@class='swal-footer']/div[2]/button")
	public WebElement ok_btn;

	public void units_click() throws InterruptedException {
		objWait = new WaitUtility(driver);
		objWait.elementToBeClickable(By.xpath("//span[text()='Units']"), 5);
		units.click();
	}

	public void addUnits(String name, String shortName) throws InterruptedException {
		objActions.click(addBtn);
		objWait.presenceOfElementlocated(By.xpath("//input[@id='actual_name']"), 5);
		objActions.sendkeys(actual_name, name);
		objActions.sendkeys(short_name, shortName);
		objActions.DropdownselectByvalue(decimal_dropdown, "1");
		saveBtn.click();	
	}

	public String getMessage() {
		objWait.presenceOfElementlocated(By.xpath("//*[@id='toast-container']/div"), 5);

		return objActions.getText(popUpMessage);
	}

	public boolean searchUnits(String name) throws InterruptedException {
		objActions.click(search);
		objActions.sendkeys(search, name);
//		objWait.presenceOfElementlocated(By.xpath("//table[@id='unit_table']/tbody/tr[2]/td[1]"), 8);
		objWait.normalWait(2000);
		String table_name = objActions.getText(table_actualName);
		if (table_name.equalsIgnoreCase(name)) {
			return true;
		} else {
			return false;
		}

	}

	public void delete_unit(String name) throws InterruptedException {
		objWait.elementToBeClickable(By.xpath("//table[@id='unit_table']/tbody/tr/td[4]/button[2]"), 5);
		delete_btn.click();
		objWait.normalWait(5000);
		//objWait.elementToBeClickable(By.xpath("//div[@class='swal-footer']/div[2]/button"), 5);
		ok_btn.click();
	}

}
