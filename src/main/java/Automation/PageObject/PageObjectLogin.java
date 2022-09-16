package Automation.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjectLogin {
	
	WebDriver driver;
	
	public  PageObjectLogin(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userpassword;
	
	@FindBy(id="login")
	WebElement login;

	@FindBy(css="[class*='flyInOut'")
	WebElement errorMessage;
	
	public void loginApplication(String Email, String pass)
	{
		userEmail.sendKeys(Email);
		userpassword.sendKeys(pass);
		login.click();
	}
	
	public void getURl()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	public String getErrorMessage()
	{
		return errorMessage.getText();
	}
}
