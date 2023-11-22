import org.junit.Test;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class CheckoutTest extends Hooks {
    public CheckoutPage checkoutPage;
    public WebDriverWait wait;

    @Before
    public void setupPageObject() {
        wait = new WebDriverWait(driver, 10);
        checkoutPage = new CheckoutPage(driver);
    }


    @Test
    public void checkoutAsGuest() throws InterruptedException {
        checkoutPage.addItemToCart();
        checkoutPage.addPreliminaryInfo();
        checkoutPage.assertWhenReady(checkoutPage.guestSubtitle, "CHECKOUT AS A GUEST OR REGISTER");
        checkoutPage.clickBtnRegister();
        checkoutPage.fillMandatoryFields();
        checkoutPage.clickOtherButtons();
        checkoutPage.assertWhenReady(checkoutPage.subtitleElement, "THANK YOU FOR YOUR PURCHASE!");
    }

    public void checkoutAsLoggedInUser() {

    }
    @Test
    public void checkoutWithUpdatedQuantity() throws InterruptedException {
         checkoutPage.addItemToCart();
         checkoutPage.updateQuantity(checkoutPage.inputQty, "4");
         checkoutPage.addPreliminaryInfo();
         checkoutPage.assertWhenReady(checkoutPage.guestSubtitle, "CHECKOUT AS A GUEST OR REGISTER");
         checkoutPage.clickBtnRegister();
         checkoutPage.fillMandatoryFields();
         checkoutPage.clickOtherButtons();
         checkoutPage.assertWhenReady(checkoutPage.subtitleElement, "THANK YOU FOR YOUR PURCHASE!");
    }

     @Test
     public void checkMandatoryFields() throws InterruptedException {

        checkoutPage.checkIfCheckoutFieldIsMandatory(checkoutPage.firstnameInput);
        checkoutPage.checkIfCheckoutFieldIsMandatory(checkoutPage.lastnameInput);
        checkoutPage.checkIfCheckoutFieldIsMandatory(checkoutPage.emailInput);
        checkoutPage.checkIfCheckoutFieldIsMandatory(checkoutPage.street1Input);
        checkoutPage.checkIfCheckoutFieldIsMandatory(checkoutPage.billingCity);
        checkoutPage.checkIfCheckoutFieldIsMandatory(checkoutPage.billingPostcode);
        checkoutPage.checkIfCheckoutFieldIsMandatory(checkoutPage.billingTelephone);
        checkoutPage.checkIfCheckoutSelectIsMandatory(checkoutPage.billingRegion);
        checkoutPage.checkIfCheckoutSelectIsMandatory(checkoutPage.billingCountry);
     }
}
