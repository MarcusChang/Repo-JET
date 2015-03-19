package com.marcus_chang.test.Project_Stage_X_Iteration_X.Module_X;

import com.marcus_chang.test.TestUtils.ProjectParams;
import com.marcus_chang.test.TestUtils.TestUtilFunctions;
import junit.framework.Assert;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by changxuliang(marcus chang) on 2015/3/17.
 * This java file is used for the auto ui test of the test case X on the Matrix
 * It covers Chrome, Firefox & IE
 */
public class TestCase_X_Chrome {
    @Test
    /**
     *-> TestCaseName_HomePage_Chrome_Test() is used for test the homepage go top function
     */
    public void TestCaseName_HomePage_Chrome_Test() throws IOException {
        //Try to get this file's parent path
        String parentPath = System.getProperty("user.dir");

        Logger logger = Logger.getLogger(TestCase_X_Chrome.class);
        //BasicConfigurator.configure();

        Properties prop = new Properties();

        prop.setProperty("log4j.rootLogger", "ALL, ChromeTestOutputFile");
        prop.setProperty("log4j.appender.ChromeTestOutputFile", "org.apache.log4j.FileAppender");
        prop.setProperty("log4j.appender.ChromeTestOutputFile.File", parentPath + ProjectParams.ChromeTest_X_HomePage_LayOut);
        prop.setProperty("log4j.appender.ChromeTestOutputFile.layout", "org.apache.log4j.PatternLayout");
        prop.setProperty("log4j.appender.ChromeTestOutputFile.layout.ConversionPattern", "%d{HH:mm:ss,SSS} [%t] %-5p %C{1} : %m%n");

        PropertyConfigurator.configure(prop);

        //logger.setLevel(Level.ALL);
        SimpleLayout ChromeTest_X_layout = new SimpleLayout();
        //HTMLLayout htmlLayout = new HTMLLayout();
        FileAppender ChromeTest_X_appender = null;
        //create the selenium verify | assert methods object
        //SeleneseTestBase seleneseTestBase=new SeleneseTestBase();

        try {
            System.out.println(parentPath);
            System.out.println();
            ChromeTest_X_appender = new FileAppender(ChromeTest_X_layout, parentPath + ProjectParams.ChromeTest_X_HomePage_LayOut, false);
        } catch (Exception e) {
            logger.fatal("the FileAppender cannot be initialized, please check the output path!");
        }


        logger.addAppender(ChromeTest_X_appender);
        logger.info("-----> Begin to set the driver properties : chrome driver path = " + ProjectParams.ChromeDriverLocalPath + " <-----");

        //setup the local Chrome driver path
        System.setProperty("webdriver.chrome.driver", ProjectParams.ChromeDriverLocalPath);
        //create a ChromeDriver instance
        WebDriver driver = new ChromeDriver();
        //Open the target URL
        driver.get(ProjectParams.TestURL);
        //create the testUtilFunctions instance
        TestUtilFunctions testUtil = new TestUtilFunctions();
        //maximize the window
        driver.manage().window().maximize();

        testUtil.waitForElement(driver, By.id("simplemodal-data"), (long)5000);

        if (testUtil.isElementPresent(driver, By.id("simplemodal-data"))) {

            WebElement Btn_PopUp21Medal_Close = driver.findElement(By.xpath("//*[@id=\"simplemodal-data\"]/span[1]"));
            testUtil.waitForTime(2000);
            Btn_PopUp21Medal_Close.click();

        } else {
            System.out.println("The 21 metal is not located!");
        }

        //execute the JS to simulate the scroll action which jump to the bottom of the homepage
        testUtil.waitForTime(3000);
        ((JavascriptExecutor) driver).executeScript("scrollTo(0,10000)");

        testUtil.waitForElement(driver, By.id("gotop"), (long)2000);
        WebElement Btn_GoTop = driver.findElement(By.id("gotop"));

        Assert.assertTrue((Btn_GoTop.isDisplayed()));

        if (Btn_GoTop.isDisplayed()){

            Btn_GoTop.click();
            testUtil.waitForTime(3000);
            //verify the page is scrolled to the top when the webdriver sims click the "Jump to Top" button
            WebElement tab_left_banner = driver.findElement(By.id("tab_left"));
            Assert.assertTrue(tab_left_banner.isDisplayed());

        }else{
            System.out.println("the button [Jump to Top] is not found");
        }

        // close the browser after all above test
        driver.quit();
    }





