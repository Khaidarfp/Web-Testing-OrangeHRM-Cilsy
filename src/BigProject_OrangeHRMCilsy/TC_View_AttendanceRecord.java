package BigProject_OrangeHRMCilsy;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;

public class TC_View_AttendanceRecord {

	public static String browser;
	static WebDriver driver;

	public static void main(String[] args) {
		TC_View_AttendanceRecord test = new TC_View_AttendanceRecord();
		test.setBrowser("Chrome");
		test.setBrowserConfig();
		test.navigateToUrl();
		test.loginWithValidCredential();
		test.viewInvalidAttendanceRecord();
		test.viewValidAttendanceRecord();
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
	public void viewInvalidAttendanceRecord() {
		driver.findElement(By.id("menu_time_viewTimeModule")).click();
		driver.findElement(By.id("menu_attendance_Attendance")).click();
		driver.findElement(By.id("menu_attendance_viewAttendanceRecord")).click();
		driver.findElement(By.xpath("//h1[contains(text(),'View Attendance Record')]")).getText();

		driver.findElement(By.id("attendance_employeeName_empName")).sendKeys("namasaya");
		driver.findElement(By.id("btView")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[contains(text(),'Invalid Employee Name')]")).getText();
		driver.findElement(By.xpath("//span[contains(text(),'Should be a valid date in yyyy-mm-dd format')]")).getText();
		System.out.println("Unable to save");
	}

	@Test
	public void viewValidAttendanceRecord() {
		driver.findElement(By.id("attendance_employeeName_empName")).clear();
		driver.findElement(By.id("attendance_employeeName_empName")).sendKeys("Febri Test");
		driver.findElement(By.id("attendance_date")).clear();
		driver.findElement(By.id("attendance_date")).sendKeys("2020-11-20");
		driver.findElement(By.id("btView")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//td[contains(text(),'febri test')]")).getText();
		System.out.println("Successfully saved");
	}
}
