package com.juaracoding.swaglabs.pages;

import org.openqa.selenium.WebDriver;

import com.juaracoding.swaglabs.components.HeaderComponent;

public class InventoryPage {
  private WebDriver driver;

  private HeaderComponent headerComponent;

  public InventoryPage(WebDriver driver) {
    this.driver = driver;
    headerComponent = new HeaderComponent(driver);
  }

  public HeaderComponent getHeaderComponent() {
    return headerComponent;
  }

  public String getPathURL() {
    String[] path = driver.getCurrentUrl().split("/");
    return "/" + path[path.length - 1];
  }
}
