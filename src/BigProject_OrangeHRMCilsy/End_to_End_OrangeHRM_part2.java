package BigProject_OrangeHRMCilsy;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;

public class End_to_End_OrangeHRM_part2 {

	public static String browser;
	static WebDriver driver;

	public static void main(String[] args) {
		End_to_End_OrangeHRM_part2 test = new End_to_End_OrangeHRM_part2();
		test.setBrowser("Chrome");
		test.setBrowserConfig();
		test.navigateToUrl();
		test.loginWithInvalidCredential();
		test.loginWithValidCredential();
		test.addNewInvalidUser();
		test.addNewValidUser();
		test.searchingUser();
		test.addJobTitleWithoutCredential();
		test.addJobTitleWithCredential();
		test.deleteJobTitle();
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
	public void loginWithInvalidCredential() {
		By inputUsername = By.id("txtUsername");
		By inputPassword = By.id("txtPassword");
		By btnLogin = By.id("btnLogin");
		driver.findElement(inputUsername).sendKeys("Admin");
		driver.findElement(inputPassword).sendKeys("sekolahqa");
		driver.findElement(btnLogin).click();

		driver.findElement(By.id("spanMessage")).getText();
		System.out.println("Login failed");
	}

	@Test
	public void loginWithValidCredential() {
		By inputUsername = By.id("txtUsername");
		By inputPassword = By.id("txtPassword");
		By btnLogin = By.id("btnLogin");
		driver.findElement(inputUsername).sendKeys("Admin");
		driver.findElement(inputPassword).clear();
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
	
	@Test
	public void searchingUser() {
		driver.findElement(By.id("menu_admin_viewAdminModule")).click();
		driver.findElement(By.id("menu_admin_UserManagement")).click();
		driver.findElement(By.xpath("//*[@id=\"systemUser-information\"]/div[1]/h1")).getText();

		driver.findElement(By.id("searchSystemUser_userName")).sendKeys("febri0812");
		By btnDropdown1 = By.id("searchSystemUser_userType");
		Select dropdown1 = new Select(driver.findElement(By.id("searchSystemUser_userType")));
		dropdown1.selectByValue("2");
		driver.findElement(By.id("searchSystemUser_employeeName_empName")).sendKeys("febri test");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("searchBtn")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[contains(text(),'febri0812')]")).getText();
		System.out.println("Searching success");
	}
	
	@Test
	public void addJobTitleWithoutCredential() {
		driver.findElement(By.id("menu_admin_viewAdminModule")).click();
		driver.findElement(By.id("menu_admin_Job")).click();
		driver.findElement(By.id("menu_admin_viewJobTitleList")).click();
		driver.findElement(By.xpath("//h1[contains(text(),'Job Titles')]")).getText();
		
		driver.findElement(By.id("btnAdd")).click();
		driver.findElement(By.id("btnSave")).click();
		
		driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		System.out.println("Unable to save");
	}
	
	@Test
	public void addJobTitleWithCredential() {
		driver.findElement(By.id("jobTitle_jobTitle")).sendKeys("QA");
		driver.findElement(By.id("jobTitle_jobDescription")).sendKeys("automation");
		driver.findElement(By.id("btnSave")).click();
		
		driver.findElement(By.xpath("//a[contains(text(),'QA')]")).getText();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		System.out.println("Successfully saved");
	}
	
	@Test
	public void deleteJobTitle() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebElement chkbox = driver.findElement(By.cssSelector("#ohrmList_chkSelectRecord_66"));
		chkbox.click();
		if (chkbox.isSelected()) {
			System.out.println("Checkbox is Toggled On");

		} else {
			System.out.println("Checkbox is Toggled Off");
		}
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("dialogDeleteBtn")).click();
		System.out.println("Delete success");
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
