import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLogin() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String title = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(title,"Products", "Wrong page is opened");

    }


    @Test
    public void locketOutUser() {

        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String error = driver.findElement(By.cssSelector("[data-test-error]")).getText();
        Assert.assertEquals(
                error,"Epic sadface: Sorry, this user has been locked out.", "Wrong error message"
        );
    }
}
