package Automation;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.*;

import Automation.PageObject.AddUserDetails;
import Automation.PageObject.PageObjectCart;
import Automation.PageObject.PageObjectLogin;
import Automation.PageObject.PageObjectProductCatalog;
import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
public class Login extends BaseTest{

	@Test(dataProvider="getData",groups={"Functional"})
	public void SubmitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		String productname = "ADIDAS ORIGINAL";
		String country = "India";

		PageObjectLogin po = new PageObjectLogin(driver);
		po.getURl();
		po.loginApplication("chandnirangwani97@gmail.com","Chandni@100397");
		po.loginApplication(input.get("Email"),input.get("Password"));
		
		PageObjectProductCatalog catalog = new PageObjectProductCatalog(driver);
		catalog.getProducts();
		catalog.addToCart(productname);

		PageObjectCart  cart = new PageObjectCart(driver);
		cart.clickOnCartButton();
		cart.verifyCartItem(productname);
		cart.clickonCheckout();
		
		AddUserDetails userdetails = new AddUserDetails(driver);
		userdetails.SelectCountry(country);
		
		String message = driver.findElement(By.cssSelector(".hero-primary")).getText();
		org.testng.Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");
	}
	@DataProvider
	public Object[][] getData()
	{
		HashMap<Object,Object> hash= new HashMap<Object,Object>();
		hash.put("Email","chandnirangwani97@gmail.com");
		hash.put("Password","Chandni@100397");
		return new Object[][] {{hash}};
	}

}
