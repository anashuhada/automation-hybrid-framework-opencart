package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_RF_004_LoginTest extends BaseClass {

    @Test(groups = {"Sanity", "Master"})
    public void verifyLogin() {
        logger.info("*** Starting TC_RF_004_LoginTest ***");
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

            MyAccountPage map = new MyAccountPage(driver);

            logger.info("*** Verifying expected message ***");
            try {
                boolean status = map.isMyAccountPageExists();
                Assert.assertEquals(status, true, "Login failed!");
                logger.info("*** Login success ***");
//            if(status == true) {
//                Assert.assertTrue(status);
//            }
//            else {
//                Assert.assertTrue(false);
//            }
            }
            catch(Exception e) {
                logger.error("*** Login failed ***");
                throw e;
            }

        } catch(Exception e) {
            Assert.fail("Test failed due to an exception: " + e.getMessage()); // intentionally fail the test
            e.printStackTrace();
        }
        logger.info("*** Finished TC_RF_004_LoginTest ***");
    }
}