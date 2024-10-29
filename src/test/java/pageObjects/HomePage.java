package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    // constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // locators
    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement linkMyAccount;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
    WebElement linkRegister1;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
    WebElement linkLogin;

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    WebElement buttonRegister;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Register']")
    WebElement linkRegister2;

    // action methods
    public void clickLinkMyAccount() {
        js.executeScript("arguments[0].click()", linkMyAccount);
    }

    public void clickLinkRegister() {
        js.executeScript("arguments[0].click()", linkRegister1);
    }

    public void clickLinkLogin() {
        js.executeScript("arguments[0].click()", linkLogin);
    }
}
