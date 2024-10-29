package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    WebElement msgHeading;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
    WebElement linkLogout;

    public boolean isMyAccountPageExists() {
        try {
            return msgHeading.isDisplayed();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void clickLogout() {
        js.executeScript("arguments[0].click()", linkLogout);
    }
}
