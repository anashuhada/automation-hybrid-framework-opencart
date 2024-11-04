package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC_RF_005_ShoppingCartTest extends BaseClass {

    @Test
    public void verifyShoppingCart() throws InterruptedException {
        try {
            HomePage hp = new HomePage(driver);
            hp.clickLinkMyAccount();
            logger.info("*** Clicking on MyRegister link ***");

            hp.clickLinkLogin();
            logger.info("*** Clicking on Login link ***");

            LoginPage lp = new LoginPage(driver);

            logger.info("*** Entering login details ***");

            lp.setTextEmail(prop.getProperty("email"));
            logger.info("*** Entering registered email ***");

            lp.setTextPassword(prop.getProperty("password"));
            logger.info("*** Entering registered password ***");

            lp.clickButtonLogin();
            logger.info("*** Clicking on Login button ***");

            logger.info("*** Verifying Shopping Cart page ***");
            ShoppingCartPage sc = new ShoppingCartPage(driver);
            sc.clickShoppingCart();
            logger.info("*** Clicking Shopping Cart link ***");

            sc.clickLinkImage();
            logger.info("*** Clicking the image link ***");

            sc.clickLinkText();
            logger.info("*** Clicking the text link ***");

            sc.updateQuantity("MacBook", "2");
            logger.info("*** Updating the quantity of MacBook ***");

            String msg = sc.successMessage();
            if(msg.contains("Success")) {
                Assert.assertTrue(true);
            }
            else {
                Assert.assertTrue(false);
            }
            logger.info("*** Verifying the update alert message ***");

            sc.clickAccordion();
            logger.info("*** Clicking the accordion ***");

            sc.removeProduct("iMac");
            logger.info("*** Removing iMac from Shopping Cart ***");

            sc.checkStockStatus("MacBook", "***");
            logger.info("*** Checking the existance of danger text ***");

        } catch(Exception e) {
            Assert.fail("Test failed due to an exception: " + e.getMessage()); // intentionally fail the test
            e.printStackTrace();
        }

        logger.info("*** Finished TC_RF_005_ShoppingCartTest ***");

    }
}
