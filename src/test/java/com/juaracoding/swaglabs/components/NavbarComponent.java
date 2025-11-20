package com.juaracoding.swaglabs.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavbarComponent extends BaseComponent{
    private By burgerMenu = By.id("react-burger-menu-btn");
    private By resetSidebarLink = By.id("reset_sidebar_link");

    public NavbarComponent(WebDriver driver) {
        super(driver);
    }  

    public void clickBurgerMenu() {
        waitingElementReady(burgerMenu).click();
    }

    public void clickResetSideBar() {
        waitingElementReady(resetSidebarLink).click();
    }

}
