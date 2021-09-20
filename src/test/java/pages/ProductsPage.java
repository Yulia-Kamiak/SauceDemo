package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    private static final By TITLE = By.cssSelector(".title");
    public static final String BASE_PRODUCT_ON_PRODUCT_PAGE_LOCATOR = "//*[contains(text(), '%s')]//ancestor::*[contains" +
            "(@class, 'inventory_item_";
    private static final String PRODUCT_LOCATOR = "//*[contains(text(), '%s')]/ancestor::*[contains(@class, " +
            "'inventory_item')]//button";
    private static final String PRODUCT_PRICE_LOCATOR = BASE_PRODUCT_ON_PRODUCT_PAGE_LOCATOR +
            "description')]//descendant::*[contains(@class, 'inventory_item_price')]";
    private static final String PRODUCT_DESCRIPTION_LOCATOR = BASE_PRODUCT_ON_PRODUCT_PAGE_LOCATOR +
            "label')]/child::*[contains(@class, 'inventory_item_desc')]";
    private static final By DROPDOWN = By.xpath("//select[@class='product_sort_container']");
    private static final String PRODUCT_TITLE = ".inventory_item_name";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "/inventory.html");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
    }

    public String getHeader() {
        return driver.findElement(TITLE).getText();
    }

    public void addToCart(String product) {
        driver.findElement(By.xpath(String.format(PRODUCT_LOCATOR, product))).click();
    }

    public String getPrice(String product) {
        return driver.findElement(By.xpath(String.format(PRODUCT_PRICE_LOCATOR, product))).getText();
    }

    public String getProductDescription(String product) {
        return driver.findElement(By.xpath(String.format(PRODUCT_DESCRIPTION_LOCATOR, product))).getText();
    }

    public void removeFromCart(String product) {
        driver.findElement(By.xpath(String.format(PRODUCT_LOCATOR, product))).click();
    }

    public void selectOption(String value) {
        WebElement selectElement = driver.findElement(DROPDOWN);
        Select option = new Select(selectElement);
        option.selectByVisibleText(value);
    }

    public List<String> getProductNames() {
        List<String> stringNames = new ArrayList<>();
        List<WebElement> names = driver.findElements(By.cssSelector(PRODUCT_TITLE));
        for (WebElement element : names) {
            stringNames.add(element.getText());
        }
        return stringNames;
    }
}
