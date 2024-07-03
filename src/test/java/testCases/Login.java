package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import automationCore.BasePage;
import pomClasses.POMLogin;
import utility.ExcelUtility;
import utility.PropertyReadUtility;
import java.io.IOException;

public class Login extends BasePage {
	POMLogin objPomLogin;
	

	

	

	@Test(priority = 1, enabled = true, groups= {"sequential"})
	public void ValidatelogIn() throws IOException {
		
		objPomLogin=new POMLogin(driver);
		String username = ExcelUtility.readStringData(1, 0);
		String password = ExcelUtility.integerData(1, 1);
		objPomLogin.loginVerification(username, password);
		SoftAssert objassert = new SoftAssert();
		objassert.assertEquals(objPomLogin.isElementDisplayed(), true);	
		objassert.assertAll();
	}
//	@Test(priority = 2, enabled = true)
//	public void InvalidlogIn() throws IOException {
//		
//		objPomLogin=new POMLogin(driver);
//		String username = ExcelUtility.readStringData(1, 0);
//		String password = ExcelUtility.integerData(1, 1);
//		objPomLogin.loginVerification(username, password);
//		SoftAssert objassert = new SoftAssert();
//		objassert.assertEquals(objPomLogin.isElementDisplayed(), false);	
//		objassert.assertAll();
//	}


}
