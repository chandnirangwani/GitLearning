package Automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	
	@BeforeMethod
	public WebDriver IntializeDriver() throws IOException
	{
		Properties pro = new Properties();
		FileInputStream file = new FileInputStream(
				"D:\\Eclipse\\SeleniumFramework\\src\\main\\java\\Resources\\GlobalData.properties");
		pro.load(file);
		String browser = pro.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else {
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	@AfterMethod
	public void CloseDriver() {
		driver.close();
	}
	

}
