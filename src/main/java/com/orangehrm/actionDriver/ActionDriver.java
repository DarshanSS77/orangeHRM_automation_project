package com.orangehrm.actionDriver;

import com.orangehrm.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionDriver {
    private WebDriver driver;
    private WebDriverWait wait;

    public ActionDriver(WebDriver driver) {
        this.driver = driver;
        int explicitWait = Integer.parseInt(BaseClass.getProp().getProperty("explicitWait"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait ));
    }

    //Method to click an element
    public void click(By by) {

        try {
            waitForElementToBeClickable(by);
            driver.findElement(by).click();
        } catch (Exception e) {
            System.out.println("Unable to click Element" + e.getMessage());
            ;
        }
    }

    //Method to enter text in input field
    public void enterText(By by, String value) {
        try {
            waitForElementToBeVisible(by);
            WebElement element = driver.findElement(by);
            element.clear();
            element.sendKeys(value);
        } catch (Exception e) {
            System.out.println("Unable to enter value " + e.getMessage());
        }
    }

    //Method to get text from input field
    public String getText(By by) {
        try {
            waitForElementToBeVisible(by);
            return driver.findElement(by).getText();
        } catch (Exception e) {
            System.out.println("Unable to get the text " + e.getMessage());
            return "";
        }
    }

    //Method to compare two text
    public void compareText(By by, String expectedText) {
        try {
            waitForElementToBeVisible(by);
            String actualText = driver.findElement(by).getText();

            if (expectedText.equals(actualText)) {
                System.out.println("Text are matching " + actualText + " equals to " + expectedText);
            } else {
                System.out.println("Text are not matching " + actualText + " not equals to " + expectedText);

            }
        } catch (Exception e) {
            System.out.println("Unable to compare the text " + e.getMessage());
            ;
        }

    }

    /*
        //Method to check if elements are displayed
        public boolean isElementDisplayed(By by) {
            try {
                waitForElementToBeVisible(by);
                boolean isDisplayed = driver.findElement(by).isDisplayed();

                if (isDisplayed) {
                    System.out.println("Element is visible");
                    return isDisplayed;
                } else {
                    System.out.println("Element is not visible");
                    return isDisplayed;
                }
            } catch (Exception e) {
                System.out.println("Element is not displayed " + e.getMessage());
                return false;
            }

        }

        */
//Simplified method and remove redundent condition
    public boolean isDisplayed(By by) {
        try {
            waitForElementToBeVisible(by);
            return driver.findElement(by).isDisplayed();
        } catch (Exception e) {
            System.out.println("Element is not displayed " + e.getMessage());
            return false;

        }
    }

    //Scroll to element
    public void scrollToElement(By by) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(by);
            js.executeScript("argument[0], scrollIntoView(true);", element);
        } catch (Exception e) {
            System.out.println("Unable to locate the element " + e.getMessage());
        }
    }

    // Wait for the page to load
    public void waitForPageLoad(int timeOutInSec) {
        try {
            wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(WebDriver -> ((JavascriptExecutor) WebDriver).executeScript("return document.readyState").equals("complete"));
            System.out.println("Page loaded successfully.");
        } catch (Exception e) {
            System.out.println("Page did not load within " + timeOutInSec + "seconds. Exception: " + e.getMessage());
        }
    }

    //wait Element to be clickable
    private void waitForElementToBeClickable(By by) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            System.out.println("Element is not clickable " + e.getMessage());
            ;
        }
    }

    //wait Element to be visible
    private void waitForElementToBeVisible(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            System.out.println("Element is not visible " + e.getMessage());
            ;
        }
    }
}
