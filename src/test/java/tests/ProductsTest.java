package tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class ProductsTest extends BaseTest {

    @Test(description = "sort products alphabetically in ascending order", retryAnalyzer = Retry.class)
    public void sortDescOrderName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.selectOption("Name (Z to A)");
        List<String> names = productsPage.getProductNames();
        Assert.assertEquals(names.get(0), "Test.allTheThings() T-Shirt (Red)", "Sorting is not correctly performed");
        Assert.assertEquals(names.get(5), "Sauce Labs Backpack", "Sorting is not correctly performed");
    }

    @Test(description = "sorting products in descending alphabetical order", retryAnalyzer = Retry.class)
    public void sortAscOrderName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.selectOption("Name (A to Z)");
        List<String> names = productsPage.getProductNames();
        Assert.assertEquals(names.get(0), "Sauce Labs Backpack");
        Assert.assertEquals(names.get(5), "Test.allTheThings() T-Shirt (Red)");
    }

    @Test(description = "sorting products by price in ascending order", retryAnalyzer = Retry.class)
    public void sortAscOrderPrice() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.selectOption("Price (low to high)");
        List<String> names = productsPage.getProductNames();
        Assert.assertEquals(names.get(0), "Sauce Labs Onesie");
        Assert.assertEquals(names.get(5), "Sauce Labs Fleece Jacket");
    }

    @Test(description = "sorting goods by price in descending order", retryAnalyzer = Retry.class)
    public void sortDescOrderPrice() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.selectOption("Price (high to low)");
        List<String> names = productsPage.getProductNames();
        Assert.assertEquals(names.get(0), "Sauce Labs Fleece Jacket");
        Assert.assertEquals(names.get(5), "Sauce Labs Onesie");
    }
}
