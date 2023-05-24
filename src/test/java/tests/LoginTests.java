package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    @Test
    public void positiveLoginPage() {
        loginPage.setUsernameValue("standard_user");
        loginPage.setPasswordValue("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(productsPage.isShoppingCartLinkDisplayed());
    }
    @Test
    public void negativeLoginPage() {
        loginPage.login("", "secret_sauce");
        Assert.assertTrue(loginPage.isErrormessageIsDisplayed());

    }
}
