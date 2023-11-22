import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest extends Hooks {
    public LoginPage loginPage;
    public CheckoutPage checkoutPage;
    public WebDriverWait wait;

    @Before
    public void setupPageObject() {
        wait = new WebDriverWait(driver, 10);
        loginPage = new LoginPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }


    @Test
    public void negativeLoginTest() throws InterruptedException {
        loginPage.clickAccount();
        loginPage.clickLoginLink();
        loginPage.fillEmail("example@something.ro");
        loginPage.fillPassword("123456");
        loginPage.clickLoginButton();
        checkoutPage.assertWhenReady(loginPage.errorField, "Invalid login or password.");
    }

}
