package BigProject_OrangeHRMCilsy;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TC_Edit_General_Information_Form {

	public static String browser;
	static WebDriver driver;

	public static void main(String[] args) {
		TC_Edit_General_Information_Form test = new TC_Edit_General_Information_Form();
		test.setBrowser("Chrome");
		test.setBrowserConfig();
		test.navigateToUrl();
		test.loginWithValidCredential();
		test.editInfoWithInvalidCredential();
		test.editInfoWIthValidCredential();
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

		String actualResult1 = driver.findElement(By.id("welcome")).getText();
		String expecctedResult1 = "Welcome Anfo";
		Assert.assertEquals(expecctedResult1, actualResult1);
		System.out.println("Login success");
	}

	@Test
	public void editInfoWithInvalidCredential() {
		driver.findElement(By.id("menu_admin_viewAdminModule")).click();
		driver.findElement(By.id("menu_admin_Organization")).click();
		driver.findElement(By.id("menu_admin_viewOrganizationGeneralInformation")).click();
		driver.findElement(By.xpath("//h1[contains(text(),'General Information')]")).getText();

		driver.findElement(By.id("btnSaveGenInfo")).click();
		driver.findElement(By.id("organization_email")).sendKeys("cilsy.com");
		driver.findElement(By.id("organization_fax")).sendKeys("faxnumber");
		driver.findElement(By.id("btnSaveGenInfo")).click();

		driver.findElement(By.xpath("//span[contains(text(),'Allows numbers and only + - / ( )')]")).getText();
		driver.findElement(By.xpath("//span[contains(text(),'Expected format: admin@example.com')]")).getText();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		System.out.println("Unable to save");
	}

	@Test
	public void editInfoWIthValidCredential() {
		driver.findElement(By.id("organization_email")).clear();
		driver.findElement(By.id("organization_email")).sendKeys("cilsy@email.com");
		driver.findElement(By.id("organization_fax")).clear();
		driver.findElement(By.id("organization_fax")).sendKeys("08123456");
		driver.findElement(By.id("btnSaveGenInfo")).click();

		System.out.println("Successfully saved");
	}
}
