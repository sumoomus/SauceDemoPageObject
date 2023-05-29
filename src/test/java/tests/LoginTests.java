package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import utils.Retry;

public class LoginTests extends BaseTest {
    @Test(groups = {"smoke"}, description = " Login form", retryAnalyzer = Retry.class)
    public void positiveLoginTest() {
        loginPage.setUsernameValue("standard_user");
        loginPage.setPasswordValue("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isShoppingCartLinkDisplayed());
    }

    @Test(groups = {"smoke"}, description = "negat Login form", dataProvider = "negativeLoginTestData")
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
        loginPage.login(username, password);
        Assert.assertEquals(loginPage.getErrorMessageText(), expectedErrorMessage);

    }

    @DataProvider
    public Object[][] negativeLoginTestData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}
        };
    }
}


