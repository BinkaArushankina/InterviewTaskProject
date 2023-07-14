package com.ebay.fw;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
public class AppManager {

    Logger logger = LoggerFactory.getLogger(AppManager.class);
    String browser;
    public WebDriver driver;
    SelectHelper select;
    ItemHelper item;

    public AppManager(String browser) {
        this.browser = browser;
    }

    public void init() {

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();//initialisazia metodow
            logger.info("Tests start in Chrome Browser");
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            logger.info("Tests start in FireFox Browser");
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
            logger.info("Tests start in Safari Browser");
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("remote-allow-origins=*");
            driver = new EdgeDriver(options);
            logger.info("Tests start in Edge Browser");
        }

        WebDriverListener listener = new MyListener();
        driver = new EventFiringDecorator<>(listener).decorate(driver);

        driver.get("https://www.ebay.de"); //sajti
        logger.info("Current url is " + driver.getCurrentUrl());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //dast 10 sec wremeni schtobi sajt uspel sagrusitsa, a potom test

        select = new SelectHelper(driver);
        item = new ItemHelper(driver);
    }

    public void stop() {
        driver.quit();
    }

    public SelectHelper getSelect() {
        return select;
    }

    public ItemHelper getItem() {
        return item;
    }
}
