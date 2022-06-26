package com.gittigidiyor.action;

import com.gittigidiyor.base.BaseLibrary;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.util.Random;

import static com.gittigidiyor.objectrepo.ProductPageListObjectRepo.*;


public class ProductPageListActions extends BaseLibrary {


    public ProductPageListActions(RemoteWebDriver driver) {
        super(driver);
    }


    public ProductPageListActions openPageByPageNumber(String pageNum) throws InterruptedException {

        int elementSize = getElementSize(pageNumber);
        String text;
        for (int i = 0; i < elementSize; i++) {
            text = getTextOfElement(pageNumber, i);
            if (text.equals(pageNum)) {
                click(pageNumber, i);
                break;
            }
        }
        return this;
    }

    public ProductPageListActions isPageOpen(String pageNumber) throws InterruptedException {

        String activePageNumber = getTextOfElement(activePage);
        Assert.assertEquals(activePageNumber, pageNumber, "Page Number is wrong");

        return this;
    }

    public ProductPageListActions selectRandomProduct() throws InterruptedException {

        Random random = new Random();
        int num = random.nextInt(32);

        click(productItem, num);
        return this;
    }
}
