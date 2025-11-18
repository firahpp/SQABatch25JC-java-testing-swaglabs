package com.juaracoding.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.xpath("//h3[@data-test='error']");


    public LoginPage(WebDriver driver) {
        this.driver = driver; //constructor
      }

    public void inputUsername(String data) {
        driver.findElement(username).sendKeys(data);
      }
    
    public void inputPassword(String data) {
        driver.findElement(password).sendKeys(data);
      }
    
    public void clickButtonLogin() {
        driver.findElement(loginButton).click();
      }
    
      public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
      }
    
    public void login(String username, String password, int delayPerActivity) throws InterruptedException {
        Thread.sleep(delayPerActivity);
        inputUsername(username);
    
        Thread.sleep(delayPerActivity);
        inputPassword(password);
    
        Thread.sleep(delayPerActivity);
        clickButtonLogin();
      }
}
