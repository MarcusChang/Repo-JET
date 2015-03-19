package com.marcus_chang.test.Project_Stage_X_Iteration_X.Module_X;

import com.marcus_chang.test.TestUtils.ProjectParams;
import com.marcus_chang.test.TestUtils.TestUtilFunctions;
import junit.framework.Assert;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by changxuliang(marcus chang) on 2015/3/18.
 * This java file is used for the auto ui test of the test case Y on the Matrix
 * It covers Chrome browser
 */
public class TestCase_Y_Chrome {
    @Test
    /**
     *-> TestCaseName_Chrome_Test() is used for test the homepage go top function
     */
    public void TestCaseName_Chrome_Test() throws IOException {
        //Try to get this file's parent path
        String parentPath = System.getProperty("user.dir");

        Logger logger = Logger.getLogger(TestCase_Y_Chrome.class);
        //BasicConfigurator.configure();

        Properties prop = new Properties();

        prop.setProperty("log4j.rootLogger", "ALL, ChromeTestOutputFile");
        prop.setProperty("log4j.appender.ChromeTestOutputFile", "org.apache.log4j.FileAppender");
        prop.setProperty("log4j.appender.ChromeTestOutputFile.File", parentPath + ProjectParams.ChromeTest_Y_LayOut);
        prop.setProperty("log4j.appender.ChromeTestOutputFile.layout", "org.apache.log4j.PatternLayout");
        prop.setProperty("log4j.appender.ChromeTestOutputFile.layout.ConversionPattern", "%d{HH:mm:ss,SSS} [%t] %-5p %C{1} : %m%n");

        PropertyConfigurator.configure(prop);

        //logger.setLevel(Level.ALL);
        SimpleLayout ChromeTest_Y_layout = new SimpleLayout();
        //HTMLLayout htmlLayout = new HTMLLayout();
        FileAppender ChromeTest_Y_appender = null;
        //create the selenium verify | assert methods object
        //SeleneseTestBase seleneseTestBase=new SeleneseTestBase();

        try {
            System.out.println(parentPath);
            System.out.println();
            ChromeTest_Y_appender = new FileAppender(ChromeTest_Y_layout, parentPath + ProjectParams.ChromeTest_Y_LayOut, false);
        } catch (Exception e) {
            logger.fatal("the FileAppender cannot be initialized, please check the output path!");
        }


        logger.addAppender(ChromeTest_Y_appender);
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

        testUtil.waitForElement(driver, By.id("simplemodal-data"), (long)8000);

        if (testUtil.isElementPresent(driver, By.id("simplemodal-data"))) {
            WebElement Btn_PopUp21Medal_Close = driver.findElement(By.xpath("//*[@id=\"simplemodal-data\"]/span[1]"));
            testUtil.waitForTime(2000);
            Btn_PopUp21Medal_Close.click();

        } else {
            System.out.println("The 21 metal is not located!");
        }

        //simulate the user click myTopic to open the login pop-up window
        testUtil.waitForTime(3000);
        testUtil.waitForElement(driver, By.id("sidebar_mytopic"), (long)5000);
        WebElement Btn_MyTopic = driver.findElement(By.id("sidebar_mytopic"));

        Assert.assertTrue((Btn_MyTopic.isDisplayed()));

        if (Btn_MyTopic.isDisplayed()) {

            Btn_MyTopic.click();
            testUtil.waitForTime(5000);

            if (testUtil.isElementPresent(driver, By.xpath("//*[@id=\"thicktitler\"]/span"))){
                //simulate the login actions when the login window pop-up
                WebElement Frame_FormLogin = driver.findElement(By.xpath("//*[@id=\"thickconr\"]/iframe"));
                //switch the driver into the Frame_FormLogin inner frame to locate the element
                driver.switchTo().frame(Frame_FormLogin);
                WebElement LoginName = driver.findElement(By.xpath("//*[@id=\"loginname\"]")); //iframe object
                WebElement LoginPass= driver.findElement(By.xpath("//*[@id=\"nloginpwd\"]")); //iframe object
                WebElement LoginSubmit = driver.findElement(By.xpath("//*[@id=\"loginsubmit\"]")); // iframe object
                if (testUtil.isElementPresent(driver, By.xpath("//*[@id=\"chkOpenCtrl\"]"))){
                    WebElement SecurityCheckBox = driver.findElement(By.xpath("//*[@id=\"chkOpenCtrl\"]")); // iframe object
                    testUtil.waitForTime(500);
                    //simulate to check whether the Security Check Box has been checked, if so，cancel the check for the later pass input
                    if(SecurityCheckBox.isEnabled()){
                        SecurityCheckBox.click();
                        testUtil.waitForTime(500);
                    }
                }else{
                    System.out.println("The SecurityCheckBox is not exist!");
                }

                testUtil.clearInputs(driver, LoginName);
                testUtil.clearInputs(driver, LoginPass);
                LoginName.sendKeys(ProjectParams.SimLoginUserName_1);
                LoginPass.sendKeys(ProjectParams.SimLoginUserPass);
                testUtil.waitForTime(1000);
                LoginSubmit.click();
                testUtil.waitForTime(2000);

                //after simulate the login action, switch the driver out of the Frame_FormLogin inner frame
                driver.switchTo().defaultContent();

                if(testUtil.isElementPresent(driver, By.xpath("//*[@id=\"dialog_mytopic_container\"]/div[1]/i"))){
                    testUtil.waitForTime(2000);
                    driver.findElement(By.xpath("//*[@id=\"dialog_mytopic_container\"]/div[1]/i")).click();
                }else{
                    System.out.println("The MyTopic Contents is not pop-up!");
                }

            } else {
                System.out.println("The user already login!");
                if(testUtil.isElementPresent(driver, By.xpath("//*[@id=\"dialog_mytopic_container\"]/div[1]/i"))) {
                    driver.findElement(By.xpath("//*[@id=\"dialog_mytopic_container\"]/div[1]/i")).click();
                }
            }


        } else {
            System.out.println("the button [My Topic] is not located");
        }

        //Simulate the user creates a standard topic on the homepage, then test the url verification mechanism
        testUtil.waitForElement(driver, By.xpath("//*[@id=\"sidebar_ask_btn\"]"), (long)3000);
        WebElement Btn_UserAsk = driver.findElement(By.xpath("//*[@id=\"sidebar_ask_btn\"]"));
        testUtil.waitForTime(3000);
        Btn_UserAsk.click();

        //Assert the returns after the user created the topic
        testUtil.waitForTime(1000);
        Assert.assertTrue(testUtil.isElementPresent(driver, By.xpath("//*[@id=\"dialog_ask_container\"]")));
        WebElement Ipt_AskUrl = driver.findElement(By.xpath("//*[@id=\"dialog_ask_form\"]/div[2]/input"));
        WebElement Ipt_AskTextArea = driver.findElement(By.xpath("//*[@id=\"dialog_ask_form_textarea_wrapper\"]/textarea"));
        WebElement Btn_AskSubmit = driver.findElement(By.xpath("//*[@id=\"dialog_ask_form\"]/p/input"));

        Ipt_AskUrl.sendKeys(ProjectParams.TargetSkuUrl_1);
        Ipt_AskTextArea.sendKeys(ProjectParams.TargetDialogAskInput_4);
        Btn_AskSubmit.click();

        //Assert the returns after the user created the topic
        testUtil.waitForTime(1000);
        WebElement ActReturns_CreateTopic_4 = driver.findElement(By.xpath("//*[@id=\"dialog_ask_form_textarea_wrapper\"]/p/span"));
        Assert.assertEquals(ProjectParams.CreateTopicReturns_1, ActReturns_CreateTopic_4.getText());

        //Clear the URL input to initial the next test input action
        testUtil.clearInputs(driver, Ipt_AskTextArea);
        testUtil.waitForTime(1000);


        //URL verification mechanism test for TargetDialogAskInput_5
        Ipt_AskTextArea.sendKeys(ProjectParams.TargetDialogAskInput_5);
        Btn_AskSubmit.click();

        //Assert the returns after the user created the topic
        testUtil.waitForTime(1000);
        WebElement ActReturns_CreateTopic_5 = driver.findElement(By.xpath("//*[@id=\"dialog_ask_form_textarea_wrapper\"]/p/span"));
        Assert.assertEquals(ProjectParams.CreateTopicReturns_1, ActReturns_CreateTopic_5.getText());

        //Clear the URL input to initial the next test input action
        testUtil.clearInputs(driver, Ipt_AskTextArea);
        testUtil.waitForTime(1000);


        //URL verification mechanism test for TargetDialogAskInput_6
        Ipt_AskTextArea.sendKeys(ProjectParams.TargetDialogAskInput_6);
        Btn_AskSubmit.click();

        //Assert the returns after the user created the topic
        testUtil.waitForTime(1000);
        WebElement ActReturns_CreateTopic_6 = driver.findElement(By.xpath("//*[@id=\"dialog_ask_form_textarea_wrapper\"]/p/span"));
        Assert.assertEquals(ProjectParams.CreateTopicReturns_1, ActReturns_CreateTopic_6.getText());

        //Clear the URL input to initial the next test input action
        testUtil.clearInputs(driver, Ipt_AskTextArea);
        testUtil.waitForTime(1000);


        //URL verification mechanism test for TargetDialogAskInput_7
        Ipt_AskTextArea.sendKeys(ProjectParams.TargetDialogAskInput_7);
        Btn_AskSubmit.click();

        //Assert the returns after the user created the topic
        testUtil.waitForTime(1000);
        WebElement ActReturns_CreateTopic_7 = driver.findElement(By.xpath("//*[@id=\"dialog_ask_form_textarea_wrapper\"]/p/span"));
        Assert.assertEquals(ProjectParams.CreateTopicReturns_1, ActReturns_CreateTopic_7.getText());

        //Clear the URL input to initial the next test input action
        testUtil.clearInputs(driver, Ipt_AskTextArea);
        testUtil.waitForTime(1000);

        WebElement Btn_AskClose = driver.findElement(By.xpath("//*[@id=\"dialog_ask_close\"]"));
        Btn_AskClose.click();

        testUtil.waitForTime(2000);
        // close the browser after all above test
        driver.quit();
    }

}





