package myprojects.automation.assignment4.tests.login_page;

import myprojects.automation.assignment4.BaseTest;
import myprojects.automation.assignment4.utils.Properties;
import org.testng.annotations.Test;

/**
 * Created by user on 6/12/18.
 */
public class SuccessfullyLogout extends BaseTest {

    //Successfully logout with 'remember me'.
    @Test()
    public void successfullyLoggedIn(){

        driver.get(Properties.getBaseUrl());
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.activatedTab();
        signUpPage.successfullyEnterInputValue(Properties.getAdminEmail(), Properties.getAdminPWD());
        signUpPage.markRememberMe();
        signUpPage.loggedIn();
        signUpPage.myAccountIsVisible();
        signUpPage.logoutFromApp();
    }

}
