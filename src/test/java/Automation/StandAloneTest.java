package Automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.*;

import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {


	public static void main(String[] args) throws InterruptedException {
		String productname = "ADIDAS ORIGINAL";
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("chandnirangwani97@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Chandni@100397");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[routerlink='/dashboard/']")));
		List<WebElement> products = driver.findElements(By.cssSelector(".card"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".btn.w-10.rounded")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection"));
		Boolean match = cartproducts.stream()
				.anyMatch(cartproduct -> cartproduct.findElement(By.cssSelector("h3")).getText().equals(productname));
		org.testng.Assert.assertTrue(match);

		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select Country']")));
		WebElement country = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
		Actions a = new Actions(driver);
		// a.moveToElement(country).sendKeys("India").build().perform();
		a.sendKeys(country, "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		Thread.sleep(3000);
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("window.scrollBy(0,500)");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
		driver.findElement(By.cssSelector(".action__submit")).click();

		String message = driver.findElement(By.cssSelector(".hero-primary")).getText();
		org.testng.Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");
		driver.close();
	}

}