    @Test
    /**
     *-> TestCaseName_StandardTopic_Chrome_Test() is used for test the standard topic go top function
     */
    public void TestCaseName_StandardTopic_Chrome_Test() throws IOException {
        //Try to get this file's parent path
        String parentPath = System.getProperty("user.dir");

        Logger logger = Logger.getLogger(TestCase_X_Chrome.class);
        //BasicConfigurator.configure();

        Properties prop = new Properties();

        prop.setProperty("log4j.rootLogger", "ALL, ChromeTestOutputFile");
        prop.setProperty("log4j.appender.ChromeTestOutputFile", "org.apache.log4j.FileAppender");
        prop.setProperty("log4j.appender.ChromeTestOutputFile.File", parentPath + ProjectParams.ChromeTest_X_StdTopic_LayOut);
        prop.setProperty("log4j.appender.ChromeTestOutputFile.layout", "org.apache.log4j.PatternLayout");
        prop.setProperty("log4j.appender.ChromeTestOutputFile.layout.ConversionPattern", "%d{HH:mm:ss,SSS} [%t] %-5p %C{1} : %m%n");

        PropertyConfigurator.configure(prop);

        //logger.setLevel(Level.ALL);
        SimpleLayout ChromeTest_X_layout = new SimpleLayout();
        //HTMLLayout htmlLayout = new HTMLLayout();
        FileAppender ChromeTest_X_appender = null;
        //create the selenium verify | assert methods object
        //SeleneseTestBase seleneseTestBase=new SeleneseTestBase();

        try {
            System.out.println(parentPath);
            System.out.println();
            ChromeTest_X_appender = new FileAppender(ChromeTest_X_layout, parentPath + ProjectParams.ChromeTest_X_StdTopic_LayOut, false);
        } catch (Exception e) {
            logger.fatal("the FileAppender cannot be initialized, please check the output path!");
        }


        logger.addAppender(ChromeTest_X_appender);
        logger.info("-----> Begin to set the driver properties : chrome driver path = " + ProjectParams.ChromeDriverLocalPath + " <-----");

        // setup the local Chrome driver path
        System.setProperty("webdriver.chrome.driver", ProjectParams.ChromeDriverLocalPath);
        // create a ChromeDriver instance
        WebDriver driver = new ChromeDriver();
        // Open the target URL
        driver.get(ProjectParams.StandardTopicURL);
        //create the testUtilFunctions instance
        TestUtilFunctions testUtil = new TestUtilFunctions();
        //maximize the window
        driver.manage().window().maximize();

        testUtil.waitForElement(driver, By.id("simplemodal-data"), (long)5000);

        if (testUtil.isElementPresent(driver, By.id("simplemodal-data"))) {

            WebElement Btn_PopUp21Medal_Close = driver.findElement(By.xpath("//*[@id=\"simplemodal-data\"]/span[1]"));
            testUtil.waitForTime(2000);
            Btn_PopUp21Medal_Close.click();

        } else {
            System.out.println("The 21 metal is not located!");
        }

        //execute the JS to simulate the scroll action which jump to the bottom of the homepage
        testUtil.waitForTime(3000);
        ((JavascriptExecutor) driver).executeScript("scrollTo(0,10000)");

        testUtil.waitForElement(driver, By.id("gotop"), (long)2000);
        WebElement Btn_GoTop = driver.findElement(By.id("gotop"));

        Assert.assertTrue((Btn_GoTop.isDisplayed()));

        if (Btn_GoTop.isDisplayed()){

            Btn_GoTop.click();
            testUtil.waitForTime(3000);
            //verify the page is scrolled to the top when the webdriver sims click the "Jump to Top" button
            WebElement share_title = driver.findElement(By.xpath("//*[@id=\"share_title\"]"));
            Assert.assertTrue(share_title.isDisplayed());

        }else{
            System.out.println("the button [Jump to Top] is not found");
        }

        // close the browser after all above test
        driver.quit();
    }





