package com.gittigidiyor.objectrepo;

import org.openqa.selenium.By;

public class HomepageObjectRepo {

    public static final By searchBoxInput = By.cssSelector("[data-cy='header-search-input']");
    public static final By findButton = By.cssSelector("[data-cy='search-find-button']");
    public static final By basketButton = By.className("basket-title");
}
