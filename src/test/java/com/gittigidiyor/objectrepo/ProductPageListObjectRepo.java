package com.gittigidiyor.objectrepo;

import org.openqa.selenium.By;

public class ProductPageListObjectRepo {

    public static final By pageNumber = By.cssSelector("[data-testid='pagination-list-item'] a");
    public static final By activePage = By.cssSelector("[aria-current='true'] span");
    public static final By productItem = By.cssSelector("[data-testid='productImageSlider']");

}
