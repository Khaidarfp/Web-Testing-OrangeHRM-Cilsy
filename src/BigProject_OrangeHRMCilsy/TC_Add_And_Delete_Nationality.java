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

public class TC_Add_And_Delete_Nationality {

	public static String browser;
	static WebDriver driver;

	public static void main(String[] args) {
		TC_Add_And_Delete_Nationality test = new TC_Add_And_Delete_Nationality();
		test.setBrowser("Chrome");
		test.setBrowserConfig();
		test.navigateToUrl();
		test.loginWithValidCredential();
		test.addNationalityWithoutCredential();
		test.addNationalityWithCredential();
		test.deleteNationality();
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
	public void addNationalityWithoutCredential() {
		driver.findElement(By.id("menu_admin_viewAdminModule")).click();
		driver.findElement(By.id("menu_admin_nationality")).click();
		driver.findElement(By.xpath("//h1[contains(text(),'Add Nationality')]")).getText();
		
		driver.findElement(By.id("btnAdd")).click();
		driver.findElement(By.id("btnSave")).click();
		
		driver.findElement(By.xpath("//span[contains(text(),'Required')]")).getText();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		System.out.println("Unable to save");
	}
	
	@Test
	public void addNationalityWithCredential() {
		driver.findElement(By.id("nationality_name")).sendKeys("1indonesia");
		driver.findElement(By.id("btnSave")).click();
		
		driver.findElement(By.xpath("//a[contains(text(),'1indonesia')]")).getText();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		System.out.println("Successfully saved");
	}
	
	@Test
	public void deleteNationality() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebElement chkbox = driver.findElement(By.name("chkListRecord[]"));
		chkbox.click();
		if (chkbox.isSelected()) {
			System.out.println("Checkbox is Toggled On");

		} else {
			System.out.println("Checkbox is Toggled Off");
		}
		driver.findElement(By.id("btnDelete")).click();
		System.out.println("Delete success");
	}
}
