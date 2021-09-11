import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CartTest extends BaseTest{

    String productLocator = "//*[contains(text(), 'Sauce Labs Backpack')]/ancestor::[contains(@class, 'inventory_item')]/button";

    @Test
    public void buyProduct() {

        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        //driver.get("https://www.saucedemo.com/inventory.html");
        driver.findElement(By.xpath(String.format(productLocator, "Sauce Labs Backpack"))).click();
        driver.findElement(By.xpath("//=[text()='Sauce Labs Backpack'"));
        //*[contains(text(), 'Sauce Labs Backpack')]/../../../button
        //*[contains(text(), 'Sauce Labs Backpack')]/ancestor::[contains(@class, 'inventory_item')]/button
    }

}
