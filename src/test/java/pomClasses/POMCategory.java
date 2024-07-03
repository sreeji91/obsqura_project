package pomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WaitUtility;
import utility.PageUtility;

public class POMCategory {
	public static String expected_message = "";

	WebDriver driver;
	PageUtility objActions;
	WaitUtility objWait;

	public POMCategory(WebDriver driver) {
		this.driver = driver;
		objActions = new PageUtility(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Categories '] ")
	public WebElement categories;

	@FindBy(xpath = "//button[@class='btn btn-block btn-primary btn-modal']")
	public WebElement addBtn;

	@FindBy(xpath = "//input[@id='name']")
	public WebElement category_name;

	@FindBy(xpath = "//input[@id='short_code']")
	public WebElement category_code;

	@FindBy(xpath = "//*[@id=\"category_add_form\"]/div[3]/button[1]")
	public WebElement button_save;

	@FindBy(xpath = "//input[@class='form-control input-sm']")
	public WebElement searchClick;

	@FindBy(xpath = "//table[@id='category_table']/tbody/tr[1]/td[1]")
	public WebElement table_value;

	@FindBy(xpath = "//*[@id='toast-container']/div")
	public WebElement popUpMessage;

	@FindBy(xpath = "//table[@id='category_table']/tbody/tr/td[3]/button[2]")
	public WebElement delete_btn;

	@FindBy(xpath = "//div[@class='swal-footer']/div[2]/button")
	public WebElement ok_btn;

	public void category_click() throws InterruptedException {
		objWait = new WaitUtility(driver);
		objWait.elementToBeClickable(By.xpath("//span[text()='Categories '] "), 5);
		categories.click();
		
	}

	public void add_Category(String categoryName, String categoryCode) throws InterruptedException {
		objActions.click(addBtn);
		objWait = new WaitUtility(driver);
		objWait.presenceOfElementlocated(By.xpath("//input[@id='name']"), 5);
		objActions.sendkeys(category_name, categoryName);
		objActions.sendkeys(category_code, categoryCode);
		button_save.click();
		objWait.presenceOfElementlocated(By.xpath("//*[@id='toast-container']/div"), 5);
		expected_message = objActions.getText(popUpMessage);
		

	}

	public boolean Search_category(String category) throws InterruptedException {
		objActions.click(searchClick);
		objActions.sendkeys(searchClick, category);
		objWait = new WaitUtility(driver);
//		objWait.visibilityOfElementLocated(By.xpath("//table[@id='category_table']/tbody/tr[1]/td[1]"), 5);
		objWait.normalWait(2000);
		String table_name = objActions.getText(table_value);
		objWait.normalWait(2000);

		if (table_name.equalsIgnoreCase(category)) {
			return true;
		} else {
			return false;
		}

	}

	public void delete_category(String category) throws InterruptedException {
		objWait.presenceOfElementlocated(By.xpath("//table[@id='category_table']/tbody/tr/td[3]/button[2]"), 5);
		objActions.click(delete_btn);
		objWait.normalWait(5000);
		objActions.click(ok_btn);
		objWait.presenceOfElementlocated(By.xpath("//*[@id='toast-container']/div"), 5);
		expected_message = objActions.getText(popUpMessage);
		System.out.println("message=" + expected_message);
	}
}
