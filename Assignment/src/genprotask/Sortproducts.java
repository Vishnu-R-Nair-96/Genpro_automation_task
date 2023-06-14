package genprotask;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Sortproducts {
	public WebDriver driver;
	public static List<WebElement> items;
	public static int count;
	public String data;
	public float currentPrice,lastprice=0;

	public Sortproducts(WebDriver driver) {
		this.driver=driver;
	}
	public void sortLowtohigh() {
		WebElement drpSort=driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select"));
		Select sort=new Select(drpSort);
		sort.selectByVisibleText("Price (low to high)");
		items=driver.findElements(By.xpath("//*[@class=\"inventory_container\"]/div/div"));
		for(WebElement item:items) {
			data=item.findElement(By.className("inventory_item_price")).getText();
			currentPrice=Float.parseFloat(data.replace("$",""));
			Assert.assertTrue(lastprice<=currentPrice);
			lastprice=currentPrice;
		}
	}
	public void addTocart() {
		count=0;
		for(WebElement item:items) {
			count++;
			item.findElement(By.tagName("button")).click();
			String remove=item.findElement(By.tagName("button")).getText();
			Assert.assertEquals(remove, "Remove");
			String cartCount=item.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).getText();
			Assert.assertEquals(count, Integer.parseInt(cartCount));
		}
	}
}
