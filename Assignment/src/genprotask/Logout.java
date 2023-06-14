package genprotask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Logout {
	public WebDriver driver;

	public Logout(WebDriver driver) {
		this.driver=driver;	
	}

	public void exitUser() throws InterruptedException {
		driver.findElement(By.id("react-burger-menu-btn")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("logout_sidebar_link")).click();
	}

}
