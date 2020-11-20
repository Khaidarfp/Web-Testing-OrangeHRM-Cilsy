package BigProject_OrangeHRMCilsy;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;

public class TC_Add_And_Edit_Employee {

	public static String browser;
	static WebDriver driver;

	public static void main(String[] args) {
		TC_Add_And_Edit_Employee test = new TC_Add_And_Edit_Employee();
		test.setBrowser("Chrome");
		test.setBrowserConfig();
		test.navigateToUrl();
		test.loginWithValidCredential();
		test.addNewInvalidEmployee();
		test.addNewValidEmployee();
		test.updateEmployeeInformation();
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public void setBrowserConfig() {
		String projectEmployee = System.getProperty("user.dir");

		if (browser.contains("Chrome")) {
			System.setProperty("webdriver.chrome.driver", projectEmployee + "\\lib\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		if (browser.contains("Firefox")) {
			System.setProperty("webdriver.firefox.driver", projectEmployee + "\\lib\\driver\\geckodriver.exe");
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
	public void addNewInvalidEmployee() {
		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
		driver.findElement(By.xpath("//*[@id=\"employee-information\"]/a")).getText();
		driver.findElement(By.id("btnAdd")).click();

		driver.findElement(By.id("firstName")).sendKeys("febri");
		driver.findElement(By.id("lastName")).sendKeys("test");
		
		driver.findElement(By.id("chkLogin")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("user_name")).sendKeys("feb");
		driver.findElement(By.id("user_password")).sendKeys("test1234");
		driver.findElement(By.id("btnSave")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[contains(text(),'Should have at least 5 characters')]")).getText();
		driver.findElement(By.xpath("//span[@id='user_password_help_text']")).getText();
		System.out.println("Unable to save");
	}

	@Test
	public void addNewValidEmployee() {
		driver.findElement(By.id("user_name")).clear();
		driver.findElement(By.id("user_name")).sendKeys("febritest");
		driver.findElement(By.id("user_password")).clear();
		driver.findElement(By.id("user_password")).sendKeys("TEst_123!*");
		driver.findElement(By.id("re_password")).sendKeys("TEst_123!*");
		driver.findElement(By.id("btnSave")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath(" //h1[contains(text(),'Personal Details')]")).getText();
		System.out.println("Successfully saved");
	}
	
	@Test
	public void updateEmployeeInformation() {
		driver.findElement(By.id("btnSave")).click();
		driver.findElement(By.id("personal_optGender_1")).click();
		By btnDropdown1 = By.id("personal_cmbMarital");
		Select dropdown1 = new Select(driver.findElement(By.id("personal_cmbMarital")));
		dropdown1.selectByValue("Single");
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("btnSave")).click();
		System.out.println("Successfully edited");
	}
}
