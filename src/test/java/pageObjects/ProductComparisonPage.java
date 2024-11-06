package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductComparisonPage extends BasePage {

    public ProductComparisonPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='content']//h1")
    WebElement titleProductComparison;

    @FindBy(xpath = "//tbody//tr//td//a//strong")
    List<WebElement> titleProduct;

    @FindBy(xpath = "//tbody//tr//td//a[@class='btn btn-danger btn-block']")
    List<WebElement> buttonRemove;

    public String getTitleProductComparison() {
        try {
            wait.until(ExpectedConditions.visibilityOf(titleProductComparison));
            return titleProductComparison.getText();
        } catch(Exception e) {
            return e.getMessage();
        }
    }

    public void removeiMac() {
        // System.out.println(titleProduct.size());
        for(int i = 0; i < titleProduct.size(); i++) {

            WebElement title = titleProduct.get(i);

            if(title.getText().equalsIgnoreCase("iMac")) {
                js.executeScript("arguments[0].scrollIntoView()", title);

                // multiple similar elements need to select one
                WebElement remove = buttonRemove.get(i);
                wait.until(ExpectedConditions.elementToBeClickable(remove)).click();
                System.out.println("iMac removed.");
                break;
            }
        }
    }

}
