import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class LoginPage extends BasePage {
    public WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(css = ".skip-account span.label")
    private WebElement linkAccount;
    @FindBy(linkText = "Log In")
    private WebElement linkLogin;
    @FindBy(id = "email")
    private WebElement inputEmail;
    @FindBy(id = "pass")
    private WebElement inputPassword;
    @FindBy(id = "send2")
    private WebElement btnLogin;
    @FindBy(css = ".error-msg span")
    public WebElement errorField;

    // methods
    public void clickAccount() {
        linkAccount.click();
    }
    public void clickLoginLink() {
        linkLogin.click();
    }


    public void fillEmail(String mail) {
        inputEmail.sendKeys(mail);
    }

    public void fillPassword(String number) {
        inputPassword.sendKeys(number);
    }

    public void clickLoginButton() {
        btnLogin.click();
    }


}
