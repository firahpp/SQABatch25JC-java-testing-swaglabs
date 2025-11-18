package com.juaracoding.swaglabs.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.juaracoding.swaglabs.utils.ScreenshotUtil;

public class ScreenshotListener implements ITestListener {
  // When Test case get passed, this method is called.		
  @Override		
  public void onTestSuccess(ITestResult result) {		
    ITestContext context = result.getTestContext();
    WebDriver driver = (WebDriver) context.getAttribute("driver");

    String path = ScreenshotUtil.takeScreenshot(driver, result.getName() + "_success");
    Reporter.log("<div><img style='width: 30%' src='" + path + "' /></div>");
    Reporter.log("<div><a href='"+ path +"' target='_blank'>" + result.getName()  + "</a></div>");						
  }		

  // When Test case get failed, this method is called.		
  @Override		
  public void onTestFailure(ITestResult result) {		
    ITestContext context = result.getTestContext();
    WebDriver driver = (WebDriver) context.getAttribute("driver");

    String path = ScreenshotUtil.takeScreenshot(driver, result.getName() + "_fail");
    Reporter.log("<div><img style='width: 30%' src='" + path + "' /></div>");
    Reporter.log("<div><a href='"+ path +"' target='_blank'>" + result.getName()  + "</a></div>");							
  }	
}
