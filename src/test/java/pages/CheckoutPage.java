package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private static final By FIRSTNAME = By.id("first-name");
    private static final By LASTNAME = By.id("last-name");
    private static final By ZIPCODE = By.cssSelector("[data-test='postalCode']");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By FINISH_BUTTON = By.id("finish");
    public static final By CONFIRMATION_MESSAGE = By.className("complete-text");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "checkout-step-one.html");
    }

    public void confirmOrder(String firstName, String lastName, String zipCode) {
        driver.findElement(FIRSTNAME).sendKeys(firstName);
        driver.findElement(LASTNAME).sendKeys(lastName);
        driver.findElement(ZIPCODE).sendKeys(zipCode);
        driver.findElement(CONTINUE_BUTTON).click();
        driver.findElement(FINISH_BUTTON).click();
    }

    public String getOrderSuccessful() {
        return driver.findElement(CONFIRMATION_MESSAGE).getText();
    }
}
