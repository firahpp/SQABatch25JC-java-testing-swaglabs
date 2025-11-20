package com.juaracoding.swaglabs;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.juaracoding.swaglabs.pages.InventoryPage;
import com.juaracoding.swaglabs.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 1, enabled = true)
    @Parameters({"username", "password"})
    public void loginSuccessWithValidCredentialTest(String username, String password) throws InterruptedException {
     
      LoginPage loginPage = new LoginPage(driver);
      loginPage.login(username, password, 500);
      InventoryPage inventoryPage = new InventoryPage(driver);
      Assert.assertEquals(inventoryPage.getPathURL(), "/inventory.html");
    
    }
  
    @Test(priority = 2, enabled = true)
    @Parameters({"invalidUsername", "password"})
    public void loginFailedWithInvalidUsernameTest(String invalidUsername, String password) throws InterruptedException {
  
      LoginPage loginPage = new LoginPage(driver);
      loginPage.login(invalidUsername, password, 500);
      String expected = "Epic sadface: Username and password do not match any user in this service";
      Assert.assertEquals(loginPage.getErrorMessage(), expected);
  

    }

    @Test(priority = 3, enabled = true)
    @Parameters({"username", "invalidPassword"})
    public void loginFailedWithInvalidPasswordTest(String username, String invalidPassword) throws InterruptedException {
  
      LoginPage loginPage = new LoginPage(driver);
      loginPage.login(username, invalidPassword, 500);
      String expected = "Epic sadface: Username and password do not match any user in this service";
      Assert.assertEquals(loginPage.getErrorMessage(), expected);

    }

    //Test Case 4: Login with Empty Username and password

    @Test(priority = 4, enabled = true)
    @Parameters({"emptyUsername", "emptyPassword"})
    public void loginFailedWithEmptyTest(String emptyUsername, String emptyPassword) throws InterruptedException {
  
      LoginPage loginPage = new LoginPage(driver);
      loginPage.login(emptyUsername, emptyPassword, 500);
      String expected = "Epic sadface: Username is required";
      Assert.assertEquals(loginPage.getErrorMessage(), expected);
  
    }
    //Test Case 5: Login with Empty Username

    @Test(priority = 5, enabled = true)
    @Parameters({"emptyUsername", "password"})
    public void loginFailedWithEmptyUsernameTest(String emptyUsername, String password) throws InterruptedException {
  
      LoginPage loginPage = new LoginPage(driver);
      loginPage.login(emptyUsername, password, 500);
      String expected = "Epic sadface: Username is required";
      Assert.assertEquals(loginPage.getErrorMessage(), expected);

    }

     //Test Case 5: Login with Empty Password
    @Test(priority = 6, enabled = true)
    @Parameters({"username", "emptyPassword"})
    public void loginFailedWithEmptyPasswordTest(String username, String emptyPassword) throws InterruptedException {
  
      LoginPage loginPage = new LoginPage(driver);
      loginPage.login(username, emptyPassword, 500);
      String expected = "Epic sadface: Password is required";
      Assert.assertEquals(loginPage.getErrorMessage(), expected);

    }
  }
  