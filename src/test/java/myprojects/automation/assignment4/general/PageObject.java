package myprojects.automation.assignment4.general;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by user on 10/3/17.
 */
public class PageObject {
    protected EventFiringWebDriver driver;
   // protected WebDriverWait wait;

    //Base Page class
    public PageObject(EventFiringWebDriver driver){
        //WebDriverWait wait
        this.driver = driver;
    //    this.wait = wait;
        PageFactory.initElements(driver, this);
    }
}
