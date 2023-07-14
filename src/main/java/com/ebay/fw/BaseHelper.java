package com.ebay.fw;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseHelper {

    WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementPresent(By locator) { //By class selenium locator peremennaja, ubiwersalnaja
        return driver.findElements(locator).size() > 0;  //esli bolsche 0 ubedimsa schto element totschno suschestwuet
    }

    public boolean isElementPresent1(By locator) { //wtoroe uniwersalnoe sredstwo
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;//esli ne najdesch eto kidaj false, a ne oschibku
        }
    }

    public void type(By locator, String text) {
        if (text != null) {
            driver.findElement(locator).click();
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    public void click(By locator) {//uniwersalnij metod click() on budet klikatj
        driver.findElement(locator).click();
    }

    public boolean isAlertPresent() {//schtobi wspliwajuschee okno robot uwidel i naschal na ok i wernulsa na str
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.alertIsPresent());//klass otwetschajuschij sa oschidanie i ukasiwaem wrema , jawnoe oschidanie, otrabatiwaet 20 sek

        if (alert == null) {
            return false;
        } else {
            driver.switchTo().alert();//pereprigni so stranizi na alert wipliwajuschij
            alert.accept();//robot podtwerschdaet knopku ok na wspliwajuschem okne
            return true;
        }
    }

    public void jumpDown() {
        Actions actions = new Actions(driver);

        actions.sendKeys(Keys.PAGE_DOWN).perform();
    }
}
