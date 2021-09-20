package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

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
    }

    public boolean isProductDisplayed(String product) {
        return driver.findElement(By.xpath(String.format(PRODUCT_DISPLAY, product))).isDisplayed();
    }

    public String getPrice(String product) {
        return driver.findElement(By.xpath(String.format(PRODUCT_PRICE_LOCATOR, product))).getText();
    }

    public String getProductDescription(String product) {
        return driver.findElement(By.xpath(String.format(PRODUCT_DESCRIPTION_LOCATOR, product))).getText();
    }
}