package com.orangehrm.pages;

import com.orangehrm.actionDriver.ActionDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private ActionDriver actionDriver;

    //Define locators using By Class
    private By userNameField = By.name("username");
    private By passwordField = By.cssSelector("input[type=\"password\"]");
    private By loginButton = By.xpath("//button[@type=\"submit\"]");
    private By errorMessage = By.xpath("//p[text()='Invalid credentials']");

    //Initialize the ActionDriver object by passing webdriver instance
    public LoginPage(WebDriver driver) {
        this.actionDriver = new ActionDriver(driver);
    }

    //Method to perform login
    public void login(String username, String passsword) {
        actionDriver.enterText(userNameField, username);
        actionDriver.enterText(passwordField, passsword);
        actionDriver.click(loginButton);
    }

    //Method check if error message is displayed
    public boolean isErrorMessageDisplayed() {
        return actionDriver.isDisplayed(errorMessage);
    }

    //Method to get text from error message
    public String getErrorMessageText() {
        return actionDriver.getText(errorMessage);
    }

    //verify if error message is correct or not
    public void verifyErrorMessage(String expectedError) {
        actionDriver.compareText(errorMessage, expectedError);
    }
}
