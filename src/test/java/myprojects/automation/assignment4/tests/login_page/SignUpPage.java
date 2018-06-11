package myprojects.automation.assignment4.tests.login_page;

import myprojects.automation.assignment4.general.PageObject;
import myprojects.automation.assignment4.utils.logging.CustomReporter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

/**
 * Created by user on 10/3/17.
 */
public class SignUpPage extends PageObject {

    SoftAssert softAssert = new SoftAssert();

    //Find the 'Partner Login' Tab.
    @CacheLookup
    @FindBy(css=".sbox-content>ul:nth-child(2)>li:nth-child(2)")
    private WebElement partnerLoginTab;

    @CacheLookup
    @FindBy(name="email")
    private WebElement email;

    @CacheLookup
    @FindBy(name="password")
    private WebElement password;

    @CacheLookup
    @FindBy(id = "loginBtn")
    private WebElement submitButton;

    @CacheLookup
    @FindBy (css=".alert-danger")
    private WebElement alertDanger;

    @CacheLookup
    @FindBy (css=".parsley-error-list")
    private WebElement emailInvalidFormat;


    WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 10).withMessage("Element was not found");

    //Method for login on the site
    public SignUpPage(EventFiringWebDriver driver) {
        super(driver);
    }

    public void activatedTab(){

        wait.until(ExpectedConditions.visibilityOf(this.partnerLoginTab));
        this.partnerLoginTab.click();
    }

    public void successfullyEnterInputValue(String eml, String pwd) {
        try {

            //founds end enters correct email
            wait.until(ExpectedConditions.visibilityOf(this.email));
            this.email.clear();
            this.email.sendKeys(eml);

            //founds and enters correct pwd
            wait.until(ExpectedConditions.visibilityOf(this.password));
            this.password.clear();
            this.password.sendKeys(pwd);

            CustomReporter.logAction("User is input all value");

        } catch (UnsupportedOperationException ue) {
            ue.printStackTrace();
            System.out.println("User doesn't logged in to the \'Work-Panel\'");
        }
    }

    public void loggedIn(){
        wait.until(ExpectedConditions.visibilityOf(this.submitButton));
        this.submitButton.click();
        CustomReporter.logAction("Click on 'Login' button");
    }
    public void loggedInWithAlert(){
        wait.until(ExpectedConditions.visibilityOf(this.submitButton));
        this.submitButton.click();
        wait.until(ExpectedConditions.visibilityOf(this.alertDanger));
        softAssert.assertEquals(this.alertDanger.getText(), "x\n" +
                "Your username/password combination was incorrect");
        /*this.alertDanger.getText().equals("\" Your username/password combination was incorrect \"");*/
        CustomReporter.logAction("Click on 'Login' button with alert-danger");
        CustomReporter.logAction("Message in alert-dangerblock is correct" + this.alertDanger.getText());
        softAssert.assertAll();
    }
    public void loggedInWithRequiredFields(){
        wait.until(ExpectedConditions.visibilityOf(this.submitButton));
        this.submitButton.click();
        softAssert.assertEquals(this.email.getAttribute("required"), "true");
        CustomReporter.logAction("Email is a Required field : " + this.email.getAttribute("required"));
        softAssert.assertEquals(this.password.getAttribute("required"), "true");
        CustomReporter.logAction("Password is a Required field : " + this.email.getAttribute("required"));
        CustomReporter.logAction("Click on 'Login' button with required fields");
        softAssert.assertAll();
    }

    public void loggedInWithInCorrectEmailBody(){
        wait.until(ExpectedConditions.visibilityOf(this.submitButton));
        this.submitButton.click();
        softAssert.assertEquals(this.emailInvalidFormat.getText(), "The email format is invalid.");
        CustomReporter.logAction("Email is incorrect. Error message: " + this.emailInvalidFormat.getText());
        CustomReporter.logAction("Click on 'Login' button with invalid email body");
        softAssert.assertAll();
    }
    public void incorrectEmailBodyMessage(){

        softAssert.assertEquals(this.alertDanger.getText(), "x\n" +
                "The following  errors occurred");
        /*this.alertDanger.getText().equals("\" Your username/password combination was incorrect \"");*/
        CustomReporter.logAction("Click on 'Login' button with alert: \"The following  errors occurred\"");
    }

}
