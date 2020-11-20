package BigProject_OrangeHRMCilsy;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;

public class TC_Add_Location {

	public static String browser;
	static WebDriver driver;

	public static void main(String[] args) {
		TC_Add_Location test = new TC_Add_Location();
		test.setBrowser("Chrome");
		test.setBrowserConfig();
		test.navigateToUrl();
		test.loginWithValidCredential();
		test.addNewInvalidLocation();
		test.addNewValidLocation();
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public void setBrowserConfig() {
		String projectlocation = System.getProperty("user.dir");

		if (browser.contains("Chrome")) {
			System.setProperty("webdriver.chrome.driver", projectlocation + "\\lib\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		if (browser.contains("Firefox")) {
			System.setProperty("webdriver.firefox.driver", projectlocation + "\\lib\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
	}

	@Test
	public void navigateToUrl() {
		driver.get("https://qa.cilsy.id/");
		driver.manage().window().maximize();
		System.out.println("Browser opened");

		String expectedTitle = "OrangeHRM";
		String actualTitle = "";
		actualTitle = driver.getTitle();
		Assert.assertEquals(expectedTitle, actualTitle);
		System.out.println("Assert passed");
	}

	@Test
	public void loginWithValidCredential() {
		By inputUsername = By.id("txtUsername");
		By inputPassword = By.id("txtPassword");
		By btnLogin = By.id("btnLogin");
		driver.findElement(inputUsername).sendKeys("Admin");
		driver.findElement(inputPassword).sendKeys("s3Kol4HQA!*");
		driver.findElement(btnLogin).click();

		String actualResult = driver.findElement(By.id("welcome")).getText();
		String expecctedResult = "Welcome Anfo";
		Assert.assertEquals(expecctedResult, actualResult);
		System.out.println("Login success");
	}

	@Test
	public void addNewInvalidLocation() {
		driver.findElement(By.id("menu_admin_viewAdminModule")).click();
		driver.findElement(By.id("menu_admin_Organization")).click();
		driver.findElement(By.id("menu_admin_viewLocations")).click();
		driver.findElement(By.xpath("//h1[@id='searchLocationHeading']")).getText();
		driver.findElement(By.id("btnAdd")).click();

		driver.findElement(By.id("location_name")).sendKeys("cabang 1");
		By btnDropdown1 = By.id("location_country");
		Select dropdown1 = new Select(driver.findElement(By.id("location_country")));
		dropdown1.selectByValue("ID");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("location_city")).sendKeys("tangerang");
		driver.findElement(By.id("location_phone")).sendKeys("phonenumber");
		driver.findElement(By.id("btnSave")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[contains(text(),'Allows numbers and only + - / ( )')]")).getText();
		System.out.println("Unable to save");
	}

	@Test
	public void addNewValidLocation() {
		driver.findElement(By.id("location_name")).clear();
		driver.findElement(By.id("location_name")).sendKeys("cabang 1");
		By btnDropdown1 = By.id("location_country");
		Select dropdown1 = new Select(driver.findElement(By.id("location_country")));
		dropdown1.selectByValue("ID");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("location_city")).clear();
		driver.findElement(By.id("location_city")).sendKeys("tangerang");
		driver.findElement(By.id("location_phone")).clear();
		driver.findElement(By.id("location_phone")).sendKeys("phonenumber");
		driver.findElement(By.id("btnSave")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[contains(text(),'cabang 1')]")).getText();
		System.out.println("Successfully saved");
	}
}
