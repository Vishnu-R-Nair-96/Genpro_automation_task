package genprotask;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Checkout {
	public WebDriver driver;
	public String info;
	public static List<WebElement> items;
	public String data;
	public static double currentPrice=0.00,totalPrice=0.00;
	public float totalPriceWithTax,taxPrice;
	public Checkout(WebDriver driver) {
		this.driver=driver;
	}

	public void emptyFields() {
		driver.findElement(By.id("checkout")).click();
		boolean urlStatus = driver.getCurrentUrl().contains("checkout-step-one");
		Assert.assertEquals(urlStatus, true);
		driver.findElement(By.id("continue")).click();
		info=driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3")).getText();
		Assert.assertEquals(info, "Error: First Name is required");
		driver.navigate().refresh();
	}

	public void onlyFirstname() {
		driver.findElement(By.id("first-name")).sendKeys("testfirstname");
		driver.findElement(By.id("continue")).click();
		info=driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3")).getText();
		Assert.assertEquals(info, "Error: Last Name is required");
		driver.navigate().refresh();
	}

	public void onlyLastname() {
		driver.findElement(By.id("last-name")).sendKeys("testlastname");
		driver.findElement(By.id("continue")).click();
		info=driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3")).getText();
		Assert.assertEquals(info, "Error: First Name is required");
		driver.navigate().refresh();		
	}

	public void onlyZip() {
		driver.findElement(By.id("postal-code")).sendKeys("688539");
		driver.findElement(By.id("continue")).click();
		info=driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3")).getText();
		Assert.assertEquals(info, "Error: First Name is required");
		driver.navigate().refresh();
	}

	public void noFirstname() {
		driver.findElement(By.id("last-name")).sendKeys("testlastname");
		driver.findElement(By.id("postal-code")).sendKeys("688539");
		driver.findElement(By.id("continue")).click();
		info=driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3")).getText();
		Assert.assertEquals(info, "Error: First Name is required");
		driver.navigate().refresh();		
	}

	public void noLastname() {
		driver.findElement(By.id("first-name")).sendKeys("testfirstname");
		driver.findElement(By.id("postal-code")).sendKeys("688539");
		driver.findElement(By.id("continue")).click();
		info=driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3")).getText();
		Assert.assertEquals(info, "Error: Last Name is required");
		driver.navigate().refresh();		
	}

	public void noZip() {
		driver.findElement(By.id("first-name")).sendKeys("testfirstname");
		driver.findElement(By.id("last-name")).sendKeys("testlastname");
		driver.findElement(By.id("continue")).click();
		info=driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[1]/div[4]/h3")).getText();
		Assert.assertEquals(info, "Error: Postal Code is required");
		driver.navigate().refresh();		
	}

	public void validData() {
		driver.findElement(By.id("first-name")).sendKeys("testfirstname");
		driver.findElement(By.id("last-name")).sendKeys("testlastname");
		driver.findElement(By.id("postal-code")).sendKeys("688539");
		driver.findElement(By.id("continue")).click();
		boolean urlStatus = driver.getCurrentUrl().contains("checkout-step-two");
		Assert.assertEquals(urlStatus, true);		
	}

	public void itemTotal() {
		items=driver.findElements(By.xpath("//*[@class=\"cart_item\"]"));
		for(WebElement item:items) {
			data=item.findElement(By.className("inventory_item_price")).getText();
			currentPrice = currentPrice+Double.parseDouble(data.replace("$",""));
		}	
		data=driver.findElement(By.className("summary_subtotal_label")).getText();
		totalPrice=Double.parseDouble(data.replace("Item total: $",""));
		Assert.assertEquals(totalPrice, currentPrice);
	}

	public void totalWithTax() {
		data=driver.findElement(By.className("summary_tax_label")).getText();
		taxPrice=Float.parseFloat(data.replace("Tax: $",""));
		totalPrice=totalPrice+taxPrice;
		data=driver.findElement(By.className("summary_total_label")).getText();
		totalPriceWithTax=Float.parseFloat(data.replace("Total: $",""));
		Assert.assertEquals((float)totalPrice, totalPriceWithTax);		
	}

	public void finishAndBackToHome() {
		driver.findElement(By.id("finish")).click();
		boolean urlStatus = driver.getCurrentUrl().contains("checkout-complete");
		Assert.assertEquals(urlStatus, true);
		driver.findElement(By.id("back-to-products")).click();
		boolean urlStatushome = driver.getCurrentUrl().contains("inventory");
	}

}
