package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import automationCore.BasePage;
import pomClasses.POMBrands;
import pomClasses.POMLogin;
import utility.ExcelUtility;
import utility.PropertyReadUtility;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.testng.Assert;

public class BrandsTestCase extends BasePage{
	POMLogin objPomLogin;
	static String url = PropertyReadUtility.readConfigFile("login_url");
	static String browser = PropertyReadUtility.readConfigFile("browser");
	POMBrands objPomBrands;

	@Test(priority = 1, enabled = true)
	public void logIn() throws IOException {
		objPomLogin = new POMLogin(driver);
		String username = ExcelUtility.readStringData(1, 0);
		String password = ExcelUtility.integerData(1, 1);
		objPomLogin.loginVerification(username, password);

		SoftAssert objassert = new SoftAssert();
		objassert.assertEquals(objPomLogin.isElementDisplayed(), true);	
		objassert.assertAll();
	}

	@Test(priority = 2, enabled = true, dataProvider = "testData")
	public void addBrands(String brandName, String brandDescription) throws IOException, InterruptedException {
		objPomBrands = new POMBrands(driver);
		objPomLogin.product_click();
		objPomBrands.brands_click();
		objPomBrands.add_brands(brandName, brandDescription);
		String exp_message = "Brand added successfully";
		String current_message = objPomBrands.expected_message;
		Assert.assertTrue(exp_message.contains(current_message));

	}

	@Test(priority = 3, enabled = true)
	public void SearchCategory() throws InterruptedException {
		boolean status = objPomBrands.search_brand(PropertyReadUtility.readConfigFile("brand_test_data"));
		SoftAssert objassert = new SoftAssert();
		objassert.assertEquals(status, true);
		objassert.assertAll();
	}

	@DataProvider(name = "testData")
	public Object[][] TestDataFeed() {
		Object[][] brandData = new Object[1][2];
		brandData[0][0] = "Sree_brand";
		brandData[0][1] = "Sree_brand";
		return brandData;
	}

}
