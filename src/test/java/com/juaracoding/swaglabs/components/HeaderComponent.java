package com.juaracoding.swaglabs.components;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// WebElement chartIcon = driver.findElement(By.xpath("//span[@data-test='shopping-cart-badge']"));
public class HeaderComponent {
  private WebDriver driver;

  private By buttonAddToCart;
  private By buttonRemoveCart;
  private By cartIcon = By.xpath("//span[@data-test='shopping-cart-badge']");

  private WebDriverWait wait;

  public HeaderComponent(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  public void setButtonAddToCart(String xpathExpression) {
    this.buttonAddToCart = By.xpath(xpathExpression);
  }

  public void setButtonRemoveCart(String xpathExpression) {
    this.buttonRemoveCart = By.xpath(xpathExpression);
  }

  public void clickButtonAddToCart() {
    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(buttonAddToCart));
    element.click();
  }

  public void clickButtonRemoveCart() {
    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(buttonRemoveCart));
    element.click();
  }

  public boolean isVisibleButtonAddToCart() {
    try {
      return driver.findElement(buttonAddToCart).isDisplayed();
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isVisibleButtonRemoveToCart() {
    try {
      return driver.findElement(buttonRemoveCart).isDisplayed();
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public String getTextButtonAddToCart() {
    return driver.findElement(buttonAddToCart).getText();
  }
  
  public String getTextButtonRemoveCart() {
    return driver.findElement(buttonAddToCart).getText();
  }

  public int getTotalCart() {
    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(cartIcon));

    return Integer.parseInt(element.getText());
  }
}
