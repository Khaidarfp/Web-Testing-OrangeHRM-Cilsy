package BigProject_OrangeHRMCilsy;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;

public class TC_Add_And_Edit_AttendanceRecord {

	public static String browser;
	static WebDriver driver;

	public static void main(String[] args) {
		TC_Add_And_Edit_AttendanceRecord test = new TC_Add_And_Edit_AttendanceRecord();
		test.setBrowser("Chrome");
		test.setBrowserConfig();
		test.navigateToUrl();
		test.loginWithValidCredential();
		test.addInvalidAttendanceRecord1();
		test.addValidAttendanceRecord1();
		test.addInvalidAttendanceRecord2();
		test.addValidAttendanceRecord2();
		test.changeAttendanceRecordWithInvalidCredential();
		test.changeAttendanceRecordWithValidCredential();
		test.deleteAttendanceRecord();
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public void setBrowserConfig() {
		String projectAttendanceRecord = System.getProperty("user.dir");

		if (browser.contains("Chrome")) {
			System.setProperty("webdriver.chrome.driver", projectAttendanceRecord + "\\lib\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		if (browser.contains("Firefox")) {
			System.setProperty("webdriver.firefox.driver", projectAttendanceRecord + "\\lib\\driver\\geckodriver.exe");
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
	public void addInvalidAttendanceRecord1() {
		driver.findElement(By.id("btnPunchOut")).click();
		driver.findElement(By.id("attendance_time")).sendKeys("60:60");
		By btnDropdown1 = By.id("attendance_timezone");
		Select dropdown1 = new Select(driver.findElement(By.id("attendance_timezone")));
		dropdown1.selectByValue("9");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("attendance_note")).sendKeys("punchin");
		driver.findElement(By.id("btnPunch")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[@id='timeErrorHolder']")).getText();
		System.out.println("Unable to save");
	}

	@Test
	public void addValidAttendanceRecord1() {
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
	public void addInvalidAttendanceRecord2() {
		driver.findElement(By.id("btnPunchOut")).click();
		driver.findElement(By.id("attendance_time")).sendKeys("08:00");
		By btnDropdown1 = By.id("attendance_timezone");
		Select dropdown1 = new Select(driver.findElement(By.id("attendance_timezone")));
		dropdown1.selectByValue("9");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("attendance_note")).sendKeys("punchout");
		driver.findElement(By.id("btnPunch")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[@id='timeErrorHolder']")).getText();
		System.out.println("Unable to save");
	}

	@Test
	public void addValidAttendanceRecord2() {
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
	
	@Test
	public void changeAttendanceRecordWithInvalidCredential() {
		driver.findElement(By.name("btnEdit")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("attendance_punchInTime_1")).sendKeys("09:30");
		driver.findElement(By.id("attendance_punchOutTime_1")).sendKeys("08:30");
		driver.findElement(By.id("validationMsg")).getText();
		
		driver.findElement(By.id("213_1_3")).click();
		driver.findElement(By.id("punchInOutNote")).sendKeys("masuk");
		driver.findElement(By.id("commentSave")).click();
		driver.findElement(By.id("213_1_4")).click();
		driver.findElement(By.id("punchInOutNote")).sendKeys("keluar");
		driver.findElement(By.id("commentSave")).click();
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("btnSave")).click();
		System.out.println("Unable to edit");
	}
	
	@Test
	public void changeAttendanceRecordWithValidCredential() {
		driver.findElement(By.id("attendance_punchInTime_1")).clear();
		driver.findElement(By.id("attendance_punchInTime_1")).sendKeys("09:30");
		driver.findElement(By.id("attendance_punchOutTime_1")).clear();
		driver.findElement(By.id("attendance_punchOutTime_1")).sendKeys("08:30");
		driver.findElement(By.id("validationMsg")).getText();
		
		driver.findElement(By.id("213_1_3")).click();
		driver.findElement(By.id("punchInOutNote")).clear();
		driver.findElement(By.id("punchInOutNote")).sendKeys("masuk");
		driver.findElement(By.id("commentSave")).click();
		driver.findElement(By.id("213_1_4")).click();
		driver.findElement(By.id("punchInOutNote")).clear();
		driver.findElement(By.id("punchInOutNote")).sendKeys("keluar");
		driver.findElement(By.id("commentSave")).click();
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("btnSave")).click();
		System.out.println("Successfully edited");
	}
	
	@Test
	public void deleteAttendanceRecord() {
		driver.findElement(By.name("chkSelectRow[]")).click();
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("okBtn")).click();
	}
}
