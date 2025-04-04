package com.orangehrm.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class BaseClass {
    protected static Properties prop;
    protected static WebDriver driver;

    @BeforeSuite
    public void loadConfig() throws IOException {
        //Load the configuration file

        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/java/resources/config.properties");
        prop.load(fis);

    }

    @BeforeMethod
    public void setup() throws IOException {
        System.out.println("Setting up WebDriver for : " + this.getClass().getSimpleName());
        launchBrowser();
        configureBrowser();
        staticWait(2);

    }

    //initialize the Webdriver based on browser defined in config.property file

    private void launchBrowser() {
        //Initialize the browser based on browser defined in config.properties file

        String browser = prop.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();

        } else {
            throw new IllegalArgumentException("Browser not supported " + browser);
        }
    }

    //configure the brwoser settings such as implicit wait , maximize the browser and navigate to url
    private void configureBrowser() {
        //Implicit wait
        int implicitWait = Integer.parseInt(prop.getProperty("implicitWait"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

        //maximize the browser
        driver.manage().window().maximize();

        //Navigate to url
        try {
            driver.get(prop.getProperty("url"));
        } catch (Exception e) {
            System.out.println("Failed to navigate to the url " + e.getMessage());
            throw new RuntimeException(e);

        }
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Unable to quit the driver " + e.getMessage());
            }
        }
    }

    //Driver getter method
    public WebDriver getDriver() {
        return driver;
    }

    //setting driver
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    //static wait for pause
    public void staticWait(int seconds) {
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
    }


}
