package myprojects.automation.assignment4.tests.login_page;

import myprojects.automation.assignment4.BaseTest;
import myprojects.automation.assignment4.utils.Properties;
import org.testng.annotations.Test;

public class SuccessfullyLogIn extends BaseTest {
    @Test ()
    public void successfullyLoggedIn(){
        driver.get(Properties.getBaseUrl());
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.activatedTab();
        signUpPage.successfullyEnterInputValue(Properties.getAdminEmail(), Properties.getAdminPWD());
        signUpPage.loggedIn();
    }
}