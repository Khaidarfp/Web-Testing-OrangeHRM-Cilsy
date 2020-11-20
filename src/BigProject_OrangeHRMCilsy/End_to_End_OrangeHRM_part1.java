package BigProject_OrangeHRMCilsy;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;

public class End_to_End_OrangeHRM_part1 {

	public static String browser;
	static WebDriver driver;

	public static void main(String[] args) {
		End_to_End_OrangeHRM_part1 test = new End_to_End_OrangeHRM_part1();
		test.setBrowser("Chrome");
		test.setBrowserConfig();
		test.navigateToUrl();
		test.loginWithInvalidCredential();
		test.loginWithValidCredential();
		test.addNewValidEmployee();
		test.viewValidAttendanceRecord();
		test.addValidAttendanceRecord1();
		test.addValidAttendanceRecord2();
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
	public void addNewValidEmployee() {
		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
		driver.findElement(By.xpath("//*[@id=\"employee-information\"]/a")).getText();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("btnAdd")).click();
		
		driver.findElement(By.id("firstName")).sendKeys("febri");
		driver.findElement(By.id("lastName")).sendKeys("test");
		driver.findElement(By.id("chkLogin")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		driver.findElement(By.id("user_name")).clear();
		driver.findElement(By.id("user_name")).sendKeys("febritest021");
		driver.findElement(By.id("user_password")).clear();
		driver.findElement(By.id("user_password")).sendKeys("TEst_123!*");
		driver.findElement(By.id("re_password")).sendKeys("TEst_123!*");
		driver.findElement(By.id("btnSave")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath(" //h1[contains(text(),'Personal Details')]")).getText();
		System.out.println("Successfully saved");
	}

	@Test
	public void viewValidAttendanceRecord() {
		driver.findElement(By.id("menu_time_viewTimeModule")).click();
		driver.findElement(By.id("menu_attendance_Attendance")).click();
		driver.findElement(By.id("menu_attendance_viewAttendanceRecord")).click();
		driver.findElement(By.xpath("//h1[contains(text(),'View Attendance Record')]")).getText();
		
		driver.findElement(By.id("attendance_employeeName_empName")).clear();
		driver.findElement(By.id("attendance_employeeName_empName")).sendKeys("Febri Test");
		driver.findElement(By.id("attendance_date")).clear();
		driver.findElement(By.id("attendance_date")).sendKeys("2020-11-20");
		driver.findElement(By.id("btView")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//td[contains(text(),'febri test')]")).getText();
		System.out.println("Successfully viewed");
	}
	
	@Test
	public void addValidAttendanceRecord1() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("btnPunchOut")).click();
		driver.findElement(By.id("attendance_time")).clear();
		driver.findElement(By.id("attendance_time")).sendKeys("09:00");
		By btnDropdown1 = By.id("attendance_timezone");
		Select dropdown1 = new Select(driver.findElement(By.id("attendance_timezone")));
		dropdown1.selectByValue("9");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("attendance_note")).clear();
		driver.findElement(By.id("attendance_note")).sendKeys("punchin");
		driver.findElement(By.id("btnPunch")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath(" //td[contains(text(),'2020-11-20 09:00:00')]")).getText();
		System.out.println("Successfully saved");
	}
	
	@Test
	public void addValidAttendanceRecord2() {
		driver.findElement(By.id("btnPunchOut")).click();
		driver.findElement(By.id("attendance_time")).clear();
		driver.findElement(By.id("attendance_time")).sendKeys("17:00");
		By btnDropdown1 = By.id("attendance_timezone");
		Select dropdown1 = new Select(driver.findElement(By.id("attendance_timezone")));
		dropdown1.selectByValue("9");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("attendance_note")).clear();
		driver.findElement(By.id("attendance_note")).sendKeys("punchin");
		driver.findElement(By.id("btnPunch")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath(" //td[contains(text(),'2020-11-20 19:00:00')]")).getText();
		System.out.println("Successfully saved");
	}
}
