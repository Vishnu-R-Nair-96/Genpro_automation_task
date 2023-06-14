package genprotask;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class Login{
	public WebDriver driver;
	public String info;

	public Login(WebDriver driver) {
		this.driver=driver;
	}
	
	public void emptyFields() {
		driver.findElement(By.id("login-button")).click();
		info=driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
		Assert.assertEquals(info, "Epic sadface: Username is required");
		driver.navigate().refresh();
	}
	
	public void emptyPassword() {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("login-button")).click();
		info=driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
		Assert.assertEquals(info, "Epic sadface: Password is required");
		driver.navigate().refresh();
	}
	public void emptyUsername() {
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		info=driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
		Assert.assertEquals(info, "Epic sadface: Username is required");
		driver.navigate().refresh();
	}
	public void invalidData() {
		driver.findElement(By.id("user-name")).sendKeys("standard_use");
		driver.findElement(By.id("password")).sendKeys("secret_sauc");
		driver.findElement(By.id("login-button")).click();
		info=driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
		Assert.assertEquals(info, "Epic sadface: Username and password do not match any user in this service");
		driver.navigate().refresh();
	}
	public void validData() {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		boolean urlStatus = driver.getCurrentUrl().contains("inventory");
		Assert.assertEquals(urlStatus, true);
		driver.navigate().refresh();
	}
}
