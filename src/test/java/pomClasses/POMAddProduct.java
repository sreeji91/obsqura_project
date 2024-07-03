package pomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.PropertyReadUtility;
import utility.WaitUtility;
import utility.PageUtility;

public class POMAddProduct {
	WebDriver driver;
	PageUtility objActions;
	WaitUtility objWait;

	public POMAddProduct(WebDriver driver) {
		this.driver = driver;
		objActions = new PageUtility(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//li[@id='tour_step5']/ul/li[2]/a")
	public WebElement addProduct;

	@FindBy(xpath = "//input[@id='name']")
	public WebElement product_name;

	@FindBy(xpath = "//span[@id='select2-brand_id-container'] ")
	public WebElement brand_id;

	@FindBy(xpath = "/html/body/span/span/span[1]/input")
	public WebElement brand_search;

	@FindBy(xpath = "//ul[@id='select2-brand_id-results']/li[1]")
	public WebElement brand_name;

	@FindBy(xpath = "//span[@id='select2-category_id-container']")
	public WebElement category_id;

	@FindBy(xpath = "/html/body/span/span/span[1]/input")
	public WebElement category_search;

	@FindBy(xpath = "//ul[@id='select2-category_id-results']/li[1]")
	public WebElement category_name;

	@FindBy(xpath = "//input[@id='alert_quantity']")
	public WebElement alert_quantity;

	@FindBy(xpath = "//input[@id='expiry_period']")
	public WebElement expiry_period;

	@FindBy(xpath = "//input[@id='single_dpp']")
	public WebElement exc_tax;

	@FindBy(xpath = "//div[@class='btn-group']/button[4]")
	public WebElement save_btn;

	@FindBy(xpath = "//*[@id='toast-container']/div")
	public WebElement popUpMessage;

	public void add_product() throws Exception {
		objWait = new WaitUtility(driver);
		objWait.elementToBeClickable(By.xpath("//li[@id='tour_step5']/ul/li[2]/a"), 5);
		objActions.click(addProduct);
		objActions.screenshot();
		objWait.presenceOfElementlocated(By.xpath("//input[@id='name']"), 5);
		objActions.sendkeys(product_name,PropertyReadUtility.readConfigFile("product_name") );
//		objActions.click(brand_id);
//		objWait.normalWait(2000);
//		objActions.sendkeys(brand_search, PropertyReadUtility.readConfigFile("brand_test_data"));
//		objActions.click(brand_name);
//		objWait.normalWait(2000);
//		objActions.click(category_id);
//		objWait.normalWait(2000);
//		objActions.sendkeys(category_search, PropertyReadUtility.readConfigFile("category_test_data"));
//		objActions.click(category_name);
		objActions.sendkeys(alert_quantity, PropertyReadUtility.readConfigFile("alert_quantity"));
		objWait.presenceOfElementlocated(By.xpath("//input[@id='expiry_period']"), 5);
		objActions.sendkeys(expiry_period, PropertyReadUtility.readConfigFile("expiry_period"));
		objWait.presenceOfElementlocated(By.xpath("//input[@id='single_dpp']"), 5);
		objActions.sendkeys(exc_tax, PropertyReadUtility.readConfigFile("exc_tax"));
		objWait.elementToBeClickable(By.xpath("//div[@class='btn-group']/button[4]"), 5);
		objActions.click(save_btn);
	}

	public String get_message() {
		objWait.presenceOfElementlocated(By.xpath("//*[@id='toast-container']/div"), 5);
		return objActions.getText(popUpMessage);
	}

}
