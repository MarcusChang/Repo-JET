package com.marcus_chang.test.webdriver_demo;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.*;
import org.junit.Test;
import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.thoughtworks.selenium.SeleneseTestBase;


/**
 * Created by changxuliang on 2015/3/2.
 */


public class ExampleForIE {

    @Test
    public void IE_Test() throws IOException {

        //Try to get this file's parent path
        String parentPath = System.getProperty("user.dir");

        Logger logger = Logger.getLogger(ExampleForIE.class);
        //BasicConfigurator.configure();

        Properties prop = new Properties();

        prop.setProperty("log4j.rootLogger", "INFO, IEDemoOutputFile");
        prop.setProperty("log4j.appender.IEDemoOutputFile", "org.apache.log4j.FileAppender");
        prop.setProperty("log4j.appender.IEDemoOutputFile.File", parentPath + "\\WebDriverLogs\\IEDemoLayOut.html");
        prop.setProperty("log4j.appender.IEDemoOutputFile.layout", "org.apache.log4j.PatternLayout");
        prop.setProperty("log4j.appender.IEDemoOutputFile.layout.ConversionPattern", "%d{HH:mm:ss,SSS} [%t] %-5p %C{1} : %m%n");


        /**below is the log property for console output
         *prop.setProperty("log4j.rootLogger", "ALL, CONSOLE");
         *prop.setProperty("log4j.appender.CONSOLE", "org.apache.log4j.ConsoleAppender");
         *prop.setProperty("log4j.appender.CONSOLE.layout", "org.apache.log4j.PatternLayout");
         *prop.setProperty("log4j.appender.CONSOLE.layout.ConversionPattern", "%d{HH:mm:ss,SSS} [%t] %-5p %C{1} : %m%n");
         */


        PropertyConfigurator.configure(prop);

        //logger.setLevel(Level.ALL);
        SimpleLayout IEDemoLayout = new SimpleLayout();
        //HTMLLayout htmlLayout = new HTMLLayout();
        FileAppender IEdemo_appender = null;
        //create the selenium verify | assert methods object
        SeleneseTestBase seleneseTestBase=new SeleneseTestBase();


        try {
            System.out.println(parentPath);
            System.out.println();
            IEdemo_appender = new FileAppender(IEDemoLayout, parentPath + "\\WebDriverLogs\\IEDemoLayOut.html", false);
        } catch (Exception e) {
            logger.fatal("the FileAppender cannot be initialized, please check the output path!");
        }


        logger.addAppender(IEdemo_appender);
        logger.info("-----> Begin to set the driver properties : IE driver path = C:\\Users\\admin\\Desktop\\IEDriverServer_Win32_2.44.0\\IEDriverServer.exe <-----");
        // setup the local IE driver path
        System.setProperty(
                "webdriver.ie.driver",
                "C:\\Users\\admin\\Desktop\\IEDriverServer_Win32_2.44.0\\IEDriverServer.exe");
        DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);

        // create a FirefoxDriver instance
        WebDriver driver = new InternetExplorerDriver(ieCapabilities);
        // Open the Baidu URL
        driver.get("http://www.baidu.com");
        // below code can also open the target URL
        // driver.navigate().to("http://www.baidu.com");


        try {
            Thread.sleep(5000);
            String browserTitle = driver.getTitle();

            if (!(browserTitle.equals("百度一下，你就知道"))){
                logger.error("The home page is not located! Please check the network connection.");
            }else{
                // get the page tab title
                System.out.println("Home Page title is: " + driver.getTitle());
                logger.info("The home page is located successfully. The Home Page title is: " + driver.getTitle());
            }

        }catch(Exception e){
            logger.error("The home page title cannot be obtained, please check the home page tab string.");
        }


        try {
            // use the By.id to locate the input DOM
            WebElement element = driver.findElement(By.id("kw"));

            if (element == null) {
                logger.error("The search bar on the home page is not located, its id cannot be found.");

            } else {
                // send the search keys
                element.sendKeys("JD");
                Thread.sleep(5000);
                // submit the key
                //element.submit();
                // Timeout set to 10 sec, then verify whether the text has been searched correctly
                (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        return d.getTitle().toLowerCase().endsWith("京东_百度搜索");
                    }
                });

                //seleneseTestBase is used for verify the test result, its verify* methods can skip the junit assert exception return and execute the case continually.
                //seleneseTestBase.verifyEquals("京东_百度搜索", driver.getTitle());

                //Assert methods extends the Junit package used for test result assertion.
                //Assert.assertEquals("The searched result is not match the expected result!", "京东_百度搜索", driver.getTitle());

                // print the searched page's tab title
                System.out.println("The searched page title is: " + driver.getTitle());
            }

        }catch(Exception e){
            logger.error("The search bar cannot be located, please check the search bar's id.");
        }

        // close the browser after all above test
        driver.quit();

    }
}