    @Test
    /**
     *-> TestCaseName_PKTopic_Chrome_Test() is used for test the PK topic go top function
     */
    public void TestCaseName_PKTopic_Chrome_Test() throws IOException {
        //Try to get this file's parent path
        String parentPath = System.getProperty("user.dir");

        Logger logger = Logger.getLogger(TestCase_X_Chrome.class);
        //BasicConfigurator.configure();

        Properties prop = new Properties();

        prop.setProperty("log4j.rootLogger", "ALL, ChromeTestOutputFile");
        prop.setProperty("log4j.appender.ChromeTestOutputFile", "org.apache.log4j.FileAppender");
        prop.setProperty("log4j.appender.ChromeTestOutputFile.File", parentPath + ProjectParams.ChromeTest_X_PKTopic_LayOut);
        prop.setProperty("log4j.appender.ChromeTestOutputFile.layout", "org.apache.log4j.PatternLayout");
        prop.setProperty("log4j.appender.ChromeTestOutputFile.layout.ConversionPattern", "%d{HH:mm:ss,SSS} [%t] %-5p %C{1} : %m%n");

        PropertyConfigurator.configure(prop);

        //logger.setLevel(Level.ALL);
        SimpleLayout ChromeTest_X_layout = new SimpleLayout();
        //HTMLLayout htmlLayout = new HTMLLayout();
        FileAppender ChromeTest_X_appender = null;
        //create the selenium verify | assert methods object
        //SeleneseTestBase seleneseTestBase=new SeleneseTestBase();

        try {
            System.out.println(parentPath);
            System.out.println();
            ChromeTest_X_appender = new FileAppender(ChromeTest_X_layout, parentPath + ProjectParams.ChromeTest_X_PKTopic_LayOut, false);
        } catch (Exception e) {
            logger.fatal("the FileAppender cannot be initialized, please check the output path!");
        }


        logger.addAppender(ChromeTest_X_appender);
        logger.info("-----> Begin to set the driver properties : chrome driver path = " + ProjectParams.ChromeDriverLocalPath + " <-----");

        // setup the local Chrome driver path
        System.setProperty("webdriver.chrome.driver", ProjectParams.ChromeDriverLocalPath);
        // create a ChromeDriver instance
        WebDriver driver = new ChromeDriver();
        // Open the target URL
        driver.get(ProjectParams.PKTopicURL);
        //create the testUtilFunctions instance
        TestUtilFunctions testUtil = new TestUtilFunctions();
        //maximize the window
        driver.manage().window().maximize();

        testUtil.waitForElement(driver, By.id("simplemodal-data"), (long)5000);

        if (testUtil.isElementPresent(driver, By.id("simplemodal-data"))) {

            WebElement Btn_PopUp21Medal_Close = driver.findElement(By.xpath("//*[@id=\"simplemodal-data\"]/span[1]"));
            testUtil.waitForTime(2000);
            Btn_PopUp21Medal_Close.click();

        } else {
            System.out.println("The 21 metal is not located!");
        }

        //execute the JS to simulate the scroll action which jump to the bottom of the homepage
        testUtil.waitForTime(3000);
        ((JavascriptExecutor) driver).executeScript("scrollTo(0,10000)");

        testUtil.waitForElement(driver, By.id("gotop"), (long)2000);
        WebElement Btn_GoTop = driver.findElement(By.id("gotop"));

        Assert.assertTrue((Btn_GoTop.isDisplayed()));

        if (Btn_GoTop.isDisplayed()){

            Btn_GoTop.click();
            testUtil.waitForTime(3000);
            //verify the page is scrolled to the top when the webdriver sims click the "Jump to Top" button
            WebElement share_title = driver.findElement(By.xpath("//*[@id=\"share_title\"]"));
            Assert.assertTrue(share_title.isDisplayed());

        }else{
            System.out.println("the button [Jump to Top] is not found");
        }

        // close the browser after all above test
        driver.quit();
    }





