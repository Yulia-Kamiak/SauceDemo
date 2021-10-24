package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {


    @Test(description = "check that user can login", retryAnalyzer = Retry.class)
    public void validLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        String title = productsPage.getHeader();
        assertEquals(title, "PRODUCTS", "Wrong page is opened");
    }

    @Test(description = "locked user can't login", retryAnalyzer = Retry.class)
    public void lockedUser() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        assertEquals(loginPage.getError(), "Epic sadface: Sorry, this user has been locked out.",
                "Wrong error message");
    }

}
