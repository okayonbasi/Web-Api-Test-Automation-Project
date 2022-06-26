package com.gittigidiyor.objectrepo;

import org.openqa.selenium.By;

public class BasketPageObjectRepo {
    public static final By selectedProductName = By.cssSelector("[class$='product-item-box-container'] h2");
    public static final By selectedProductPrice = By.cssSelector("[class$='product-item-box-container'] [class='total-price']");
    public static final By amountCheckbox = By.cssSelector("[class='amount']");
    public static final By basketDetailText = By.cssSelector("[class^='cart-payment-detail']");
    public static final By deleteProductButton = By.cssSelector("[class$='product-item-box-container'] [title='Sil']");
    public static final By basketBox = By.cssSelector("[class='products-container'] h2");
}
