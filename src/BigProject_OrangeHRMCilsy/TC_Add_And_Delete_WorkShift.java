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

public class TC_Add_And_Delete_WorkShift {

	public static String browser;
	static WebDriver driver;

	public static void main(String[] args) {
		TC_Add_And_Delete_WorkShift test = new TC_Add_And_Delete_WorkShift();
		test.setBrowser("Chrome");
		test.setBrowserConfig();
		test.navigateToUrl();
		test.loginWithValidCredential();
		test.addWorkShiftWithoutCredential();
		test.addWorkShiftWithHighFromTime();
		test.addWorkShiftWithValidCredential();
		test.deleteWorkShift();
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
	public void addWorkShiftWithoutCredential() {
		driver.findElement(By.id("menu_admin_viewAdminModule")).click();
		driver.findElement(By.id("menu_admin_Job")).click();
		driver.findElement(By.id("menu_admin_workShift")).click();
		driver.findElement(By.xpath("//h1[contains(text(),'Work Shifts')]")).getText();

		driver.findElement(By.id("btnAdd")).click();
		driver.findElement(By.id("btnSave")).click();

		driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		System.out.println("Unable to save");
	}

	@Test
	public void addWorkShiftWithHighFromTime() {
		driver.findElement(By.id("workShift_name")).sendKeys("lembur");
		By btnDropdown1 = By.id("workShift_workHours_from");
		Select dropdown1 = new Select(driver.findElement(By.id("workShift_workHours_from")));
		dropdown1.selectByValue("17:00");
		By btnDropdown2 = By.id("workShift_workHours_to");
		Select dropdown2 = new Select(driver.findElement(By.id("workShift_workHours_to")));
		dropdown1.selectByValue("09:15");
		
		driver.findElement(By.xpath("//span[contains(text(),'From time should be less than To time')]")).getText();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("btnSave")).click();
		System.out.println("Unable to save");
	}
	
	@Test
	public void addWorkShiftWithValidCredential() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		By btnDropdown1 = By.id("workShift_workHours_from");
		Select dropdown1 = new Select(driver.findElement(By.id("workShift_workHours_from")));
		dropdown1.selectByValue("09:15");
		By btnDropdown2 = By.id("workShift_workHours_to");
		Select dropdown2 = new Select(driver.findElement(By.id("workShift_workHours_to")));
		dropdown1.selectByValue("17:00");
		driver.findElement(By.id("btnSave")).click();

		driver.findElement(By.xpath("//a[contains(text(),'lembur')]")).getText();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		System.out.println("Successfully saved");
	}

	@Test
	public void deleteWorkShift() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebElement chkbox = driver.findElement(By.cssSelector("#ohrmList_chkSelectRecord_47"));
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
}
