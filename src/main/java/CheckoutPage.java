import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class CheckoutPage extends BasePage {
    public WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy(xpath = "//a[text()='Food']")
    public WebElement foodMenu;
    @FindBy(css = ".product-info button")
    public WebElement addToCart;
    @FindBy(id = "city")
    public WebElement cityCart;
    @FindBy(id = "postcode")
    public WebElement postCode;
    @FindBy(css = ".checkout-types.top .button")
    public WebElement btnProceed;
    @FindBy(id = "region_id")
    public WebElement optionRegion;
    @FindBy(id = "billing:region_id")
    public WebElement billingRegion;

    @FindBy(id = "billing:country_id")
    public WebElement billingCountry;
    @FindBy(id = "onepage-guest-register-button")
    public WebElement btnRegister;
    @FindBy(id = "billing:firstname")
    public WebElement firstnameInput;

    @FindBy(id = "billing:lastname")
    public WebElement lastnameInput;

    @FindBy(id = "billing:company")
    public WebElement companyInput;

    @FindBy(id = "billing:email")
    public WebElement emailInput;

    @FindBy(id = "billing:street1")
    public WebElement street1Input;

    @FindBy(id = "billing:street2")
    public WebElement street2Input;

    @FindBy(id = "billing:city")
    public WebElement billingCity;
    @FindBy(id = "billing:postcode")
    public WebElement billingPostcode;
    @FindBy(id = "billing:telephone")
    public WebElement billingTelephone;

    @FindBy(css = "#shipping-method-buttons-container button")
    public WebElement shippingButton;

    @FindBy(css = "#payment-buttons-container button")
    public WebElement paymentButton;

    @FindBy(css = ".btn-checkout")
    public WebElement finalButton;
    @FindBy(css = "#billing-buttons-container button")
    public WebElement billingButton;

    @FindBy(css = ".sub-title")
    public WebElement subtitleElement;
    @FindBy(css = ".col-1 h3")
    public WebElement guestSubtitle;

    @FindBy(css = ".product-cart-actions input")
    public WebElement inputQty;

    @FindBy(css = ".product-cart-actions button")
    public WebElement btnUpdateQty;

    // methods
    public void getFoodMenu() {
        foodMenu.click();
    }

    public void clickAddToCart() {
        addToCart.click();
    }

    public void inputCityCart(String city) {
        cityCart.sendKeys(city);
    }

    public void inputPostCode(String code) {
        postCode.sendKeys(code);
    }

    public void clickBtnProceed() {
        btnProceed.click();
    }

    public void selectOption(WebElement element, String option) {

        Select optionSelect = new Select(element);

        optionSelect.selectByVisibleText(option);
    }

    public void clickBtnRegister() {
        btnRegister.click();
    }

    public void enterFirstname(String firstname) {
        firstnameInput.sendKeys(firstname);
    }


    public void enterLastname(String lastname) {
        lastnameInput.sendKeys(lastname);
    }

    public void enterCompany(String company) {
        companyInput.sendKeys(company);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterStreet1(String street1) {
        street1Input.sendKeys(street1);
    }

    public void enterStreet2(String street2) {
        street2Input.sendKeys(street2);
    }

    public void enterCity(String string) {
        billingCity.sendKeys(string);
    }

    public void enterPostcode(String string) {
        billingPostcode.sendKeys(string);
    }

    public void enterTelephone(String string) {
        billingTelephone.sendKeys(string);
    }

    public void addItemToCart() {
        getFoodMenu();
        clickAddToCart();
    }

    public void addPreliminaryInfo() {
        inputCityCart("Alabama");
        inputPostCode("12345");
        selectOption(optionRegion, "Alabama");
        clickBtnProceed();

    }

    public void fillMandatoryFields() {
        enterFirstname("Test");
        enterLastname("Test3");
        enterCompany("Test5");
        enterEmail("asdf@example.com");
        enterStreet1("adresa mea");
        enterStreet2("adresa mea 2");
        enterCity("Alabama City");
        selectOption(billingRegion, "Alabama");
        enterPostcode("12345");
        enterTelephone("0777777777");
    }

    public void clickOtherButtons() {
        clickWhenReady(billingButton);
        clickWhenReady(shippingButton);
        clickWhenReady(paymentButton);
        clickWhenReady(finalButton);
    }

    public void updateQuantity(WebElement locator, String qty) {
        locator.clear();
        locator.sendKeys(qty);
        btnUpdateQty.click();
    }

    public void clickWhenReady(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void assertWhenReady(WebElement locator, String text) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(locator));
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(locator, text));
        } catch (Exception e) {
            System.out.println("Element/text not visible or incorrect.");
        }
        assertEquals(text, locator.getText());
    }

    public void checkIfCheckoutFieldIsMandatory(WebElement locator) throws InterruptedException {
        addItemToCart();
        addPreliminaryInfo();
        clickBtnRegister();
        fillMandatoryFields();
        locator.clear();
        clickWhenReady(billingButton);
        WebElement target = locator.findElement(By.xpath(".//following-sibling::div"));
        assertWhenReady(target, "This is a required field.");
    }

    public void checkIfCheckoutSelectIsMandatory(WebElement locator) throws InterruptedException {
        addItemToCart();
        addPreliminaryInfo();
        clickBtnRegister();
        fillMandatoryFields();
        locator.findElement(By.xpath(".//option")).click();
        clickWhenReady(billingButton);
        WebElement target = locator.findElement(By.xpath(".//following-sibling::div"));
        assertWhenReady(target, "Please select an option.");
    }
}
