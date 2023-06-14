package genprotask;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class Testcases {
	public ChromeOptions options;
	public WebDriver driver;
	public String url="https://www.saucedemo.com/";
	public Login login;
	public Sortproducts sortProduct;
	public Cart cart;
	public Checkout checkout;
	public Logout logout;
	
//	Initialize driver objecj and link provided
	@BeforeTest
	public void beforeTest() {
		options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vishn\\Desktop\\selenium\\chromedriver.exe");
		driver= new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(url);
	}
//	Create instances for different pages
	@BeforeMethod
	public void beforeMethod() {
		login=new Login(driver);
		sortProduct=new Sortproducts(driver);
		cart=new Cart(driver);
		checkout=new Checkout(driver);
		logout=new Logout(driver);
	}
	
//	TC_1 login with all empty fields
	@Test(priority=0)
	public void login_emptyFields() {
		login.emptyFields();
	}
	
//	TC_2 login with empty password
	@Test(priority=1)
	public void login_emptyPassword() {
		login.emptyPassword();
	}
	
//	TC_3 login with empty username
	@Test(priority=2)
	public void login_emptyUsername() {
		login.emptyUsername();
	}
	
//	TC_4 login with invalid username and password
	@Test(priority=3)
	public void login_invalidData() {
		login.invalidData();
	}
	
//	TC_5 login with valid username and password
	@Test(priority=4)
	public void login_validData() {
		login.validData();
	}
	
//	TC_6 sort on product listing page with low to high
	@Test(priority=5)
	public void products_sortLowtohigh() {
		sortProduct.sortLowtohigh();
	}
	
//	TC_7 products added to cart
	@Test(priority=6)
	public void products_addTocart() {
		sortProduct.addTocart();
	}
	
//	TC_8 remove products which have price less than $15 from cart
	@Test(priority=7)
	public void cart_remove() {
		cart.cartRemove();
	}
	
//	TC_9 checkout with all empty input fields
	@Test(priority=8)
	public void checkout_emptyFields() {
		checkout.emptyFields();
	}
	
//	TC_10 checkout with only firstname input
	@Test(priority=9)
	public void checkout_onlyFirstname() {
		checkout.onlyFirstname();
	}
	
//	TC_11 checkout with only lastname input
	@Test(priority=10)
	public void checkout_onlyLastname() {
		checkout.onlyLastname();
	}
	
//	TC_12 checkout with only zipcode input
	@Test(priority=11)
	public void checkout_onlyZip() {
		checkout.onlyZip();
	}
	
//	TC_13 checkout with no firstname input and have other fields filled
	@Test(priority=12)
	public void checkout_noFirstname() {
		checkout.noFirstname();
	}
	
//	TC_14 checkout with no lastname input and have other fields filled
	@Test(priority=13)
	public void checkout_noLastname() {
		checkout.noLastname();
	}
	
//	TC_15 checkout with no zipcode input and have other fields filled
	@Test(priority=14)
	public void checkout_noZip() {
		checkout.noZip();
	}
	
//	TC_16 checkout with all fields filled
	@Test(priority=15)
	public void checkout_validData() {
		checkout.validData();
	}
	
//	TC_17 validate products price total without tax
	@Test(priority=16)
	public void checkout_itemTotal() {
		checkout.itemTotal();
	}
	
//	TC_18 validate products price total including tax
	@Test(priority=17)
	public void checkout_totalWithTax() {
		checkout.totalWithTax();
	}
	
//	TC_19 finish checkout and goto home page
	@Test(priority=18)
	public void checkout_finishAndBackToHome() {
		checkout.finishAndBackToHome();
	}
	
//	TC_20 logout the user session
	@Test(priority=19)
	public void logout() throws InterruptedException {
		logout.exitUser();
	}
	
//	release driver object
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
