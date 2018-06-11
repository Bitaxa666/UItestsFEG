package myprojects.automation.assignment4.tests;

import myprojects.automation.assignment4.general.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by user on 10/9/17.
 */
public class CreateNewProduct extends PageObject {

//    public String firstName;
//    public String firstCount;
//    public String firstPrice;


    WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 10).withMessage("Element was not found");

    @CacheLookup
     @FindBy(xpath = ".//*[@id='employee_infos']/a/span/img")
  //  @FindBy(xpath = "html/body/header/nav/div[3]/div/div[1]/i")
    private WebElement logOutIcon;

    @CacheLookup
    @FindBy(xpath = "html/body/header/nav/div[3]/div/div[1]/i")
    private WebElement logOutIcon2;

//    @FindBy(xpath =".//i[text()='store']")
//    private WebElement menuItem;
    @CacheLookup
    @FindBy(id = "subtab-AdminCatalog")
    private WebElement catalog;

    @CacheLookup
    @FindBy(xpath = ".//span[text()='Новый товар']")
    private WebElement newProductBtn;

    @CacheLookup
    @FindBy(id = "form_step1_name_1")
    private WebElement newProductName;

    @CacheLookup
    @FindBy(id = "form_step1_qty_0_shortcut")
    private WebElement newCount;

    @CacheLookup
    @FindBy(id = "form_step1_price_shortcut")
    private WebElement newPrice;

    @CacheLookup
    @FindBy(css = ".switch-input")
    private WebElement productActivate;

    @CacheLookup
    @FindBy (xpath = ".//*[@id='growls']/div/div[3]")
    private WebElement popUpMessage;

    @CacheLookup
    @FindBy (css = ".growl-close")
    private WebElement closePopUp;

    @CacheLookup
    @FindBy (id = "submit")
    private WebElement saveBtn;

    @CacheLookup
    @FindBy (xpath = ".//*[@id='growls']/div/div[1]")
    private WebElement savePopUpClose;



    public CreateNewProduct(EventFiringWebDriver driver) {
        super(driver);
        //ProductData.generate();
            }

    public void markCatalogItem() {
        try {
            waitTheElement(driver, logOutIcon, 5);

            Actions actions = new Actions(driver);
            actions.moveToElement(catalog).build().perform();

            catalog.findElements(By.cssSelector("li")).get(0).click();
        } catch (TimeoutException ignore) {
            ignore.printStackTrace();
            System.out.println("Mark Catalog Item not run");
        }
    }

    public void newProductBtn(){
        //waitTheElement(driver, logOutIcon2,5);
        this.newProductBtn.click();
    }

    public void newProductCreated(String firstName, String firstCount, String firstPrice){
        waitTheElement(driver, logOutIcon2,5);

        this.newProductName.clear();
        this.newProductName.sendKeys(firstName);

        this.newCount.clear();
        this.newCount.sendKeys(firstCount);

        this.newPrice.clear();
        this.newPrice.sendKeys(firstPrice);
    }

    public void activateProduct(){

        waitTheElement(driver, productActivate,5);
        this.productActivate.click();

        waitTheElement(driver, popUpMessage, 5);
        this.closePopUp.click();
    }

    public void saveProduct(){

        try {
        waitTheElement(driver, logOutIcon2,5);
        this.saveBtn.click();
        //waitTheElement(driver, logOutIcon2, 5);

         Thread.sleep(3000);
         this.savePopUpClose.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       // this.closePopUp.click();

    }


    public void waitTheElement(EventFiringWebDriver driver, WebElement element, int timeSec){
        WebDriverWait wait = new WebDriverWait(driver, timeSec);
       // wait.until(ExpectedConditions. elementToBeClickable(element));
        wait.until(ExpectedConditions. visibilityOf(element));
    }

}
