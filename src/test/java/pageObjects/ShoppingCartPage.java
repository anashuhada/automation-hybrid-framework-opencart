package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='Shopping Cart']")
    WebElement linkShoppingCart;

    @FindBy(xpath = "//tbody//td[@class='text-center']//a//img[@class='img-thumbnail' and @title='MacBook']")
    WebElement linkImage;

    @FindBy(xpath = "//tbody//td[@class='text-left']//a[contains(text(),'MacBook')]")
    WebElement linkText;

    @FindBy(xpath = "//div[@class='table-responsive']//tr")
    List<WebElement> totalRow;

    @FindBy(xpath = "//span[@class='input-group-btn']//button[@data-original-title='Remove']")
    List<WebElement> buttonRemoveList;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    WebElement successUpdateMsg;

    @FindBy(xpath = "//a[normalize-space()='Continue Shopping']")
    WebElement buttonContinueShopping;

    @FindBy(xpath = "//a[@class='btn btn-primary']")
    WebElement buttonCheckout;

    @FindBy(xpath = "//div[@id='accordion']//div[@class='panel panel-default']")
    List<WebElement> listAccordion;

    public void clickShoppingCart() {
        wait.until(ExpectedConditions.elementToBeClickable(linkShoppingCart)).click();
    }

    public void clickLinkImage() throws InterruptedException {
        js.executeScript("arguments[0].click()", linkImage);
        Thread.sleep(2000);
        driver.navigate().back();
    }

    public void clickLinkText() throws InterruptedException {
        js.executeScript("arguments[0].click()", linkText);
        Thread.sleep(2000);
        driver.navigate().back();
    }

    public void updateQuantity(String searchProduct, String qty) throws InterruptedException {
        for (int i = 0; i < totalRow.size(); i++) {
            WebElement row = totalRow.get(i);
            if (row.getText().contains(searchProduct)) {
                System.out.println("Found product: " + searchProduct);

                // find the quantity input field within the specific row
                WebElement quantityField = row.findElement(By.xpath(".//div[@class='input-group btn-block']//input[@type='text']"));
                wait.until(ExpectedConditions.visibilityOf(quantityField));
                quantityField.clear();
                Thread.sleep(2000);
                quantityField.sendKeys(qty);

                // find the update button within the specific row
                WebElement updateButton = row.findElement(By.xpath(".//button[@data-original-title='Update']"));
                wait.until(ExpectedConditions.visibilityOf(updateButton)).click();
                break;
            }
        }
    }

    public String successMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successUpdateMsg));
            return successUpdateMsg.getText();
        } catch(Exception e) {
            return e.getMessage();
        }
    }

    public void clickAccordion() throws InterruptedException {
        for(WebElement a : listAccordion) {
            wait.until(ExpectedConditions.elementToBeClickable(a));
            js.executeScript("arguments[0].scrollIntoView(true)", a);
            a.click();
            System.out.println(a.getText());
            Thread.sleep(2000);
        }
    }

    public void removeProduct(String searchProduct) {
        for (WebElement row : totalRow) {
            if (row.getText().contains(searchProduct)) {
                System.out.println("Removing product: " + searchProduct);

                // single specific element within a broader element or container
                WebElement removeButton = row.findElement(By.xpath(".//span[@class='input-group-btn']//button[@data-original-title='Remove']"));

                wait.until(ExpectedConditions.visibilityOf(removeButton)).click();
                break;
            }
        }
    }

    public void checkStockStatus(String searchProduct, String textDangerSymbol) {
        for (int i = 0; i < totalRow.size(); i++) {
            WebElement row = totalRow.get(i);
            if (row.getText().contains(searchProduct)) {
                if (row.getText().contains(textDangerSymbol)) {
                    System.out.println("Product is out of stock - cannot proceed to checkout. Continue Shopping.");
                    wait.until(ExpectedConditions.elementToBeClickable(buttonContinueShopping)).click();
                } else {
                    wait.until(ExpectedConditions.elementToBeClickable(buttonCheckout)).click();
                }
            }
        }
    }

}
