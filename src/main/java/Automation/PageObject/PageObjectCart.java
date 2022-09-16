package Automation.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class PageObjectCart {
	
	WebDriver driver;
	public  PageObjectCart(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement CartButton;
	
	@FindBy(css=".cartSection")
	List<WebElement> CartItems;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement CheckoutButton;
	
	public void clickOnCartButton()
	{
		CartButton.click();
	}
	
	public List<WebElement> getCartItems()
	{
		List<WebElement> cartproducts = CartItems;
		return cartproducts;
	}
	public void verifyCartItem(String productname) {
	Boolean match = getCartItems().stream()
			.anyMatch(cartproduct -> cartproduct.findElement(By.cssSelector("h3")).getText().equals(productname));
		Assert.assertTrue(match);
	}
	
	public void clickonCheckout()
	{
		CheckoutButton.click();
	}
}
