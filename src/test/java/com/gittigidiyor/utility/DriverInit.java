package com.gittigidiyor.utility;

import com.gittigidiyor.data.GetData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DriverInit {

    RemoteWebDriver driver;

    public void setDriver(ThreadLocal<RemoteWebDriver> webDriver,String browserName) throws MalformedURLException {

        browserName = browserName.equals("") || browserName == null ? "chrome" : browserName;
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
//        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");

        switch (browserName) {
            case "grid-firefox": {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("start-maximized");
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
                System.out.println("***** Selenium Grid Firefox *****");
                break;
            }
            case "grid-chrome": {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
                System.out.println("***** Selenium Grid Chrome *****");
                break;
            }
            case "firefox": {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("start-maximized");
                driver = new FirefoxDriver(firefoxOptions);
                System.out.println("***** Browser is firefox *****");
                break;
            }
            case "firefox-headless": {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("start-maximized");
                firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                System.out.println("***** Browser is firefox *****");
                break;
            }
            case "chrome": {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                driver = new ChromeDriver(chromeOptions);
                System.out.println("***** Browser is chrome *****");
                break;
            }
            case "chrome-headless": {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                System.out.println("***** Browser is chrome-headless *****");
                break;
            }
            default: {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                System.out.println("***** Browser is chrome-bonigarcia *****");
                break;
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(GetData.DEFAULT_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(GetData.DEFAULT_WAIT_LOADERBOX, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        webDriver.set(driver);
        log.info("Setup started");
    }

    public WebDriver getDriver(){
        return driver;
    }

}

