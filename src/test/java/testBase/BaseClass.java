package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

// log4j
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

    public WebDriver driver;
    public Logger logger;
    public Properties prop;

    @BeforeClass(groups = {"Sanity", "Regression", "Master", "EndToEnd"})
    @Parameters({"os", "browser"})
    public void setup(@Optional("mac") String os, @Optional("chrome") String br) throws IOException {

        // loading config.properties file
        FileReader file = new FileReader("./src/test/resources/config.properties");
        prop = new Properties();
        prop.load(file);

        logger = LogManager.getLogger(this.getClass()); // apache log4j

        // remote environment
        if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities dc = new DesiredCapabilities();

            // os
            if(os.equalsIgnoreCase("mac")) {
                dc.setPlatform(Platform.MAC);
            }
            else if(os.equalsIgnoreCase("windows")) {
                dc.setPlatform(Platform.WIN11);
            }
            else if(os.equalsIgnoreCase("linux")) {
                dc.setPlatform(Platform.LINUX);
            }
            else {
                System.out.println("No matching operating system found...");
                return;
            }

            // browser
            switch(br.toLowerCase()) {
                case "chrome" : dc.setBrowserName("chrome"); break;
                case "edge" : dc.setBrowserName("MicrosoftEdge"); break;
                case "firefox" : dc.setBrowserName("firefox"); break;
                default : System.out.println("No matching browser found..."); return;
            }
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
        }

        // local environment - get from xml file
        if(prop.getProperty("execution_env").equalsIgnoreCase("local")){
            switch(br.toLowerCase()) {
                case "chrome" : driver = new ChromeDriver(); break;
                case "edge" : driver = new EdgeDriver(); break;
                case "firefox" : driver = new FirefoxDriver(); break;
                default : System.out.println("No matching browser found..."); return;
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(prop.getProperty("appUrl"));
        driver.manage().window().maximize();
        System.out.println("Web app successfully launched...");
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Web app closed...");
        //driver.quit();
    }

    public String randomString() {
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        return generatedString;
    }

    public String randomNumeric() {
        String generatedNumber = RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }

    public String randomAlphaNumeric() {
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        String generatedNumber = RandomStringUtils.randomNumeric(10);
        return generatedString + "#" + generatedNumber;
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot ss = (TakesScreenshot) driver;
        File sourceFile = ss.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }
}