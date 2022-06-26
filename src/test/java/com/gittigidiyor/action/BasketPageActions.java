package com.gittigidiyor.action;

import com.gittigidiyor.base.BaseLibrary;
import static com.gittigidiyor.data.GetData.*;

import com.gittigidiyor.utility.log;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import static com.gittigidiyor.objectrepo.BasketPageObjectRepo.*;

public class BasketPageActions extends BaseLibrary {
    public BasketPageActions(RemoteWebDriver driver) {
        super(driver);
    }

    private String getSelectedProductName() throws InterruptedException {
        return getTextOfElement(selectedProductName);
    }

    private String getSelectedProductPrice() throws InterruptedException {
        return getTextOfElement(selectedProductPrice);
    }

    public BasketPageActions checkSelectedProductInfo() throws InterruptedException {
        String selectedProductName = getSelectedProductName();
        String selectedProductPrice = getSelectedProductPrice();

        //Assert.assertEquals(selectedProductName.toLowerCase(), (GetData.productName.toLowerCase()), "ProductName is not identical");
        Assert.assertEquals(selectedProductPrice.toLowerCase(), (productPrice.toLowerCase()), "ProductPrice is not identical");
        log.info("Selected product name and price is identical in basket's.");
        return this;
    }

    public BasketPageActions increaseAmount() throws InterruptedException {

        selectComboboxByText(amountCheckbox, "2");
        return this;
    }

    public BasketPageActions checkIncreasedAmount() throws InterruptedException {
        Thread.sleep(1000);
        String detailText = getTextOfElement(basketDetailText);
        System.out.println("detail text : " + detailText);
        Assert.assertTrue(detailText.contains("2 Adet"), "Product amount couldn't increase");
        log.info("There is 2 product in basket");
        return this;
    }

    public BasketPageActions clearBasket() {
        click(deleteProductButton);
        return this;
    }

    public BasketPageActions checkBasketIsEmpty() throws InterruptedException {
        Thread.sleep(1000);
        String txt = getTextOfElement(basketBox);
        System.out.println("basket text : " + txt);
        Assert.assertTrue(txt.equalsIgnoreCase("Sepetinizde ürün bulunmamaktadır."), "The basket is not empty");
        log.info("The basker is empty");
        return this;
    }
}
