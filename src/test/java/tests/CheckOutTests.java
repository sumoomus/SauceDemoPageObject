package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckOutTests extends BaseTest {

    @Test
    public void positiveCheckout() {
        String testItemName = "Sauce Labs Backpack";
        loginPage.login(USERNAME, PASSWORD);
        productsPage.clickAddToCartButton(testItemName);
        productsPage.clickShoppingCartLink();
        shoppingCartPage.clickCheckOutButton();
        checkOutPage.setFirstNameValue("test");
        checkOutPage.setLastNameValue("test");
        checkOutPage.setPostalCodeValue("12345");
        checkOutPage.clickContinueButton();
        Assert.assertTrue(checkoutOverviewPage.finishButtonIsDisplayed());
    }

    @Test
    public void cancelButtonTest() {
        String testItemName = "Sauce Labs Backpack";
        loginPage.login(USERNAME, PASSWORD);
        productsPage.clickAddToCartButton(testItemName);
        productsPage.clickShoppingCartLink();
        shoppingCartPage.clickCheckOutButton();
        checkOutPage.clickCancelButton();
        Assert.assertTrue(shoppingCartPage.isContinueShoppingIsDisplayed());

    }
}