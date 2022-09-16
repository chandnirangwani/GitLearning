package Automation;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Automation.PageObject.PageObjectLogin;

public class ErrorValidation extends BaseTest {

		public WebDriver driver1;
		
		@Test(groups= {"Functional"})
		public void IncorrectLogin() throws IOException
		{
			driver1=driver;
			
			PageObjectLogin po = new PageObjectLogin(driver1);
			po.getURl();
			po.loginApplication("chandnirangwani978@gmail.com","Chandni@100397");
			po.getErrorMessage();
			Assert.assertEquals(po.getErrorMessage(), "Incorrect email or password.");
		}
}
