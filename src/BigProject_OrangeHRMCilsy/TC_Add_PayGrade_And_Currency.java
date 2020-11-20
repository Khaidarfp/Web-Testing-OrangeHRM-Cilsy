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

public class TC_Add_PayGrade_And_Currency {

	public static String browser;
	static WebDriver driver;

	public static void main(String[] args) {
		TC_Add_PayGrade_And_Currency test = new TC_Add_PayGrade_And_Currency();
		test.setBrowser("Chrome");
		test.setBrowserConfig();
		test.navigateToUrl();
		test.loginWithValidCredential();
		test.addPayGradeWithoutCredential();
		test.addPayGradeWithCredential();
		test.addCurrencyWithNegatifNumber();
		test.addCUrrencyWithHighMinSalary();
		test.aadCurrencyWithValidCredential();
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
	public void addPayGradeWithoutCredential() {
		driver.findElement(By.id("menu_admin_viewAdminModule")).click();
		driver.findElement(By.id("menu_admin_Job")).click();
		driver.findElement(By.id("menu_admin_viewPayGrades")).click();
		driver.findElement(By.xpath("//h1[contains(text(),'Pay Grades')]")).getText();

		driver.findElement(By.id("btnAdd")).click();
		driver.findElement(By.id("btnSave")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		System.out.println("Unable to save");
	}

	@Test
	public void addPayGradeWithCredential() {
		driver.findElement(By.id("payGrade_name")).sendKeys("karyawan");
		driver.findElement(By.id("btnSave")).click();

		System.out.println("Successfully saved");
	}

	@Test
	public void addCurrencyWithNegatifNumber() {
		driver.findElement(By.id("btnAddCurrency")).click();
		driver.findElement(By.id("payGradeCurrency_currencyName")).sendKeys("IDR - Indonesian Rupiah");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.findElement(By.id("payGradeCurrency_minSalary")).sendKeys("-10000");
		driver.findElement(By.id("payGradeCurrency_maxSalary")).sendKeys("30000");
		driver.findElement(By.id("btnSaveCurrency")).click();

		System.out.println("Unable to save");
	}

	@Test
	public void addCUrrencyWithHighMinSalary() {
		driver.findElement(By.id("payGradeCurrency_minSalary")).clear();
		driver.findElement(By.id("payGradeCurrency_minSalary")).sendKeys("30000");
		driver.findElement(By.id("payGradeCurrency_maxSalary")).clear();
		driver.findElement(By.id("payGradeCurrency_maxSalary")).sendKeys("10000");

		driver.findElement(By.id("btnSaveCurrency")).click();
		System.out.println("Unable to save");
	}

	@Test
	public void aadCurrencyWithValidCredential() {
		driver.findElement(By.id("payGradeCurrency_minSalary")).clear();
		driver.findElement(By.id("payGradeCurrency_minSalary")).sendKeys("10000");
		driver.findElement(By.id("payGradeCurrency_maxSalary")).clear();
		driver.findElement(By.id("payGradeCurrency_maxSalary")).sendKeys("30000");

		driver.findElement(By.id("btnSaveCurrency")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[contains(text(),'QA')]")).getText();
		System.out.println("Successfully saved");
	}
}
