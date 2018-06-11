package myprojects.automation.assignment4.tests.login_page;

import myprojects.automation.assignment4.BaseTest;
import myprojects.automation.assignment4.utils.Properties;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by user on 6/7/18.
 */
public class UnsuccessfullyLogInIncorrectPwd extends BaseTest {

    private final String INCORRECT_PWD = "passworddd";
    private final String INCORRECT_EMAIL = "11greg@element5digital.com";
    private final String INCORRECT_EMAIL_WITHOUT_SPEC_SYMBOL_AND_DOMAIN
            = "greg.element5digital";
    private final String INCORRECT_EMAIL_WITHOUT_DOMAIN
            = "greg@";



    //Unsuccessfully logged in(correct Email/Password-wrong)
    @Test()
    public void unsuccessfullyLoggedInWithCorrectEmailIncorrectPWD(){
        driver.get(Properties.getBaseUrl());
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.activatedTab();
        signUpPage.successfullyEnterInputValue(Properties.getAdminEmail(), INCORRECT_PWD);
        signUpPage.loggedInWithAlert();
    }

    //Unsuccessfully logged in(wrong Email/Password)
    @Test()
    public void unsuccessfullyLoggedInWithInCorrectEmailIncorrectPWD(){

        Random randomProduct = new Random();
        randomProduct.nextInt(7);

        driver.navigate().refresh();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.activatedTab();
        signUpPage.successfullyEnterInputValue(INCORRECT_EMAIL, INCORRECT_PWD);
        signUpPage.loggedInWithAlert();
    }

    //Unsuccessfully logged in(wrong Email body/Password - correct)
    @Test()
    public void unsuccessfullyLoggedInWithInCorrectEmailBody(){

        driver.navigate().refresh();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.activatedTab();
        signUpPage.successfullyEnterInputValue( INCORRECT_EMAIL_WITHOUT_SPEC_SYMBOL_AND_DOMAIN,
                Properties.getAdminPWD());
        signUpPage.loggedInWithInCorrectEmailBody();
    }

    //Unsuccessfully logged in(wrong Email body/Password - correct)
    @Test()
    public void unsuccessfullyLoggedInWithoutEmailDomain(){

        driver.navigate().refresh();
        SignUpPage signUpPage = new SignUpPage(driver);

        signUpPage.activatedTab();
        signUpPage.successfullyEnterInputValue( INCORRECT_EMAIL_WITHOUT_DOMAIN ,
                "password");
        signUpPage.loggedInWithInCorrectEmailBody();
        signUpPage.incorrectEmailBodyMessage();
    }
}