    @Test
    /**
     *-> TestCaseName_SKUdetails_Chrome_Test() is used for test the SKU details page go top function
     */
    public void TestCaseName_SKUdetails_Chrome_Test() throws IOException {
        //Try to get this file's parent path
        String parentPath = System.getProperty("user.dir");

        Logger logger = Logger.getLogger(TestCase_X_Chrome.class);
        //BasicConfigurator.configure();

        Properties prop = new Properties();

        prop.setProperty("log4j.rootLogger", "ALL, ChromeTestOutputFile");
        prop.setProperty("log4j.appender.ChromeTestOutputFile", "org.apache.log4j.FileAppender");
        prop.setProperty("log4j.appender.ChromeTestOutputFile.File", parentPath + ProjectParams.ChromeTest_X_SkuDetailsPage_LayOut);
        prop.setProperty("log4j.appender.ChromeTestOutputFile.layout", "org.apache.log4j.PatternLayout");
        prop.setProperty("log4j.appender.ChromeTestOutputFile.layout.ConversionPattern", "%d{HH:mm:ss,SSS} [%t] %-5p %C{1} : %m%n");

        PropertyConfigurator.configure(prop);

        //logger.setLevel(Level.ALL);
        SimpleLayout ChromeTest_X_layout = new SimpleLayout();
        //HTMLLayout htmlLayout = new HTMLLayout();
        FileAppender ChromeTest_X_appender = null;
        //create the selenium verify | assert methods object
        //SeleneseTestBase seleneseTestBase=new SeleneseTestBase();

        try {
            System.out.println(parentPath);
            System.out.println();
            ChromeTest_X_appender = new FileAppender(ChromeTest_X_layout, parentPath + ProjectParams.ChromeTest_X_SkuDetailsPage_LayOut, false);
        } catch (Exception e) {
            logger.fatal("the FileAppender cannot be initialized, please check the output path!");
        }


        logger.addAppender(ChromeTest_X_appender);
        logger.info("-----> Begin to set the driver properties : chrome driver path = " + ProjectParams.ChromeDriverLocalPath + " <-----");

        // setup the local Chrome driver path
        System.setProperty("webdriver.chrome.driver", ProjectParams.ChromeDriverLocalPath);
        // create a ChromeDriver instance
        WebDriver driver = new ChromeDriver();
        // Open the target URL
        driver.get(ProjectParams.SKUDetailsURL);
        //create the testUtilFunctions instance
        TestUtilFunctions testUtil = new TestUtilFunctions();
        //maximize the window
        driver.manage().window().maximize();

        testUtil.waitForElement(driver, By.id("simplemodal-data"), (long)5000);

        if (testUtil.isElementPresent(driver, By.id("simplemodal-data"))) {

            WebElement Btn_PopUp21Medal_Close = driver.findElement(By.xpath("//*[@id=\"simplemodal-data\"]/span[1]"));
            testUtil.waitForTime(2000);
            Btn_PopUp21Medal_Close.click();

        } else {
            System.out.println("The 21 metal is not located!");
        }

        //execute the JS to simulate the scroll action which jump to the bottom of the homepage
        testUtil.waitForTime(3000);
        ((JavascriptExecutor) driver).executeScript("scrollTo(0,10000)");

        testUtil.waitForElement(driver, By.id("gotop"), (long)2000);
        WebElement Btn_GoTop = driver.findElement(By.id("gotop"));

        Assert.assertTrue((Btn_GoTop.isDisplayed()));

        if (Btn_GoTop.isDisplayed()){

            Btn_GoTop.click();
            testUtil.waitForTime(3000);
            //verify the page is scrolled to the top when the webdriver sims click the "Jump to Top" button
            WebElement Test_logo = driver.findElement(By.xpath("/html/body/div[2]/h1/a[2]/img"));
            Assert.assertTrue(Test_logo.isDisplayed());

        }else{
            System.out.println("the button [Jump to Top] is not found");
        }

        // close the browser after all above test
        driver.quit();
    }

}
