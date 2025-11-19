package com.juaracoding.swaglabs.pages;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.xpath("//h3[@data-test='error']");

    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver; //constructor
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      }
    public WebElement waitingElementReady(By elementBy) {
    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated((elementBy)));
    return element;
  }

    public void inputUsername(String data) {
      waitingElementReady(username).sendKeys(data);
      }
    
    public void inputPassword(String data) {
      waitingElementReady(password).sendKeys(data);
      }
    
    public void clickButtonLogin() {
      waitingElementReady(loginButton).click();
        
      }
    
      public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
      }
    
    public void login(String username, String password, int delayPerActivity) throws InterruptedException {
      inputUsername(username);
      inputPassword(password);
      clickButtonLogin();
      }
}
