package tests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void buyProduct() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        cartPage.open();
    }

    @Test
    public void checkPriceInCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        String productPriceOnProductPage = productsPage.getPrice("Sauce Labs Backpack");
        cartPage.open();
        String productPriceOnCartPage = cartPage.getPrice("Sauce Labs Backpack");
        Assert.assertEquals(productPriceOnCartPage, productPriceOnProductPage, "This price is incorrect");
    }

    @Test
    public void addTwoItemsInCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        productsPage.addToCart("Sauce Labs Fleece Jacket");
        cartPage.open();
        Assert.assertTrue(cartPage.isProductDisplayed("Sauce Labs Bolt T-Shirt"));
        Assert.assertTrue(cartPage.isProductDisplayed("Sauce Labs Fleece Jacket"));
    }

    @Test
    public void makeAnOrder() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        cartPage.open();
        checkoutPage.open();
        checkoutPage.confirmOrder("Yul", "Kom", "11111");
        Assert.assertEquals(checkoutPage.getOrderSuccessful(),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!",
                "Your order is failed");
    }

    @Test
    public void problemUserDeletesFromCart() {
        loginPage.open();
        loginPage.login("problem_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.removeFromCart("Sauce Labs Backpack");
        cartPage.open();
        Assert.assertTrue(cartPage.isProductDisplayed("Sauce Labs Backpack"),
                "Problem user can delete an item from cart");
    }

    @Test
    public void checkItemDescriptionInCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        String productOnProductPageDescription = productsPage.getProductDescription("Sauce Labs Backpack");
        cartPage.open();
        String productInCartDescription = cartPage.getProductDescription("Sauce Labs Backpack");
        Assert.assertEquals(productInCartDescription, productOnProductPageDescription, "The description is wrong");
    }
}