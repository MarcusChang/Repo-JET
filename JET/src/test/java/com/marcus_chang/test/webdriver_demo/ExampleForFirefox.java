package com.marcus_chang.test.webdriver_demo;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.*;
import org.junit.Test;
import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.thoughtworks.selenium.SeleneseTestBase;

/**
 * Created by changxuliang on 2015/3/2.
 */


public class ExampleForFirefox {

    @Test
    public void Firefox_Test() throws IOException {

        //Try to get this file's parent path
        String parentPath = System.getProperty("user.dir");

        Logger logger = Logger.getLogger(ExampleForFirefox.class);
        //BasicConfigurator.configure();

        Properties prop = new Properties();

        prop.setProperty("log4j.rootLogger", "INFO, FirefoxDemoOutputFile");
        prop.setProperty("log4j.appender.FirefoxDemoOutputFile", "org.apache.log4j.FileAppender");
        prop.setProperty("log4j.appender.FirefoxDemoOutputFile.File", parentPath + "\\WebDriverLogs\\FirefoxDemoLayOut.html");
        prop.setProperty("log4j.appender.FirefoxDemoOutputFile.layout", "org.apache.log4j.PatternLayout");
        prop.setProperty("log4j.appender.FirefoxDemoOutputFile.layout.ConversionPattern", "%d{HH:mm:ss,SSS} [%t] %-5p %C{1} : %m%n");


        /**below is the log property for console output
         *prop.setProperty("log4j.rootLogger", "ALL, CONSOLE");
         *prop.setProperty("log4j.appender.CONSOLE", "org.apache.log4j.ConsoleAppender");
         *prop.setProperty("log4j.appender.CONSOLE.layout", "org.apache.log4j.PatternLayout");
         *prop.setProperty("log4j.appender.CONSOLE.layout.ConversionPattern", "%d{HH:mm:ss,SSS} [%t] %-5p %C{1} : %m%n");
         */


        PropertyConfigurator.configure(prop);

        //logger.setLevel(Level.ALL);
        SimpleLayout FirefoxDemoLayout = new SimpleLayout();
        //HTMLLayout htmlLayout = new HTMLLayout();
        FileAppender FirefoxDemo_appender = null;
        //create the selenium verify | assert methods object
        SeleneseTestBase seleneseTestBase=new SeleneseTestBase();

        try {
            System.out.println(parentPath);
            System.out.println();
            FirefoxDemo_appender = new FileAppender(FirefoxDemoLayout, parentPath + "\\WebDriverLogs\\FirefoxDemoLayOut.html", false);
        } catch (Exception e) {
            logger.fatal("the FileAppender cannot be initialized, please check the output path!");
        }


        logger.addAppender(FirefoxDemo_appender);
        logger.info("-----> Begin to set the driver properties : Firefox driver path = D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe <-----");
        // setup the local Firefox driver path
        System.setProperty(
                "webdriver.firefox.bin",
                "D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        // create a FirefoxDriver instance
        WebDriver driver = new FirefoxDriver();
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

                //  print the searched page's tab title
                System.out.println("The searched page title is: " + driver.getTitle());
            }

        }catch(Exception e){
            logger.error("The search bar cannot be located, please check the search bar's id.");
        }

        // close the browser after all above test
        driver.quit();

    }
}
