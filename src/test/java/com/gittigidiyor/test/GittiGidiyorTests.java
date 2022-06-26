package com.gittigidiyor.test;

import com.gittigidiyor.action.*;
import com.gittigidiyor.utility.DriverInit;
import com.gittigidiyor.utility.log;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class GittiGidiyorTests {

    protected ThreadLocal<RemoteWebDriver> webDriver = new ThreadLocal<RemoteWebDriver>();

    HomepageActions homepageActions;
    ProductPageListActions productPageListActions;
    ProductDetailPageActions productDetailPageActions;
    BasketPageActions basketPageActions;

    @Parameters({"browser"})
    @BeforeTest
    public void beforeMethod(@Optional String browserName) throws MalformedURLException {

        log.info("Browser name : " + browserName);
        DriverInit driverInit = new DriverInit();
        driverInit.setDriver(webDriver, browserName);
    }

    @BeforeMethod
    public void beforeTest() {
        homepageActions = new HomepageActions(webDriver.get());
        productPageListActions = new ProductPageListActions(webDriver.get());
        productDetailPageActions = new ProductDetailPageActions(webDriver.get());
        basketPageActions = new BasketPageActions(webDriver.get());
    }

    @Test(enabled = true, description = "TestCase01")
    public void TestCase01() throws InterruptedException {

        String product = "Bilgisayar";
        String pageNumber = "2";

        homepageActions.
                openPage().
                searchProduct(product);

        productPageListActions.
                openPageByPageNumber(pageNumber).
                isPageOpen(pageNumber).
                selectRandomProduct();

        productDetailPageActions.
                getProductName().
                getProductPrice().
                addBasket();

        homepageActions.
                openBasket();

        basketPageActions.
                checkSelectedProductInfo().
                increaseAmount().
                checkIncreasedAmount().
                clearBasket().
                checkBasketIsEmpty();

    }


    @AfterMethod
    public void afterMethod() {

        webDriver.get().quit();
    }
}
