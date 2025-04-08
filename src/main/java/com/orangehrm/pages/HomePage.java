package com.orangehrm.pages;

import com.orangehrm.actionDriver.ActionDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private ActionDriver actionDriver;

    //Define locators using By Class

    private By adminTab = By.xpath("//span[text()='Admin']");
    private By userIdButton = By.className("oxd-userdropdown-img");
    private By logOutButton = By.xpath("//a[text()='Logout']");
    private By orangeHRMLogo = By.xpath("//img[@alt='client brand banner']");

    //Initialize the ActionDriver object by passing webdriver instance
    public HomePage(WebDriver driver) {
        this.actionDriver = new ActionDriver(driver);
    }

    //Method to verify the admin tab is visible
    public boolean isAdminTabVisible() {
        return actionDriver.isDisplayed(adminTab);
    }

    public boolean verifyOrangeHRMLogo() {
        return actionDriver.isDisplayed(orangeHRMLogo);
    }

    //Method to perform logout operation
    public void logout() {
        actionDriver.click(userIdButton);
        actionDriver.click(logOutButton);
    }
}
