package Automation.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;

public class PageObjectProductCatalog extends AbstractComponent {
	
	WebDriver driver;
	
	public  PageObjectProductCatalog(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".card")
	List<WebElement> products;
	
	By Dashboard = By.cssSelector("button[routerlink='/dashboard/']");
	By product = By.cssSelector("b");
	By addtocart= By.cssSelector(".btn.w-10.rounded");
	By spinner = By.cssSelector("#toast-container");
	
	public List<WebElement> getProducts() {
		WaitForElementToBeVisible(Dashboard);
		return products;
	}

	public WebElement getProduct(String productname) {

		WebElement prod = getProducts().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addToCart(String productname) {
		getProduct(productname).findElement(addtocart).click();
		WaitForElementToBeVisible(spinner);
		WaitForElementToBeInVisible(spinner);
		
	}
}
