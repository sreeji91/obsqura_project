package pomClasses;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.DriverUtility;
import utility.WaitUtility;
import utility.PageUtility;

public class POMLogin {
	WebDriver driver;
	PageUtility objActions;
	WaitUtility objWait;

	public POMLogin(WebDriver driver) {
		this.driver = driver;
		objActions = new PageUtility(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='username']")
	public WebElement webuserName;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement webpassword;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement webLoginBtn;

	@FindBy(xpath = "//button[text()='End tour']")
	public WebElement endTourBtn;

	@FindBy(xpath = "//a[@id='tour_step5_menu']")
	public WebElement product;

	@FindBy(xpath = "/html/body/div[2]/header/nav/div/ul/li[2]/a")
	public WebElement profile;

	@FindBy(xpath = "//a[text()='Sign Out']")
	public WebElement signOut;

	public void loginVerification(String username, String password) throws IOException {
		objWait = new WaitUtility(driver);
		objActions.sendkeys(webuserName, username);
		objActions.sendkeys(webpassword, password);
		objActions.click(webLoginBtn);
		
		objWait.presenceOfElementlocated(By.xpath("//button[text()='End tour']"), 8);
		endTourBtn.click();
		
	}
	public boolean isElementDisplayed() {
		return product.isDisplayed();
	}
	

	public void product_click() throws InterruptedException {
		objWait = new WaitUtility(driver);
		objWait.presenceOfElementlocated(By.xpath("//a[@id='tour_step5_menu']"),5);
		objActions.click(product);

	}

	public void signout() throws InterruptedException {
		
		objActions.click(profile);
		objWait = new WaitUtility(driver);
		objWait.normalWait(4000);
		objActions.JavascriptClick(signOut);
		objWait.normalWait(8000);
	}

}
