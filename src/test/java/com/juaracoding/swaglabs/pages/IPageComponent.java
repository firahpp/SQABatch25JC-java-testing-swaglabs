package com.juaracoding.swaglabs.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface IPageComponent {
  public WebElement waitingElementReady(By elementBy);

  public List<WebElement> waitingElementsReady(By elementsBy);
}
