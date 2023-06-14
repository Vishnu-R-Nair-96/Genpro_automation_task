package genprotask;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Cart {
	public WebDriver driver;
	public static List<WebElement> items;
	public static int count;
	public String data;
	public float currentPrice;
	public Cart(WebDriver driver) {
		this.driver=driver;
	}
	public void cartRemove() {
		count=Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText());
		driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
		boolean urlStatus = driver.getCurrentUrl().contains("cart");
		Assert.assertEquals(urlStatus, true);
		items=driver.findElements(By.xpath("//*[@class=\"cart_item\"]"));
		for(WebElement item:items) {
			data=item.findElement(By.className("inventory_item_price")).getText();
			currentPrice=Float.parseFloat(data.replace("$",""));
			if(currentPrice<15) {
				item.findElement(By.tagName("button")).click();
				count--;
				String cartCount=item.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();
				Assert.assertEquals(count, Integer.parseInt(cartCount));
			}
		}
	}
}
