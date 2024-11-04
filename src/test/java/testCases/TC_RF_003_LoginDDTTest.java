package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_RF_003_LoginDDTTest extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="DataDriven") // getting data provider from different class
    public void verifyLogin(String email, String password, String expectedMsg) {
        logger.info("*** Starting TC_RF_003_LoginDDTTest ***");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickLinkMyAccount();
            logger.info("*** Clicking on MyRegister link ***");

            hp.clickLinkLogin();
            logger.info("*** Clicking on Login link ***");

            LoginPage lp = new LoginPage(driver);

            logger.info("*** Entering login details ***");

            lp.setTextEmail(email);
            logger.info("*** Entering registered email ***");

            lp.setTextPassword(password);
            logger.info("*** Entering registered password ***");

            lp.clickButtonLogin();
            logger.info("*** Clicking on Login button ***");

            MyAccountPage map = new MyAccountPage(driver);

            logger.info("*** Verifying expected message ***");
            try {
                boolean status = map.isMyAccountPageExists();

                // data is valid -> login success -> test pass -> logout
                // data is valid -> login fail -> test fail
                // data is invalid -> login success -> test fail -> logout
                // data is invalid -> login fail -> test pass

                if(expectedMsg.equalsIgnoreCase("Valid")) {
                    if(status == true) {
                        map.clickLogout();
                        Assert.assertTrue(status);
                        logger.info("*** Account is valid and successfully logout ***");
                    }
                    else {
                        Assert.assertTrue(false);
                    }
                }

                else if(expectedMsg.equalsIgnoreCase("Invalid")) {
                    if(status == true) {
                        map.clickLogout();
                        Assert.assertTrue(false);
                    }
                    else {
                        Assert.assertTrue(true);
                    }
                }
            }
            catch(Exception e) {
                logger.error("*** Login failed ***");
                throw e;
            }

        } catch(Exception e) {
            Assert.fail("Test failed due to an exception: " + e.getMessage()); // intentionally fail the test
            e.printStackTrace();
        }
        logger.info("*** Finished TC_RF_003_LoginDDTTest ***");
    }
}