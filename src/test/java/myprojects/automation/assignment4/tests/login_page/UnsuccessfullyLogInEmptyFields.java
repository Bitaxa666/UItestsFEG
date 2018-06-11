package myprojects.automation.assignment4.tests.login_page;

import myprojects.automation.assignment4.BaseTest;
import myprojects.automation.assignment4.utils.Properties;
import org.testng.annotations.Test;

/**
 * Created by user on 6/11/18.
 */
public class UnsuccessfullyLogInEmptyFields extends BaseTest {

    //Unsuccessfully logged in(Leave Email/Password fields empty)
    @Test
    public void unsuccessfullyLoggedInLeaveFieldsEmpty(){
        driver.get(Properties.getBaseUrl());
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.activatedTab();
        signUpPage.loggedInWithRequiredFields();
    }

}
