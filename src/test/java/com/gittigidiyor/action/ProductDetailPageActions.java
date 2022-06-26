package com.gittigidiyor.action;

import com.gittigidiyor.base.BaseLibrary;
import com.gittigidiyor.data.GetData;

import static com.gittigidiyor.objectrepo.ProductDetailPageObjectRepo.*;

import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductDetailPageActions extends BaseLibrary {


    public ProductDetailPageActions(RemoteWebDriver driver) {
        super(driver);
    }

    public ProductDetailPageActions getProductName() throws InterruptedException {
        GetData.productName = getTextOfElement(productName);
        return this;
    }

    public ProductDetailPageActions getProductPrice() throws InterruptedException {
        GetData.productPrice = getTextOfElement(productPrice);
        return this;
    }

    public ProductDetailPageActions addBasket() throws InterruptedException {

        do {
            click(addBasketButton);
        } while (!isDisplayedTrue(miniBasketAlertBox));


        return this;
    }
}
