import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class swagLabTests {

	WebDriver driver = new EdgeDriver();
	String SwagLabURL = "https://www.saucedemo.com/";

	@BeforeTest
	public void mySetup() {

		driver.get(SwagLabURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	}

	@Test(priority = 1)
	public void login() {

		WebElement userNameField = driver.findElement(By.id("user-name"));
		userNameField.sendKeys("standard_user");

		WebElement passwordField = driver.findElement(By.id("password"));
		passwordField.sendKeys("secret_sauce");

		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

	}

	@Test(priority = 2)
	public void sortItemByPriceAsc() {
		Select sortDropdown = new Select(driver.findElement(By.className("product_sort_container")));
		List<WebElement> options = sortDropdown.getOptions();
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().equals("Price (low to high)")) {
				options.get(i).click();
//				sortDropdown.selectByIndex(i);
				break;
			}
		}

	}

	@Test(priority = 3)
	public void addAllItemToTheCart() {

		List<WebElement> addItemButton = driver
				.findElements(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));
		for (int i = 0; i < addItemButton.size(); i++) {
			addItemButton.get(i).click();
		}

	}

	@Test(priority = 4)
	public void checkout() {

		WebElement TheCart = driver.findElement(By.className("shopping_cart_link"));
		TheCart.click();

		WebElement checkoutButton = driver.findElement(By.id("checkout"));
		checkoutButton.click();

		WebElement firstNameField = driver.findElement(By.id("first-name"));
		firstNameField.sendKeys("rand");

		WebElement lastNameField = driver.findElement(By.id("last-name"));
		lastNameField.sendKeys("hasan");

		WebElement postalCodeField = driver.findElement(By.id("postal-code"));
		postalCodeField.sendKeys("80088");

		WebElement continueButton = driver.findElement(By.id("continue"));
		continueButton.click();

		WebElement finchButton = driver.findElement(By.name("finish"));
		finchButton.click();

		String actualResult = driver.findElement(By.className("complete-header")).getText();
		System.out.println(actualResult);
		String ExpectedResult = "Thank you for your order!";
		Assert.assertEquals(actualResult, ExpectedResult);

	}

	@Test(priority = 5)
	public void logout() {

		WebElement burgerMenu = driver.findElement(By.className("bm-burger-button"));
		burgerMenu.click();

		WebElement logoutButton = driver.findElement(By.linkText("Logout"));
		logoutButton.click();

	}

	@AfterTest
	public void closeWebsite() {
		driver.close();
	}

}
