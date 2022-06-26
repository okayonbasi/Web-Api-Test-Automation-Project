package com.gittigidiyor.base;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.gittigidiyor.data.GetData;
import com.gittigidiyor.utility.log;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseLibrary {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseLibrary(RemoteWebDriver driver) {
        this.wait = new WebDriverWait(driver, GetData.DEFAULT_WAIT);
        this.driver = driver;
        DOMConfigurator.configure("src/test/resources/Log4j.xml");
    }


    protected void navigateTo(String url) {
        try {
            driver.get(url);
            log.info("Web application launched");
        } catch (Exception e) {
            log.error("Error while getting app url : " + e.getMessage());
            throw new RuntimeException(e);

        }

    }

    protected void openUrl(String url) {
        try {
            navigateTo(url);
            log.info("openUrl transaction passed");
        } catch (Exception e) {
            log.info("openUrl transaction failed");
            throw new RuntimeException(e);

        }

    }

    protected WebElement findElement(By by) throws InterruptedException {
        WebElement element = null;
        try {
            Thread.sleep(500);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
            element = driver.findElement(by);

        } catch (
                Exception e) {
            log.error("Error while finding element  e : " + e.getMessage());
            throw new RuntimeException(e);

        }
        return element;
    }

    protected WebElement findElements(By by, int nth) throws InterruptedException {
        WebElement element = null;
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
            element = driver.findElements(by).get(nth);

        } catch (
                Exception e) {
            log.error("Error while finding elements  e : " + e.getMessage());
            throw new RuntimeException(e);

        }
        return element;
    }

    protected List<WebElement> findElements(By by) {
        List<WebElement> elements = null;

        try {
            elements = driver.findElements(by);

        } catch (
                Exception e) {
            log.error("Error while finding elements  e : " + e.getMessage());
            throw new RuntimeException(e);

        }
        return elements;
    }

    protected void click(By by) {
        WebElement element;

        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            element = findElement(by);
            log.info("clicked by -->> " + by);
            element.click();
        } catch (Exception e) {
            clickJS(by);
        }
    }

    protected void click(By by, int nth) {
        WebElement element;

        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            element = findElements(by, nth);
            log.info("clicked by -->> " + by);
            element.click();
        } catch (Exception e) {
            clickJS(by, nth);
        }
    }

    protected int getElementSize(By by) throws InterruptedException {
        List<WebElement> elements = findElements(by);
        if (isExist(by))
            return elements.size();
        else
            return 0;
    }

    private void clickJS(By by) {
        WebElement element;

        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            element = findElement(by);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            log.error("Error while clicking webelement : " + e.getMessage());
            throw new RuntimeException(e);

        }
    }

    private void clickJS(By by, int nth) {
        WebElement element;

        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            element = findElements(by, nth);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {

            log.error("Error while clicking webelement : " + e.getMessage());
            throw new RuntimeException(e);

        }
    }

    protected boolean isDisplayedTrue(By by) throws InterruptedException {
        WebElement element;
        try {
            element = findElement(by);
            String data = element.getText();
            element.isDisplayed();
            log.info("Checking element : " + by);
            return true;
        } catch (Exception e) {
            log.error("Error while checking field : " + e);
            return false;
        }
    }

    public boolean isDisplayAllItem(By by) {

        boolean statu = true;

        List<WebElement> elements = driver.findElements(by);
        int count = elements.size();

        if (count == 0)
            statu = false;

        for (int i = 0; i < count; i++) {
            if (!elements.get(i).isDisplayed()) {
                log.error("There is unloaded something");  //Assert.assertTrue(false,"There is unloaded something");
                statu = false;
                break;
            }
        }

        return statu;
    }

    protected boolean isExist(By by) throws InterruptedException {
        boolean statu;
        try {
            statu = findElements(by).size() != 0;
            log.info("Checking exist element by -->> " + by);
            log.info("Statu : " + statu);
            return statu;
        } catch (Exception e) {
            log.error("Error while checking element : " + e);
            return false;
        }
    }


    protected void sendKeys(By by, String txt) {
        WebElement element = null;

        try {
            element = findElement(by);
            if (element.isEnabled()) {
                element.clear();
                element.sendKeys(txt);
                log.info("SendKeys text : " + txt + " ,by -->> " + by);

            }
        } catch (Exception e) {

            log.error("Error while filling field : " + e.getMessage());

            throw new RuntimeException(e);
        }
    }

    protected void selectComboboxByText(By by, String text) throws InterruptedException {
        WebElement element = findElement(by);
        String elemText = null;
        try {
            if (element.isEnabled()) {
                elemText = element.getText();
                Select selectBox = new Select(element);
                selectBox.selectByValue(text);


            }
            log.info("Value : " + text + " - SelectComboBox : " + elemText);
        } catch (Exception e) {
            log.error("Error while selecting value in combobox : " + e);

            throw new RuntimeException(e);
        }
    }

    protected String getTextOfElement(By by) throws InterruptedException {
        WebElement element = findElement(by);
        String elemText = null;
        try {

            elemText = element.getText();
//            log.info("Element Text : " + elemText);


        } catch (Exception e) {
            log.error("Error while getting text of element : " + e);
        }

        return elemText;

    }

    protected String getTextOfElement(By by, int nth) throws InterruptedException {
        WebElement element = findElements(by, nth);
        String elemText = null;
        try {

            elemText = element.getText();
//            log.info("Element Text : " + elemText);


        } catch (Exception e) {
            log.error("Error while getting text of element : " + e);
        }

        return elemText;

    }

}
