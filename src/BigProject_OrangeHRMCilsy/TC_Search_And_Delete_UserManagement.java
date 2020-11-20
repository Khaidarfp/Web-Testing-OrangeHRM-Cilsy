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

public class TC_Search_And_Delete_UserManagement {

	public static String browser;
	static WebDriver driver;

	public static void main(String[] args) {
		TC_Search_And_Delete_UserManagement test = new TC_Search_And_Delete_UserManagement();
		test.setBrowser("Chrome");
		test.setBrowserConfig();
		test.navigateToUrl();
		test.loginWithValidCredential();
		test.searchingUser();
		test.deleteUser();
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
	public void deleteUser() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebElement chkbox = driver.findElement(By.cssSelector("#ohrmList_chkSelectRecord_40"));
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
