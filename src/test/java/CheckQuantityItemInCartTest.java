import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CheckQuantityItemInCartTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(new FirefoxOptions().addArguments("headless"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[value=Login]")).click();

    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }

    @Test
    public void checkQuantityOfItemInTheCart() {

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        String quantityItem = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(quantityItem,"2" );

    }

    @Test
    public void removeItemFromTheCartAndCheckQuantity() {

        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        String item = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(item,"3");
        driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.id("remove-test.allthethings()-t-shirt-(red)")).click();
        String item1 = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(item1,"1");

    }

    @Test
    public void orderItem() {

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Nik");
        driver.findElement(By.id("last-name")).sendKeys("Pitt");
        driver.findElement(By.id("postal-code")).sendKeys("11111");
        driver.findElement(By.className("submit-button btn btn_primary cart_button btn_action")).click();
        driver.findElement(By.className("btn btn_action btn_medium cart_button")).click();
        String order = driver.findElement(By.className("complete-header")).getText();
        Assert.assertEquals(order, "THANK YOU FOR YOUR ORDER");

    }

    @Test
    public void checkoutInInAnEmptyCart() {

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Nik");
        driver.findElement(By.id("last-name")).sendKeys("Pitt");
        driver.findElement(By.id("postal-code")).sendKeys("11111");
        driver.findElement(By.className("submit-button btn btn_primary cart_button btn_action")).click();
        driver.findElement(By.className("btn btn_action btn_medium cart_button")).click();
        String emptyOrder = driver.findElement(By.className("complete-header")).getText();
        Assert.assertEquals(emptyOrder, "THANK YOU FOR YOUR ORDER");

    }

    @Test
    public void loginAddItemAndCheckCostAndName() {

        WebElement itemName = driver.findElement(By.className("inventory_item_name"));
        String copyItemName = itemName.getText();
        WebElement itemPrice = driver.findElement(By.className("inventory_item_price"));
        String copyItemPrice = itemPrice.getText();
        driver.findElement(By.name("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        Assert.assertEquals(
                driver.findElement(By.className("inventory_item_name")).getText(), copyItemName,
                "Item name is incorrect"
        );
        Assert.assertEquals(
                driver.findElement(By.className("inventory_item_price")).getText(), copyItemPrice,
                "Item price is incorrect"
        );

    }
}
