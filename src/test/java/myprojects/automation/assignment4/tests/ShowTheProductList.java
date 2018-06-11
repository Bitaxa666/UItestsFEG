package myprojects.automation.assignment4.tests;

import myprojects.automation.assignment4.general.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;


import java.util.List;

/**
 * Created by user on 10/9/17.
 */
public class ShowTheProductList extends PageObject {

    SoftAssert softAssert = new SoftAssert();

    @FindBy(css = "i.material-icons.shopping-cart")
    private WebElement logOutIcon;

    @FindBy(tagName = "body")
    private WebElement bodyText;

    //@FindBy (css = ".all-product-link.pull-xs-left.pull-md-right.h4")
    @FindBy (css = ".all-product-link")
    private WebElement allProductBtn;


    //@FindBy (css = ".h3.product-title")
    @FindBy (css = ".product-description")
    private WebElement productList;

    @FindBy(css = ".products article")
    private List<WebElement> products;

    @FindBy (css = ".h1.h1")
    private WebElement productName;

    @FindBy (css = "#main > div.row > div:nth-child(2) > div.product-prices > div.product-price.h5 > div > span")
    private WebElement productPrice;

    public ShowTheProductList(EventFiringWebDriver driver) {
        super(driver);
    }

    public void showAllProduct(String name){
    //public void showAllProduct(){

        waitTheElement(driver, logOutIcon, 5);

        this.allProductBtn.click();

        String productBlockXPath = String.format("//article[contains(@class,'product-miniature') and .//h1[contains(@class,'product-title') and .='%s']]", name);
        softAssert.assertEquals(name, productBlockXPath);

        //softAssert.assertEquals("Blouse", "Blouse");
        Reporter.log("Your element is find");
    }
    public void openProduct(String name, String price){
//       List<WebElement> nameProducts = new List<WebElement>();
//        добавим в список ряд элементов
//
//        nameProducts = productList.findElements(By.cssSelector("h1"));
//
//        System.out.printf("В списке %d элементов \n", nameProducts.size());
//        for(WebElement prod : nameProducts){
//
//            if(prod.getText() == CreateNewProduct.getFirstName()) {
//                System.out.println(prod.getText());
//                prod.click();
//            }
//
//        }
        List<WebElement> elements = driver.findElements(By.cssSelector(".product-description"));
        java.util.Iterator<WebElement> i = elements.iterator();
        while(i.hasNext()) {
            WebElement element = i.next();
           // if (element.getText().contains("Blouse")) {
            if (element.getText().contains(name)) {
                //softAssert.assertTrue(element.getText().contains(CreateNewProduct.getFirstName()));
                softAssert.assertTrue(element.getText().contains(name));
                Reporter.log(element.getText().toUpperCase() + ": webElement is find");
                System.out.println(element.getText().toUpperCase() + "web is find");
                element.click();

                waitTheElement(driver, productName, 5);

                softAssert.assertEquals(name.toUpperCase(), productName.getText());
                //softAssert.assertEquals(price, productPrice.getText().substring(0, 6));
                System.out.println(productName.getText());
                System.out.println(productPrice.getText().substring(0, 6));
                softAssert.assertTrue(productPrice.getText().substring(0, 6).contains(price));
                softAssert.assertAll();
                break;

            }
            else System.out.println("element is not found inner the loop");

        }
        System.out.println("element is not found outer the loop");
    }

    private String getProductName(WebElement product) {
        return product.findElement(By.cssSelector("h1 a")).getText();
    }

    private String getProductPrice(WebElement product) {
        return product.findElement(By.className("price")).getText();
    }

    public void openProductTest(String name, String price){
        WebElement product = products.stream()
               // .filter(p -> getProductName(p).equals(name) && getProductPrice(p).equals(price))
                .filter(p -> getProductName(p).equals(name) && getProductPrice(p).contains(price))
                .findFirst().orElseThrow(
                        () -> new NoSuchElementException(String.format("Product '%s' ('%s') is not present on the page.", name, price))
                );
        product.click();
    }

    public void waitTheElement(EventFiringWebDriver driver, WebElement element, int timeSec){
        WebDriverWait wait = new WebDriverWait(driver, timeSec);
        //wait.until(ExpectedConditions. elementToBeClickable(element));
        wait.until(ExpectedConditions. visibilityOf(element));
    }

}
