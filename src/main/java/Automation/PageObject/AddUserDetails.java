package Automation.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponent.AbstractComponent;

public class AddUserDetails extends AbstractComponent{
	WebDriver driver;

	public AddUserDetails(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryname;
	
	@FindBy(css=".action__submit")
	WebElement SubmitButton;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;
	
	By country = By.cssSelector("input[placeholder='Select Country']");
	By results = By.cssSelector(".ta-results");
	By Submit = By.cssSelector(".action__submit");
	public void SelectCountry(String country_detail) throws InterruptedException
	{
		WaitForElementToBeVisible(country);
		ActionsType(countryname,country_detail);
		WaitForElementToBeVisible(results);
		selectCountry.click();
		Thread.sleep(3000);
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("window.scrollBy(0,500)");
		WaitForElementToBeVisible(Submit);
		SubmitButton.click();
	}
}
