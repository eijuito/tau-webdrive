package login;

import base.BaseTests;
import pages.LoginPage;
import pages.SecureAreaPage;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
public class LoginTests extends BaseTests {

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = homePage.clickFormAuthentication();
        loginPage.setUsername("tomsmith");
        loginPage.setPassword("SuperSecretPassword!");
        SecureAreaPage secureAreaPage = loginPage.clickLoginButton();
        assertTrue(secureAreaPage.getAlertText()
            .contains("You logged into a secure area!"),
            "Alert text is incorrect");
    }
}
