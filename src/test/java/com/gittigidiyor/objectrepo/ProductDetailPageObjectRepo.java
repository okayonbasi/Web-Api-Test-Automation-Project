package com.gittigidiyor.objectrepo;

import org.openqa.selenium.By;

public class ProductDetailPageObjectRepo {

    public static final By productName = By.id("sp-title");
    public static final By productPrice = By.id("sp-price-highPrice");
    public static final By addBasketButton = By.xpath("//*[@id='add-to-basket']");
    public static final By miniBasketAlertBox = By.cssSelector("[class='header-cart-container-title']");
}
