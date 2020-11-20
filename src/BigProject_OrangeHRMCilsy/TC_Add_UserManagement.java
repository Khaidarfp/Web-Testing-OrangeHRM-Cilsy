package BigProject_OrangeHRMCilsy;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;

public class TC_Add_UserManagement {

	public static String browser;
	static WebDriver driver;

	public static void main(String[] args) {
		TC_Add_UserManagement test = new TC_Add_UserManagement();
		test.setBrowser("Chrome");
		test.setBrowserConfig();
		test.navigateToUrl();
		test.loginWithValidCredential();
		test.addNewInvalidUser();
		test.addNewValidUser();
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
	public void addNewInvalidUser() {
		driver.findElement(By.id("menu_admin_viewAdminModule")).click();
		driver.findElement(By.id("menu_admin_UserManagement")).click();
		driver.findElement(By.xpath("//*[@id=\"systemUser-information\"]/div[1]/h1")).getText();
		driver.findElement(By.id("btnAdd")).click();

		By btnDropdown1 = By.id("systemUser_userType");
		Select dropdown1 = new Select(driver.findElement(By.id("systemUser_userType")));
		dropdown1.selectByValue("1");
		driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys("febri test");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("systemUser_password")).sendKeys("TEst_123!*");
		driver.findElement(By.id("systemUser_confirmPassword")).sendKeys("TEst_123!*");
		driver.findElement(By.id("btnSave")).click();

		driver.findElement(By.xpath("//*[@id=\"frmSystemUser\"]/fieldset/ol/li[3]/span")).getText();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		System.out.println("Unable to save");
	}

	@Test
	public void addNewValidUser() {
		By btnDropdown1 = By.id("systemUser_userType");
		Select dropdown1 = new Select(driver.findElement(By.id("systemUser_userType")));
		dropdown1.selectByValue("2");
		driver.findElement(By.id("systemUser_employeeName_empName")).clear();
		driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys("febri test");
		driver.findElement(By.id("systemUser_userName")).sendKeys("febri0812");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("systemUser_password")).sendKeys("TEst_123!*");
		driver.findElement(By.id("systemUser_confirmPassword")).sendKeys("TEst_123!*");
		driver.findElement(By.id("btnSave")).click();

		driver.findElement(By.xpath("//a[contains(text(),'febri0812')]")).getText();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		System.out.println("Successfully saved");
	}
}
