package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckOutTests extends BaseTest {

    @Test(groups = {"smoke"}, description = "form checkOut")
    public void positiveCheckout() {
        String testItemName = "Sauce Labs Backpack";
        loginPage.login(USERNAME, PASSWORD);
        productsPage.clickAddToCartButton(testItemName);
        productsPage.clickShoppingCartLink();
        shoppingCartPage.clickCheckOutButton();
        checkOutPage.setFirstNameValue("qwert");
        checkOutPage.setLastNameValue("qwert");
        checkOutPage.setPostalCodeValue("12345");
        checkOutPage.clickContinueButton();
        Assert.assertTrue(checkoutOverviewPage.finishButtonIsDisplayed());
    }

    @Test(groups = {"regression"}, description = "button cancel")
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
