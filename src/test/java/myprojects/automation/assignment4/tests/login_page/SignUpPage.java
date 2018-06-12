package myprojects.automation.assignment4.tests.login_page;

import myprojects.automation.assignment4.general.PageObject;
import myprojects.automation.assignment4.utils.logging.CustomReporter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    @FindBy(name = "remember")
    private WebElement rememberMe;

    @CacheLookup
    @FindBy (css=".alert-danger")
    private WebElement alertDanger;

    @CacheLookup
    @FindBy (css=".parsley-error-list")
    private WebElement emailInvalidFormat;

    @CacheLookup
    @FindBy (css=".user.myAccount>a")
    private WebElement myAccountMenu;

    @CacheLookup
    @FindBy (css="#page-wrapper>div:nth-child(1)>nav>ul>li.user.dropdown.item_title.myAccount>a")
    private WebElement ajaxLoad;

    @CacheLookup
    @FindBy (name="search_all_fields")
    private WebElement searchInputOrders;

    @CacheLookup
    @FindBy (css=".fa.fa-sign-out")
    private WebElement logoutIcon;

    @CacheLookup
    @FindBy (css=".alert.alert-info")
    private WebElement logoutMessage;


    WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 20).withMessage("Element was not found");

    //Method for login on the site
    public SignUpPage(EventFiringWebDriver driver) {
        super(driver);
    }

    //Method is activated 'Partner' Tab.
    public void activatedTab(){

        wait.until(ExpectedConditions.visibilityOf(this.partnerLoginTab));
        this.partnerLoginTab.click();
    }

    //Mark the 'Remember me?' checkbox.
    public void markRememberMe(){
        wait.until(ExpectedConditions.visibilityOf(this.rememberMe));
        this.rememberMe.click();
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

    public void myAccountIsVisible(){

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(1190, 28)"); // if the element is on top.
        wait.until(ExpectedConditions.visibilityOf(this.myAccountMenu));
        CustomReporter.logAction("'My Account' menu is displayed");
        /*actions.moveToElement(this.myAccountMenu).click();*/
       /* this.myAccountMenu.click();*/
        softAssert.assertTrue(this.myAccountMenu.isDisplayed());
        softAssert.assertAll();
    }

    public void logoutFromApp(){

        Actions actions = new Actions(driver);

        wait.until(ExpectedConditions.visibilityOf(this.searchInputOrders));
        this.searchInputOrders.clear();

        actions.moveToElement(this.ajaxLoad).click().build().perform();
        /*this.ajaxLoad.click();*/
        CustomReporter.logAction("Click on 'My Account'");
        wait.until(ExpectedConditions.visibilityOf(this.logoutIcon));
        softAssert.assertTrue(this.logoutIcon.isDisplayed());
        CustomReporter.logAction("'Logout' item is displayed");
        this.logoutIcon.click();
        CustomReporter.logAction("User is Logout");
        wait.until(ExpectedConditions.visibilityOf(this.logoutMessage));
        softAssert.assertTrue(this.logoutMessage.isDisplayed());
        CustomReporter.logAction("Logout message is shown");
        softAssert.assertAll();
    }

}
