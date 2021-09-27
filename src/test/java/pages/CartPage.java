package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    public static final By CHECKOUT_BUTTON = By.id("checkout");
    public static final String BASE_PRODUCT_IN_CART_LOCATOR = "//*[contains(text(),'%s')]//ancestor::*[contains(@class, " +
            "'cart_item_label')]//descendant::*[contains(@class, ";
    private static final String PRODUCT_PRICE_LOCATOR = BASE_PRODUCT_IN_CART_LOCATOR + "'inventory_item_price')]";
    private static final String PRODUCT_DESCRIPTION_LOCATOR = BASE_PRODUCT_IN_CART_LOCATOR + "'inventory_item_desc')]";
    public static final String PRODUCT_DISPLAY = "//*[contains(text(), '%s')]";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "/cart.html");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BUTTON));
    }

    public boolean isProductDisplayed(String product) {
        return driver.findElement(By.xpath(String.format(PRODUCT_DISPLAY, product))).isDisplayed();
    }

    public String getPrice(String product) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(PRODUCT_PRICE_LOCATOR, product))));
        return driver.findElement(By.xpath(String.format(PRODUCT_PRICE_LOCATOR, product))).getText();
    }

    public String getProductDescription(String product) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(PRODUCT_DESCRIPTION_LOCATOR, product))));
        return driver.findElement(By.xpath(String.format(PRODUCT_DESCRIPTION_LOCATOR, product))).getText();
    }
}